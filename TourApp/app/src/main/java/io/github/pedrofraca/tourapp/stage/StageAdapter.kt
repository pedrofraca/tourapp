package io.github.pedrofraca.tourapp.stage

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.pedrofraca.domain.model.StageModel
import io.github.pedrofraca.tourapp.R
import io.github.pedrofraca.tourapp.activity.DetailActivity


class StageAdapter // Provide a suitable constructor (depends on the kind of dataset)
(private val mDataset: List<StageParcelable>, private val mActivity: Activity) : RecyclerView.Adapter<StageAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var mLeaderBoardView: View
        var mStageName: TextView
        var mStageWinner: TextView
        var mStageLeader: TextView
        var mStageKm: TextView
        var mCardView: CardView

        // each data item is just a string in this case
        var mStageImageView: ImageView
        var mStageImageCompletedIcon: ImageView

        init {
            mStageImageView = v.findViewById<View>(R.id.item_tour_stage_image) as ImageView
            mStageImageCompletedIcon = v.findViewById<View>(R.id.item_tour_stage_done_icon) as ImageView
            mStageName = v.findViewById<View>(R.id.item_tour_stage_title) as TextView
            mStageWinner = v.findViewById<View>(R.id.item_tour_stage_winner) as TextView
            mStageLeader = v.findViewById<View>(R.id.item_tour_stage_leader) as TextView
            mStageKm = v.findViewById<View>(R.id.item_tour_stage_km) as TextView
            mLeaderBoardView = v.findViewById(R.id.item_tour_leader_board)
            mCardView = v.findViewById<View>(R.id.item_tour_card_view) as CardView
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tour_stage, parent, false)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tourStage = mDataset[position]
        holder.mStageName.text = tourStage.name
        holder.mStageKm.text = mActivity.getString(R.string.km_string, tourStage.km)
        //        holder.mCardView.setTag(position);
        Picasso.with(mActivity).load(tourStage.imgUrl).into(holder.mStageImageView)

        if (!tourStage.completed()) {
            holder.mLeaderBoardView.visibility = View.GONE
            holder.mStageImageCompletedIcon.visibility = View.GONE
        } else {
            holder.mStageImageCompletedIcon.visibility = View.VISIBLE
            holder.mLeaderBoardView.visibility = View.VISIBLE
            holder.mStageLeader.text = mActivity.getString(R.string.stage_leader_string, tourStage.leader)
            holder.mStageWinner.text = mActivity.getString(R.string.stage_winner_string, tourStage.winner)
        }
        holder.mCardView.tag = position
        holder.mCardView.setOnClickListener {
            val intent = Intent(mActivity, DetailActivity::class.java)
            // create the transition animation - the images in the layouts
            // of both activities are defined with android:transitionName="robot"
            // create the transition animation - the images in the layouts
            // of both activities are defined with android:transitionName="robot"
            val p1 = androidx.core.util.Pair.create(holder.mStageImageView as View, DetailActivity.ATTR_IMG)
            val options: ActivityOptionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(mActivity, p1)

            val position : Int = it.tag as Int
            intent.putExtra(DetailActivity.ATTR_IMG, mDataset[position].imgUrl)
            intent.putExtra(DetailActivity.ATTR_STAGE, mDataset[position])
            mActivity.startActivity(intent, options.toBundle())
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset.size
    }

}