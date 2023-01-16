package androidquiz.app.ui.play

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidquiz.app.data.database.entity.History
import androidquiz.app.data.database.entity.Question
import androidquiz.app.data.repository.AndroidQuizRepository
import androidquiz.app.util.loading.Loading
import androidx.lifecycle.MutableLiveData

class PlayViewModel(private val repo: AndroidQuizRepository) : ViewModel() {
    val questionList: LiveData<List<Question>> by lazy {
        repo.questionList
    }

    val loading: LiveData<Loading> by lazy {
        repo.loading
    }

    fun resetLoading() = repo.resetLoading()

    fun getAllQuestions() {
        repo.getAllQuestions()
    }

    fun addHistory(history: History) {
        repo.addHistory(history)
    }

    fun updateAnswer(id: Int, user_answer: Int) {
        repo.updateAnswer(id, user_answer)
    }
}
