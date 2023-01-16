package androidquiz.app.ui.analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidquiz.app.data.database.entity.Question
import androidquiz.app.data.repository.AndroidQuizRepository
import androidquiz.app.util.loading.Loading

class AnalysisViewModel(private val repo: AndroidQuizRepository) : ViewModel() {
    val quesionList: LiveData<List<Question>> by lazy {
        repo.questionList
    }

    val loading: LiveData<Loading> by lazy {
        repo.loading
    }

    fun getAllQuetions() {
        repo.getAllQuestions()
    }
}
