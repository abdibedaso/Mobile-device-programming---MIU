package androidquiz.app.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidquiz.app.data.database.AndroidQuizDatabase
import androidquiz.app.data.database.entity.History
import androidquiz.app.data.database.entity.Question
import androidquiz.app.util.loading.Loading
import androidquiz.app.util.loading.LoadingState

class AndroidQuizRepositoryImpl(application: Application ) : AndroidQuizRepository {
    private val mutableQuestionList = MutableLiveData<List<Question>>()
    private val mutableHistoryList = MutableLiveData<List<History>>()
    private val mutableLoading = MutableLiveData<Loading>()
    val database: AndroidQuizDatabase = AndroidQuizDatabase.getInstance(application)

    override val loading: LiveData<Loading>
        get() = mutableLoading

    override val questionList: LiveData<List<Question>>
        get() = mutableQuestionList

    override val historyList: LiveData<List<History>>
        get() = mutableHistoryList

    override fun resetLoading() {
        mutableLoading.postValue(Loading(-1, LoadingState.IDLE))
    }

    override fun getAllQuestions() {
        GlobalScope.launch(Dispatchers.IO) {
            mutableLoading.postValue(Loading(1, LoadingState.LOADING))
            val questions = database.questionDao().getAllQuestion();
            mutableQuestionList.postValue(questions)
            mutableLoading.postValue(Loading(1, LoadingState.COMPLETED))
        }
    }

    override fun getAllHistories() {
        GlobalScope.launch(Dispatchers.IO) {
            mutableLoading.postValue(Loading(1, LoadingState.LOADING))
            val histories = database.historyDao().getAllHistory();
            mutableHistoryList.postValue(histories)
            mutableLoading.postValue(Loading(1, LoadingState.COMPLETED))
        }
    }

    override fun addHistory(history: History) {
        database.historyDao().addHistory(history)
    }

    override fun updateAnswer(id: Int, user_answer: Int) {
        database.questionDao().updateAnswer(id, user_answer)
        val questions = database.questionDao().getAllQuestion();
        mutableQuestionList.postValue(questions)
    }

    override fun resetAnswer() {
        database.questionDao().resetAnswer()
        val questions = database.questionDao().getAllQuestion();
        mutableQuestionList.postValue(questions)
    }
}