package androidquiz.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidquiz.app.data.database.entity.Question
import androidquiz.app.data.repository.AndroidQuizRepository

class HomeViewModel(private val repo: AndroidQuizRepository) : ViewModel() {
    val questionList: LiveData<List<Question>> by lazy {
        repo.questionList
    }

    fun getAllQuestions() {
        repo.getAllQuestions()
        repo.resetAnswer()
    }
}