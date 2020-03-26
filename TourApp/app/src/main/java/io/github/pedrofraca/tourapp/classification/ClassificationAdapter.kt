package io.github.pedrofraca.tourapp.classification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.pedrofraca.tourapp.R

class ClassificationAdapter(private val dataset: List<ClassificationModelParcelable>) : RecyclerView.Adapter<ClassificationAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var classificationTextView = v.findViewById<View>(R.id.item_tour_clasification_title) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tour_clasification, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val classification = dataset[position]
        val country = if(classification.country.isNullOrEmpty()) {
            ""
        } else {
            " (${classification.country})"
        }
        holder.classificationTextView.text = "${classification.pos} ${classification.rider?:""}$country ${classification.team} ${classification.time}"
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}