package androidquiz.app.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.home_fragment.*
import androidquiz.app.R
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }
    private val viewModel: HomeViewModel by inject()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupObservers()
        setupButtons()
        viewModel.getAllQuestions()
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() {

    }

    private fun setupButtons() {
        btn_singleplayer.setOnClickListener {
            navController.navigate(R.id.action_home_screen_to_quizz_screen)
        }

    }
}
