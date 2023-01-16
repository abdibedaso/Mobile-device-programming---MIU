package androidquiz.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidquiz.app.data.database.entity.History

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHistory(history: History)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllHistory(historyList: List<History>)

    @Query("SELECT * FROM history WHERE date= :date")
    fun getSingleHistory(date: String): History?

    @Query("SELECT * FROM history")
    fun getAllHistory(): List<History>

    @Query("DELETE FROM history")
    fun deleteAllHistory()
}