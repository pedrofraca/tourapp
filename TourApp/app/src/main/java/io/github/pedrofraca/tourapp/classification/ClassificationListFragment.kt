package io.github.pedrofraca.tourapp.classification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.pedrofraca.tourapp.R
import kotlinx.android.synthetic.main.fragment_clasification.*


class ClassificationListFragment : Fragment() {

    fun newInstance(dataSet: ArrayList<ClassificationModelParcelable?>?): ClassificationListFragment {
        val fragment = ClassificationListFragment()
        val args = Bundle()
        args.putParcelableArrayList(ATTR_DATASET, dataSet)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_clasification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        my_recycler_view.setHasFixedSize(true)
        my_recycler_view.layoutManager = LinearLayoutManager(activity)

        val args = arguments

        if (args != null) {
            val parcelableArrayList: ArrayList<ClassificationModelParcelable> = args.getParcelableArrayList(ATTR_DATASET)
                    ?: ArrayList(emptyList<ClassificationModelParcelable>())
            my_recycler_view.adapter = ClassificationAdapter(parcelableArrayList)
        }

    }

    companion object {
        private const val ATTR_DATASET = "ATTR_DATASET"
    }
}