package io.github.pedrofraca.tourapp.stage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.pedrofraca.tourapp.R
import kotlinx.android.synthetic.main.fragment_stages.*

class StagesFragment : Fragment() {
    private lateinit var mAdapter: StageAdapter
    private lateinit var viewModel: StagesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_stages, container, false)
        viewModel = ViewModelProviders.of(this,
                StagesViewModelFactory(requireContext())).get(StagesViewModel::class.java)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = StageAdapter(emptyList(), requireActivity())
        my_recycler_view.setHasFixedSize(true)
        my_recycler_view.layoutManager = LinearLayoutManager(activity)
        my_recycler_view.adapter = mAdapter

        viewModel.stages().observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
            stages_loading.visibility = View.GONE
        })

        viewModel.error().observe(viewLifecycleOwner, Observer { throwable: Throwable -> showErrorMessage(throwable.message) })
    }

    private fun showErrorMessage(error: String?) {
        stages_error_message.visibility = View.VISIBLE
        activity_main_text_message.text = error
    }

    companion object {
        const val TAG = "STAGES"
    }
}