package io.github.pedrofraca.tourapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.pedrofraca.tourapp.model.TourStage;

public class TourStageAdapter extends RecyclerView.Adapter<TourStageAdapter.ViewHolder> {

    private List<TourStage> mDataset;
    private Activity mActivity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mLeaderBoardView;
        public TextView mStageName;
        public TextView mStageWinner;
        public TextView mStageLeader;
        public TextView mStageKm;
        public View mShadowView;
        // each data item is just a string in this case
        public ImageView mStageImageView;
        public ViewHolder(View v) {
            super(v);
            mStageImageView = (ImageView) v.findViewById(R.id.item_tour_stage_image);
            mStageName = (TextView) v.findViewById(R.id.item_tour_stage_title);
            mStageWinner= (TextView) v.findViewById(R.id.item_tour_stage_winner);
            mStageLeader= (TextView) v.findViewById(R.id.item_tour_stage_leader);
            mStageKm=(TextView) v.findViewById(R.id.item_tour_stage_km);
            mShadowView = v.findViewById(R.id.item_tour_shadow);
            mLeaderBoardView = v.findViewById(R.id.item_tour_leader_board);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TourStageAdapter(List<TourStage> myDataset, Activity activity) {
        mDataset = myDataset;
        mActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TourStageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tour_stage, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TourStage tourStage = mDataset.get(position);
        holder.mStageName.setText(tourStage.getDescription());
        holder.mStageKm.setText(mActivity.getString(R.string.km_string, tourStage.getKm()));
        Picasso.with(mActivity).load("http://tourscraping.appspot.com/image?stage="+(position+1)).into(holder.mStageImageView);
        if(!tourStage.completed()){
            holder.mLeaderBoardView.setVisibility(View.GONE);
            holder.mShadowView.setVisibility(View.GONE);
        } else {
            holder.mShadowView.setVisibility(View.VISIBLE);
            holder.mLeaderBoardView.setVisibility(View.VISIBLE);
            holder.mStageLeader.setText(mActivity.getString(R.string.stage_leader_string,tourStage.getLeader()));
            holder.mStageWinner.setText(mActivity.getString(R.string.stage_winner_string, tourStage.getWinner()));
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}