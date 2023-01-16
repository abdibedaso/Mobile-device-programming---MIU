package androidquiz.app.ui.play

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.play_layout.view.*
import androidquiz.app.R
import androidquiz.app.data.database.entity.Question
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.play_fragment.*
import org.koin.android.ext.android.inject

class QuestionAdapter(val viewModel: PlayViewModel,
    private val itemClicked: (position: Int, optionSelected: Int) -> Unit,
    private val updateAnswerTrigger: (position: Int, optionSelected: Int) -> Unit,
    private val quitClicked: () -> Unit,
    private val homeClicked:() -> Unit,
    private val nextClicked:(position: Int) -> Unit,
    private val context: Context
) : ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(CALLBACK) {
    private var isSelected = false
    private val handler = Handler()

    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<Question>() {
            override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem.question == newItem.question
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.play_layout,
                parent,
                false
            )
        )
    }

    private fun optionClicked(
        tvOption: TextView,
        tvOptionName: TextView,
        optionCard: CardView,
        selectedOption: Int,
        correctAnswer: Int,
        position: Int
    ) {


        optionCard.isEnabled = false
        optionCard.isClickable = false
        tvOption.setTextColor(context.getColor(R.color.white))
        tvOptionName.setTextColor(context.getColor(R.color.white))
        optionCard.backgroundTintList =
            ColorStateList.valueOf(context.getColor(R.color.lightGreen))

        updateAnswerTrigger(position, selectedOption)
        isSelected = true
        itemClicked(position, selectedOption)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val question = getItem(position)
        val page = position + 1
        holder.questionNo.text = page.toString()
        //Question
        holder.tvQuestion.text = question.question
        //Options
        holder.tvOption1.text = question.answers[0]
        holder.tvOption2.text = question.answers[1]
        holder.tvOption3.text = question.answers[2]
        holder.tvOption4.text = question.answers[3]

        holder.tvOption1.setOnClickListener {
            optionClicked(
                holder.tvOption1,
                holder.tvA,
                holder.cardOption1,
                0,
                question.correct,
                position
            )
            //animation.cancel()
            cancelOtherOptions(0, holder.OptionsList,holder.OptionNameList,holder.CardViewList)
        }
        holder.tvOption2.setOnClickListener {
            optionClicked(
                holder.tvOption2,
                holder.tvB,
                holder.cardOption2,
                1,
                question.correct,
                position
            )
            //animation.cancel()
            cancelOtherOptions(1, holder.OptionsList,holder.OptionNameList,holder.CardViewList)
        }
        holder.tvOption3.setOnClickListener {
            optionClicked(
                holder.tvOption3,
                holder.tvC,
                holder.cardOption3,
                2,
                question.correct,
                position
            )
           // animation.cancel()
            cancelOtherOptions(2, holder.OptionsList,holder.OptionNameList,holder.CardViewList)
        }
        holder.tvOption4.setOnClickListener {
            optionClicked(
                holder.tvOption4,
                holder.tvD,
                holder.cardOption4,
                3,
                question.correct,
                position
            )
            //animation.cancel()
            cancelOtherOptions(3, holder.OptionsList,holder.OptionNameList,holder.CardViewList)
        }
        //Quit Button
        holder.btnQuit.setOnClickListener {
            quitClicked()
        }
        //adapter callback
        holder.btnGoHome.setOnClickListener{
            homeClicked()
        }

        holder.btnNext.setOnClickListener {
            if(isSelected == true){
                isSelected = false
                nextClicked(position)
                handler.postDelayed({
                    holder.tvWarning.text = ""
                }, 200)

            }else{
                holder.tvWarning.text = "Choose an answer!"
            }

        }
    }
    private fun cancelOtherOptions(selectedOption: Int,OptionsList: List<TextView>, OptionNameList: List<TextView>, CardViewList: List<CardView>){
        for (i in 0..3){
            if(i != selectedOption){
                OptionsList[i].setTextColor(context.getColor(R.color.black))
                OptionNameList[i].setTextColor(context.getColor(R.color.black))
                CardViewList[i].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#dbe9c5"))
            }
        }

    }
    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvQuestion = itemView.tvQuestion!!
        val cardOption1: CardView = itemView.card_option1!!
        val cardOption2: CardView = itemView.card_option2!!
        val cardOption3: CardView = itemView.card_option3!!
        val cardOption4: CardView = itemView.card_option4!!
        val tvA = itemView.tv_a!!
        val tvB = itemView.tv_b!!
        val tvC = itemView.tv_c!!
        val tvD = itemView.tv_d!!
        val tvOption1 = itemView.tv_option1!!
        val tvOption2 = itemView.tv_option2!!
        val tvOption3 = itemView.tv_option3!!
        val tvOption4 = itemView.tv_option4!!
        val tvWarning = itemView.tvIsSelect!!
        val btnQuit = itemView.btn_quit!!
        val btnGoHome = itemView.btn_goHome!!
        val btnNext = itemView.btn_next!!
        val questionNo = itemView.tv_i15!!
        val OptionsList = listOf<TextView>(
            itemView.tv_option1!!,
            itemView.tv_option2!!,
            itemView.tv_option3!!,
            itemView.tv_option4!!
        )
        val CardViewList = listOf<CardView>(
            itemView.card_option1!!,
            itemView.card_option2!!,
            itemView.card_option3!!,
            itemView.card_option4!!
        )
        val OptionNameList = listOf<TextView>(
            itemView.tv_a!!,
            itemView.tv_b!!,
            itemView.tv_c!!,
            itemView.tv_d!!
        )

    }
}