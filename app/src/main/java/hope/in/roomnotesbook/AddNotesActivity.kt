package hope.`in`.roomnotesbook

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import hope.`in`.roomnotesbook.db.AppDatabase
import hope.`in`.roomnotesbook.db.Notes
import kotlinx.android.synthetic.main.activity_add_notes.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class AddNotesActivity : BaseActivity() {

    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        if (intent != null){
            et_note_title.setText(intent.getStringExtra("notes_title"))
            et_notes_details.setText((intent.getStringExtra("notes_details")))
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
            // Permission is not granted
        }

        iv_save_notes.setOnClickListener {
            saveNotes()
        }

        imageView5.setOnClickListener {
            imageView5.setSelected(false)
        }
    }

    fun saveNotes(){
        val str_note_title = et_note_title.text.toString().trim()
        val str_note_detail = et_notes_details.text.toString().trim()

        val image = bitmapToByte(bitmap)

        launch {
            val note = Notes(str_note_title, str_note_detail, dateFormate(), false, image)
            this?.let {
                AppDatabase.getDatabase(applicationContext).notesDao().insertAll(note)
                applicationContext.toast("Note save")
            }
        }
    }

    fun bitmapToByte(bitmap: Bitmap?): ByteArray? {
        if (bitmap!=null){
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            return byteArrayOutputStream.toByteArray()
        }else{
            return null
        }
    }
}
