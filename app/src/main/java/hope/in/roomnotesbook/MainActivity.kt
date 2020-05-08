package hope.`in`.roomnotesbook


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import hope.`in`.roomnotesbook.db.NotesViewModal
import kotlinx.android.synthetic.main.activity_main.*

import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata


class MainActivity : AppCompatActivity(){

    private lateinit var noteViewModel: NotesViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcv_notes_list.setHasFixedSize(true)
        rcv_notes_list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val adapter = NotesListAdapter(this)
        rcv_notes_list.adapter = adapter

        noteViewModel = ViewModelProvider(this).get(NotesViewModal::class.java)
        noteViewModel.getAllNotes.observe(this, Observer { notes ->
            notes?.let {
               adapter.setNotes(it)
            }
        })
//        launch {
//            this.let {
//                val notes = AppDatabase.getDatabase(applicationContext).notesDao().getAllNotes()
//                rcv_notes_list.adapter = NotesListAdapter(notes)
//            }
//        }
        listAllFiles()
    }

    fun onClickAddNotes(v: View){
        val i = Intent(this, AddNotesActivity::class.java)
        i.putExtra("notes_title", "")
        i.putExtra("notes_details", "")
        startActivity(i)
//        val fr = AddNotesFragment()
//        fr.arguments = b
//        fragmentManager
//            .beginTransaction()
//            .add(R.id.container, fr)
//            .addToBackStack(null)
//            .commit()
    }

    fun listAllFiles() {
        // [START storage_list_all]
        val storage = Firebase.storage
        val listRef = storage.reference.child("files/uid")

        listRef.listAll()
            .addOnSuccessListener { listResult ->
                listResult.prefixes.forEach { prefix ->
                    // All the prefixes under listRef.
                    // You may call listAll() recursively on them.
                }

                listResult.items.forEach { item ->
                    Log.e("Image", item.path)
                }
            }
            .addOnFailureListener {
                // Uh-oh, an error occurred!
            }
        // [END storage_list_all]
    }

}
