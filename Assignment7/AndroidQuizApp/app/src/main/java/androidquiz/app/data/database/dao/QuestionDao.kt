package androidquiz.app.data.database.dao

import androidquiz.app.data.database.entity.Question
import androidx.room.*

@Dao
interface QuestionDao {
    @Query("SELECT * FROM question")
    fun getAllQuestion() : List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(question : Question) : Long

    @Query("UPDATE question SET user_answer = :user_answer WHERE id = :id")
    fun updateAnswer(id: Int, user_answer: Int)

    @Query("UPDATE question SET user_answer = -1")
    fun resetAnswer()

}