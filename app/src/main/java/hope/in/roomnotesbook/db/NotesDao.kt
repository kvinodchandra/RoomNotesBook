package hope.`in`.roomnotesbook.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {
    @Insert
    suspend fun insertAll(vararg notes: Notes)

    @Query("SELECT * FROM tbl_notes ORDER BY notes_id DESC")
    fun getAllNotes(): LiveData<List<Notes>>
}

