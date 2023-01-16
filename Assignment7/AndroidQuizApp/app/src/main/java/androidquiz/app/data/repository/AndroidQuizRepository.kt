package androidquiz.app.data.repository

import androidx.lifecycle.LiveData
import androidquiz.app.data.database.entity.History
import androidquiz.app.data.database.entity.Question
import androidquiz.app.util.loading.Loading

interface AndroidQuizRepository {
    val questionList: LiveData<List<Question>>
    val historyList: LiveData<List<History>>
    val loading: LiveData<Loading>
    fun resetLoading()
    fun getAllQuestions()
    fun getAllHistories()
    fun addHistory(history: History)
    fun updateAnswer(id: Int, user_answer: Int)
    fun resetAnswer()
}