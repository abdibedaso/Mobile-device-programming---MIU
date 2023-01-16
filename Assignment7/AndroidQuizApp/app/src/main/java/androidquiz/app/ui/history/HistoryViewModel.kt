package androidquiz.app.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidquiz.app.data.database.entity.History
import androidquiz.app.data.repository.AndroidQuizRepository
import androidquiz.app.util.loading.Loading

class HistoryViewModel(private val repo: AndroidQuizRepository) : ViewModel() {
    val historyList: LiveData<List<History>> by lazy {
        repo.historyList
    }

    val loading: LiveData<Loading> by lazy {
        repo.loading
    }

    fun getAllHistory() {
        repo.getAllHistories()
    }
}
