package io.github.pedrofraca.tourapp.stage

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso
import io.github.pedrofraca.tourapp.R
import io.github.pedrofraca.tourapp.classification.ClassificationActivity.Companion.launch
import io.github.pedrofraca.tourapp.stage.StageDetailActivity

class StageDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val mTourImageView = findViewById<View>(R.id.tour_image) as ImageView
        ViewCompat.setTransitionName(mTourImageView, ATTR_IMG)
        Picasso.with(this).load(intent.getStringExtra(ATTR_IMG)).into(mTourImageView)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val collapsingToolbar = findViewById<View>(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        val stage: StageParcelable = intent.getParcelableExtra(ATTR_STAGE)
        collapsingToolbar.title = stage.name

        //ViewPager viewPager = (ViewPager) findViewById(R.id.activity_detail_view_pager);
        //viewPager.setAdapter(new TourStageImagesAdapter(stage.getImages(),stage.getDescription()));

//        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.activity_detail_page_indicator);
//        circlePageIndicator.setFillColor(getResources().getColor(R.color.color_primary_dark));
//        circlePageIndicator.setViewPager(viewPager);
        val mStageWinner = findViewById<View>(R.id.activity_detail_stage_winner) as TextView
        mStageWinner.text = stage.winner
        val mStageDetails = findViewById<View>(R.id.activity_detail_stage_details) as TextView
        if (stage.completed()) {
            mStageDetails.text = Html.fromHtml(getString(R.string.details_info, stage.km,
                    stage.averageSpeed,
                    stage.leader))
        } else {
            mStageDetails.text = getString(R.string.details_info_simple, stage.km)
        }
        val clasification = findViewById<View>(R.id.activity_detail_clasification_button)
        if (!stage.completed()) {
            clasification.visibility = View.GONE
        }
        clasification.setOnClickListener { launch(this@StageDetailActivity, stage.stage) }

//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
    }

    companion object {
        const val ATTR_IMG = "attr_img"
        const val ATTR_STAGE = "attr_stage"
    }
}