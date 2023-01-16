package androidquiz.app.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    val question: String,
    val correct: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String? = null,
    val answers: List<String>,
    var user_answer: Int
)