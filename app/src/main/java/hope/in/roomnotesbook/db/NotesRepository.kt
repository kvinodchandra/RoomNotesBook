package hope.`in`.roomnotesbook.db

import androidx.lifecycle.LiveData

public class NotesRepository(private val notesDao: NotesDao) {
    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insertAll(notes: Notes){
        notesDao.insertAll()
    }
}