package androidquiz.app.ui.history

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
import kotlinx.android.synthetic.main.history_fragment.*
import org.koin.android.ext.android.inject
import androidquiz.app.R

class HistoryFragment : Fragment() {
    companion object {
        fun newInstance() = HistoryFragment()
    }

    private val viewModel: HistoryViewModel by inject()
    private lateinit var navController: NavController
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.getAllHistory()
        setupRecyclerView()
        setupObservers()
        setupButton()
    }

    private fun setupButton() {
        btn_backhome.setOnClickListener {
            navController.navigate(R.id.action_history_screen_to_home_screen)
        }
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter(requireContext())
        rv_historyList.layoutManager = LinearLayoutManager(requireContext())
        rv_historyList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        rv_historyList.adapter = historyAdapter
    }

    private fun setupObservers() {
        viewModel.historyList.observe(viewLifecycleOwner, Observer { historyList ->
            if (historyList != null && historyList.isNotEmpty()) {
                historyAdapter.submitList(historyList.sortedWith(Comparator { o1, o2 ->
                    return@Comparator when {
                        o1.date.after(o2.date) -> -1
                        o1.date.before(o2.date) -> 1
                        else -> 0
                    }
                }).toList())
            }
        })
    }
}
