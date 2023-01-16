package androidquiz.app.ui.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_item_layout.view.tv_date
import kotlinx.android.synthetic.main.history_item_layout.view.tv_score
import androidquiz.app.R
import androidquiz.app.data.database.entity.History
import java.text.SimpleDateFormat

class HistoryAdapter(private val context: Context) :
    ListAdapter<History, HistoryAdapter.HistoryViewHolder>(CALLBACK) {

    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem.date.toString() == newItem.date.toString()
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem.date.toString() == newItem.date.toString()
            }
        }
    }
//
//    override fun getItemViewType(position: Int): Int {
//        val history = getItem(position)
//        return 1
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context!!)
                .inflate(
                    R.layout.history_item_layout,
                    parent,
                    false
                )
        )
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = getItem(position)
        val sdf = SimpleDateFormat("EE, dd MMM - hh:mm a")
        holder.tvDate.text = sdf.format(history.date)
        holder.tvScore.text = "${history.score}/15"
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate = itemView.tv_date!!
        val tvScore = itemView.tv_score!!
    }
}