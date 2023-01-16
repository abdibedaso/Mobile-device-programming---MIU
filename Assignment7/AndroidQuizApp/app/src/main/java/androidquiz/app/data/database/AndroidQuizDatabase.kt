package androidquiz.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import androidquiz.app.data.database.dao.QuestionDao
import androidquiz.app.data.database.entity.Question
import androidquiz.app.R
import androidquiz.app.data.database.dao.HistoryDao
import androidquiz.app.data.database.entity.History
import androidquiz.app.data.database.typeconvertors.DateConverter
import java.io.BufferedReader

@Database(
    entities = [Question::class, History::class],
    version = 1
)
@TypeConverters(*[AnswerConverter::class, DateConverter::class])
abstract class AndroidQuizDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun historyDao(): HistoryDao

    companion object : SingletonHolder<AndroidQuizDatabase, Context>(
        {
            Room.databaseBuilder(it.applicationContext,
                AndroidQuizDatabase::class.java,
                "quiz_app.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch (AppDispatchers.diskDispatcher) {
                            try {
                                //creating variable that holds the loaded data
                                val questions = loadJSONArray(it)
                                if (questions != null){
                                    //looping through the variable as specified fields are loaded with data
                                    for (i in 0 until questions.length()){
                                        val question = questions.getJSONObject(i);
                                        val answers = question.getJSONArray("answers");

                                        var listAnswer = ArrayList<String>();
                                        listAnswer.add(answers.getString(0));
                                        listAnswer.add(answers.getString(1));
                                        listAnswer.add(answers.getString(2));
                                        listAnswer.add(answers.getString(3));
                                        val q = Question(
                                            question.getString("question"),
                                            question.getInt("correct"),
                                            question.getInt("id"),
                                            question.getString("image"),
                                            listAnswer,
                                            -1
                                        );
                                        val rs = AndroidQuizDatabase.getInstance(it).questionDao().insertQuestion(q);
                                    }
                                }
                            }
                            //error when exception occurs
                            catch (e: JSONException) {
                                println("error reading json data file " + e);
                            }
                        }
                    }

                    private fun loadJSONArray(it: Context): JSONArray? {
                        val inputStream = it.resources.openRawResource(R.raw.questions)
                        BufferedReader(inputStream.reader()).use {
                            return JSONArray(it.readText())
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    )
}
