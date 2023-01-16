package androidquiz.app.ui.play

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.play_fragment.*
import org.koin.android.ext.android.inject
import androidquiz.app.R
import androidquiz.app.data.database.entity.History
import androidquiz.app.util.custom_dialogs.customAlertDialog
import androidquiz.app.util.loading.LoadingState
import androidquiz.app.util.util_interface.BackPressed
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.play_layout.*
import kotlinx.android.synthetic.main.result_fragment.*
import kotlinx.android.synthetic.main.result_fragment.btn_goHome
import java.util.*


class PlayFragment : Fragment(), BackPressed {
    companion object {
        fun newInstance() = PlayFragment()
    }

    private val viewModel: PlayViewModel by inject()
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var navController: NavController
    private val handler = Handler()
    private var correctAnswers: Int = 0
    private lateinit var customDialog: AlertDialog
    private val CATEGORY_NAME: String = "Android"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.play_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        customDialog = customAlertDialog(
            requireContext(),
            "Are you sure you want to quit?",
            {
                viewModel.addHistory(
                    History( date = Date(),score = correctAnswers)
                )

                val direction =
                    PlayFragmentDirections.actionPlayScreenToResultScreen(
                        correctAnswers,
                        CATEGORY_NAME
                    )
                navController.navigate(direction)
            },
            "YES",
            {},
            "NO",
            false
        )

        setupRecyclerView()
        setupObservers()
        viewModel.getAllQuestions()

    }

    private fun updateAnswerTrigger(position: Int, optionSelected: Int) {
        try {
            viewModel.updateAnswer(questionAdapter.currentList[position].id, optionSelected);
        } catch (e: Exception) {}
    }


    private fun quizOptionClicked(position: Int, optionSelected: Int) {
        try {

            if (rv_question_list == null)
                return

            if (optionSelected == questionAdapter.currentList[position].correct)
                correctAnswers++

         else {
                viewModel.addHistory(
                    History( date = Date(),score = correctAnswers)
                )

                if (customDialog.isShowing)
                    customDialog.cancel()

            }
        } catch (e: Exception) {

        }
    }
    private fun btnNextClicked(position: Int){
        try{
            if (position != questionAdapter.currentList.size - 1) {
                handler.postDelayed({
                    rv_question_list.scrollToPosition(position + 1)
                }, 200)

            } else {
                val direction =
                    PlayFragmentDirections.actionPlayScreenToResultScreen(
                        correctAnswers,
                        CATEGORY_NAME
                    )

                viewModel.addHistory(
                    History( date = Date(),score = correctAnswers)
                )

                if (customDialog.isShowing)
                    customDialog.cancel()
                navController.navigate(direction)
            }
        }catch (e: Exception) {

        }
    }

    private fun btnQuitClicked() {
        customDialog.show()
    }
    private fun btnHomeClicked(){
        customAlertDialog(
            requireContext(),
            "Are you sure you want to go back home?",
            {
                viewModel.addHistory(
                    History( date = Date(),score = correctAnswers)
                )

                navController.navigate(R.id.action_play_screen_to_home_screen)
            },
            "YES",
            {},
            "NO",
            false
        ).show()
    }
    override fun backPressed() {
        customDialog.show()
    }

    private fun setupRecyclerView() {

        rv_question_list.setHasFixedSize(true)
        rv_question_list.layoutManager = object : LinearLayoutManager(context, HORIZONTAL, false) {
            override fun canScrollHorizontally() = false
        }
        questionAdapter =
            QuestionAdapter(viewModel,{ position: Int, optionSelected: Int ->
                quizOptionClicked(position, optionSelected)
            },{ position: Int, optionSelected: Int ->
                updateAnswerTrigger(position, optionSelected)
            }, {
                btnQuitClicked()
            },{
                btnHomeClicked()
            },{
                position:Int -> btnNextClicked(position)
            }, requireContext())
        rv_question_list.adapter = questionAdapter
    }

    private fun setupObservers() {
        viewModel.questionList.observe(viewLifecycleOwner, Observer { questionList ->
            if (questionList != null && questionList.isNotEmpty()) {
                questionAdapter.submitList(questionList)
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading.loadingID == 1) {
                when (loading.loadingState) {
                    LoadingState.COMPLETED -> {
                        lay_loading.visibility = View.GONE
                        viewModel.resetLoading()
                        rv_question_list.visibility = View.VISIBLE
                    }
                    LoadingState.LOADING -> {
                        lay_loading.visibility = View.VISIBLE
                        rv_question_list.visibility = View.GONE
                    }
                    LoadingState.IDLE -> {
                    }
                    LoadingState.ERROR -> {
                    }
                }
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
