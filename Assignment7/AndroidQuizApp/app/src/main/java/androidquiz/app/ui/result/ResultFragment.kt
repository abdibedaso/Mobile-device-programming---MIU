package androidquiz.app.ui.result

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.result_fragment.*
import androidquiz.app.R

class ResultFragment : Fragment() {

    companion object {
        fun newInstance() =
            ResultFragment()
    }

    private lateinit var navController: NavController
    private var score: Int = 0
    private var categoryName = "Android"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        score = ResultFragmentArgs.fromBundle(requireArguments()).correctAnswers
        setupUI()
        setupButton()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        if (Build.VERSION.SDK_INT > 24) {
            val s = "Quiz Topic: <b>${categoryName.toUpperCase()}</b>"
            tv_subText.text = "Your Android Quizz Score"//Html.fromHtml(s, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        } else {
            tv_subText.text = "Your Android Quizz Score"//"Quiz Topic: ${categoryName.toUpperCase()}"
        }
        chip_user.text = "$score correct"
        chip_opponent.text = "${15 - score} incorrect"
        chip_total.text = "Your score : $score / 15 "
        if(score == 0) {
            tv_matchStatus.text = "Oop! You can try again :)"
        }
    }

    private fun setupButton() {
        btn_analysis.setOnClickListener {
            navController.navigate(R.id.action_result_screen_to_analysis_screen)
        }

        btn_goHome.setOnClickListener {
            navController.navigate(R.id.action_result_screen_to_home_screen)
        }

    }

}
