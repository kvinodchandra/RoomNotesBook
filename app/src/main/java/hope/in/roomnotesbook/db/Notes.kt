package hope.`in`.roomnotesbook.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_notes")
data class Notes(
    @ColumnInfo (name = "notes_title") val notes_title: String,
    @ColumnInfo (name = "notes_details") val notes_details: String,
    @ColumnInfo (name = "saved_date") val saved_date: String,
    @ColumnInfo (name = "is_bookmarked") val is_bookmarked: Boolean,
    @ColumnInfo (name = "notes_image", typeAffinity = ColumnInfo.BLOB) var image: ByteArray?
)
{
    @PrimaryKey(autoGenerate = true) var notes_id: Int = 0
}