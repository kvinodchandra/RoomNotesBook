package hope.`in`.roomnotesbook

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import hope.`in`.roomnotesbook.db.AppDatabase
import hope.`in`.roomnotesbook.db.Notes
import kotlinx.android.synthetic.main.fragment_add_notes.*
import kotlinx.coroutines.launch


class AddNotesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_notes, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments!=null){
            et_note_title.setText(arguments.getString("notes_title"))
            et_notes_details.setText((arguments.getString("notes_details")))
        }

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), 1)
            // Permission is not granted
        }

        iv_save_notes.setOnClickListener {
            saveNotes()
        }
    }

    fun saveNotes(){
        val str_note_title = et_note_title.text.toString().trim()
        val str_note_detail = et_notes_details.text.toString().trim()

//            if(str_note_title.isEmpty()){
//                et_note_title.error = "title required"
//                et_note_title.requestFocus()
//                return@setOnClickListener
//            }
//            if(str_note_detail.isEmpty()){
//                et_notes_details.error = "details required"
//                et_notes_details.requestFocus()
//                return@setOnClickListener
//            }
//
//        launch {
//            val note = Notes(str_note_title, str_note_detail, dateFormate(), false, )
//            context?.let {
//                AppDatabase.getDatabase(it).notesDao().insertAll(note)
//                it.toast("Note save")
//            }
//        }
    }
}