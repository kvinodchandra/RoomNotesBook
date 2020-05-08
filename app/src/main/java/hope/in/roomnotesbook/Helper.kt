package hope.`in`.roomnotesbook

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun dateFormate(): String{
    val date = Date()
    val format = SimpleDateFormat("MMMM dd, hh:mm a")
    return format.format(date).toString()
}