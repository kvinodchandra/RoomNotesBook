package hope.`in`.roomnotesbook

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import hope.`in`.roomnotesbook.db.Notes
import kotlinx.android.synthetic.main.note_list_item.view.*


class NotesListAdapter(
    context: Activity
): RecyclerView.Adapter<NotesListAdapter.NotesView>() {
    var cntx = context
    private var notes = emptyList<Notes>()

    internal fun setNotes(notes: List<Notes>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesView {
        return NotesView(LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_item, null))
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NotesView, position: Int) {
        holder.view.tv_notes_title.text = notes[position].notes_title
        holder.view.tv_notes_detail.text = notes[position].notes_details
        holder.view.tv_save_date.text = notes[position].saved_date

        holder.view.item_card_view.setOnClickListener {
            val i = Intent(cntx, AddNotesActivity::class.java)
            i.putExtra("notes_title", holder.view.tv_notes_title.text.toString())
            i.putExtra("notes_details", holder.view.tv_notes_detail.text.toString())
            cntx.startActivity(i)
        }
    }
    class NotesView(val view: View) : RecyclerView.ViewHolder(view)
}

