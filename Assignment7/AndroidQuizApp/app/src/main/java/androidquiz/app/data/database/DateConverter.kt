package androidquiz.app.data.database.typeconvertors

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
class DateConverter {

    @TypeConverter
    fun toDate(date: String): Date {
        val sdf = SimpleDateFormat("yyyy MM dd HH mm ss")
        return sdf.parse(date)!!
    }

    @TypeConverter
    fun fromDate(date: Date): String {
        val sdf = SimpleDateFormat("yyyy MM dd HH mm ss")
        return sdf.format(date)
    }

}