package androidquiz.app.ui.analysis

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.text.Html.fromHtml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidquiz.app.R
import androidquiz.app.data.database.entity.Question
import kotlinx.android.synthetic.main.analysis_item_layout.view.*
import java.text.SimpleDateFormat

class AnalysisAdapter(private val context: Context) :
    ListAdapter<Question, AnalysisAdapter.AnalysisViewHolder>(CALLBACK) {

    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<Question>() {
            override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem.id  == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisViewHolder {
        return AnalysisViewHolder(
            LayoutInflater.from(parent.context!!)
                .inflate(
                    R.layout.analysis_item_layout,
                    parent,
                    false
                )
        )
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: AnalysisViewHolder, position: Int) {
        val question = getItem(position)
        val sdf = SimpleDateFormat("EE, dd MMM - hh:mm a")
        holder.tvQuestion.text = question.question;

        if(question.user_answer != question.correct) {
            holder.analysicImage.setImageResource(R.drawable.ic_incorrect);
            if(question.user_answer != -1) {
                holder.tvUserAnswer.text = fromHtml("<b>Your Answer:</b> " + question.answers.get(question.user_answer));
                holder.tvUserAnswer.setTextColor(context.getColor(R.color.darkRed1))
            }
            holder.tvCorrectAnswer.text = fromHtml("<b>Correct Answer:</b> " + question.answers.get(question.correct));
        } else {
            holder.analysicImage.setImageResource(R.drawable.ic_correct);
            holder.tvUserAnswer.text = fromHtml("<b>The Answer:</b> " + question.answers.get(question.user_answer));
            holder.tvUserAnswer.setTextColor(context.getColor(R.color.green))
            holder.tvCorrectAnswer.text = "";
        }
    }

    class AnalysisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestion = itemView.tv_question!!
        var tvUserAnswer = itemView.tv_user_answer!!
        var tvCorrectAnswer = itemView.tv_correct_answer!!
        var analysicImage = itemView.iv_analysisImage!!
    }
}