package io.github.pedrofraca.tourapp.classification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.pedrofraca.tourapp.databinding.FragmentClasificationBinding


class ClassificationListFragment : Fragment() {

    private var binding: FragmentClasificationBinding? = null

    fun newInstance(dataSet: ArrayList<ClassificationModelParcelable?>?): ClassificationListFragment {
        val fragment = ClassificationListFragment()
        val args = Bundle()
        args.putParcelableArrayList(ATTR_DATASET, dataSet)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentClasificationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.myRecyclerView?.setHasFixedSize(true)
        binding?.myRecyclerView?.layoutManager = LinearLayoutManager(activity)

        val args = arguments

        if (args != null) {
            val parcelableArrayList: ArrayList<ClassificationModelParcelable> = args.getParcelableArrayList(ATTR_DATASET)
                    ?: ArrayList(emptyList<ClassificationModelParcelable>())
            binding?.myRecyclerView?.adapter = ClassificationAdapter(parcelableArrayList)
        }

    }

    companion object {
        private const val ATTR_DATASET = "ATTR_DATASET"
    }
}