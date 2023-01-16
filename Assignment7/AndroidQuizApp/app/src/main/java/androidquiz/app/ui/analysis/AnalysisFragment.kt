package androidquiz.app.ui.analysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import androidquiz.app.R
import kotlinx.android.synthetic.main.analysis_fragment.*

class AnalysisFragment : Fragment() {
    companion object {
        fun newInstance() = AnalysisFragment()
    }

    private val viewModel: AnalysisViewModel by inject()
    private lateinit var navController: NavController
    private lateinit var analysisAdapter: AnalysisAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getAllQuetions()
        return inflater.inflate(R.layout.analysis_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupRecyclerView()
        setupObservers()
        setupButton()
    }

    private fun setupButton() {
        btn_backHome.setOnClickListener {
            navController.navigate(R.id.action_analysis_screen_to_home_screen)
        }
    }

    private fun setupRecyclerView() {
        analysisAdapter = AnalysisAdapter(requireContext())
        rv_analysisList.layoutManager = LinearLayoutManager(requireContext())
        rv_analysisList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        rv_analysisList.adapter = analysisAdapter
    }

    private fun setupObservers() {
        viewModel.quesionList.observe(viewLifecycleOwner, Observer { quesionList ->
            if (quesionList != null && quesionList.isNotEmpty()) {
                analysisAdapter.submitList(quesionList)
            }
        })
    }
}
