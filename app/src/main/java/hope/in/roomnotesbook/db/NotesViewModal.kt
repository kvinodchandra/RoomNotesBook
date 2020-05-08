package hope.`in`.roomnotesbook.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class NotesViewModal(application: Application): AndroidViewModel(application) {
    public val notesRepository: NotesRepository

    val getAllNotes: LiveData<List<Notes>>

    init {
        val notesDao = AppDatabase.getDatabase(application).notesDao()
        notesRepository = NotesRepository(notesDao)
        getAllNotes = notesRepository.getAllNotes
    }

    fun insert(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        notesRepository.insertAll(notes)
    }
}