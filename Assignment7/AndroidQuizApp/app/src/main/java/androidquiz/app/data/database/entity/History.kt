package androidquiz.app.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = false)
    val date: Date,
    val score: Int
)