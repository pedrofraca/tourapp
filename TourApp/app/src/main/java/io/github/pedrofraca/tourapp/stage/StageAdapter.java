package io.github.pedrofraca.tourapp.stage;

import android.app.Activity;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.data.model.Stage;

import java.util.List;

import io.github.pedrofraca.tourapp.R;

public class StageAdapter extends RecyclerView.Adapter<StageAdapter.ViewHolder> {

    private List<Stage> mDataset;
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
        public CardView mCardView;
        // each data item is just a string in this case
        public ImageView mStageImageView;
        public ImageView mStageImageCompletedIcon;
        public ViewHolder(View v) {
            super(v);
            mStageImageView = (ImageView) v.findViewById(R.id.item_tour_stage_image);
            mStageImageCompletedIcon = (ImageView) v.findViewById(R.id.item_tour_stage_done_icon);
            mStageName = (TextView) v.findViewById(R.id.item_tour_stage_title);
            mStageWinner= (TextView) v.findViewById(R.id.item_tour_stage_winner);
            mStageLeader= (TextView) v.findViewById(R.id.item_tour_stage_leader);
            mStageKm=(TextView) v.findViewById(R.id.item_tour_stage_km);
            mLeaderBoardView = v.findViewById(R.id.item_tour_leader_board);
            mCardView= (CardView) v.findViewById(R.id.item_tour_card_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public StageAdapter(List<Stage> myDataset, Activity activity) {
        mDataset = myDataset;
        mActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tour_stage, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Stage tourStage = mDataset.get(position);
        holder.mStageName.setText(tourStage.getName());
        holder.mStageKm.setText(mActivity.getString(R.string.km_string, tourStage.getKm()));
//        holder.mCardView.setTag(position);
//        Picasso.with(mActivity).load("http://tourscraping.appspot.com/image?stage="+(position+1)).into(holder.mStageImageView);
        if(!tourStage.isCompleted()){
            holder.mLeaderBoardView.setVisibility(View.GONE);
            holder.mStageImageCompletedIcon.setVisibility(View.GONE);
        } else {
            holder.mStageImageCompletedIcon.setVisibility(View.VISIBLE);
            holder.mLeaderBoardView.setVisibility(View.VISIBLE);
            holder.mStageLeader.setText(mActivity.getString(R.string.stage_leader_string,tourStage.getLeader()));
            holder.mStageWinner.setText(mActivity.getString(R.string.stage_winner_string, tourStage.getWinner()));
        }
//        holder.mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mActivity, DetailActivity.class);
//                // create the transition animation - the images in the layouts
//                // of both activities are defined with android:transitionName="robot"
//                Pair<View, String> p1 = Pair.create((View)holder.mStageImageView, DetailActivity.ATTR_IMG);
//                ActivityOptionsCompat options = ActivityOptionsCompat
//                        .makeSceneTransitionAnimation(mActivity,p1);
//                // start the new activity
//                int position = (int) view.getTag();
//                intent.putExtra(DetailActivity.ATTR_IMG,"http://tourscraping.appspot.com/image?stage="+(position+1));
//                intent.putExtra(DetailActivity.ATTR_STAGE, mDataset.get(position));
//                mActivity.startActivity(intent, options.toBundle());
//            }
//        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}