package io.github.pedrofraca.tourapp.stage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.pedrofraca.tourapp.databinding.FragmentStagesBinding

class StagesFragment : Fragment() {
    private lateinit var mAdapter: StageAdapter
    private val viewModel: StagesViewModel by viewModels { StagesViewModelFactory(requireContext()) }
    private var binding: FragmentStagesBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentStagesBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = StageAdapter(emptyList(), requireActivity())
        binding?.myRecyclerView?.setHasFixedSize(true)
        binding?.myRecyclerView?.layoutManager = LinearLayoutManager(activity)
        binding?.myRecyclerView?.adapter = mAdapter

        viewModel.stages().observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
            binding?.stagesLoading?.visibility = View.GONE
        })

        viewModel.error().observe(viewLifecycleOwner, Observer { throwable: Throwable -> showErrorMessage(throwable.message) })
    }

    private fun showErrorMessage(error: String?) {
        binding?.stagesErrorMessage?.visibility = View.VISIBLE
        binding?.activityMainTextMessage?.text = error
    }

    companion object {
        const val TAG = "STAGES"
    }
}