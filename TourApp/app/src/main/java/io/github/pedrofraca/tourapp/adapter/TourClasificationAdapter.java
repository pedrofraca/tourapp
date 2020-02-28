package io.github.pedrofraca.tourapp.adapter;

import android.app.Activity;
import android.os.Parcelable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.pedrofraca.tourapp.R;

public class TourClasificationAdapter extends RecyclerView.Adapter<TourClasificationAdapter.ViewHolder> {

    private List<? extends Parcelable> mDataset;
    private Activity mActivity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mClasificationText;
        public ViewHolder(View v) {
            super(v);
            mClasificationText=(TextView) v.findViewById(R.id.item_tour_clasification_title);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TourClasificationAdapter(List<? extends Parcelable> myDataset, Activity activity) {
        mDataset = myDataset;
        mActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TourClasificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tour_clasification, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Object tourStageClasification = mDataset.get(position);
        holder.mClasificationText.setText(tourStageClasification.toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}