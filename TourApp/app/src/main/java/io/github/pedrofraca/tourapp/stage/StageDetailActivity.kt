package io.github.pedrofraca.tourapp.stage

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso
import com.viewpagerindicator.CirclePageIndicator
import io.github.pedrofraca.tourapp.R
import io.github.pedrofraca.tourapp.classification.ClassificationActivity.Companion.launch

class StageDetailActivity : AppCompatActivity() {
    private lateinit var stage: StageParcelable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val mTourImageView = findViewById<View>(R.id.tour_image) as ImageView
        ViewCompat.setTransitionName(mTourImageView, ATTR_IMG)
        Picasso.with(this).load(intent.getStringExtra(ATTR_IMG)).into(mTourImageView)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val collapsingToolbar = findViewById<View>(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        stage = intent.getParcelableExtra(ATTR_STAGE)
        collapsingToolbar.title = stage.name

        val stageProfileImageView = findViewById<ImageView>(R.id.stage_profile)

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
        val classification = findViewById<View>(R.id.activity_detail_clasification_button)
        if (!stage.completed()) {
            classification.visibility = View.GONE
        }
        classification.setOnClickListener { launch(this@StageDetailActivity, stage.stage) }

//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
    }

//    override fun supportShouldUpRecreateTask(targetIntent: Intent): Boolean {
//        return true
//    }
//
//    override fun onPrepareSupportNavigateUpTaskStack(builder: TaskStackBuilder) {
//        super.onPrepareSupportNavigateUpTaskStack(builder)
////        val albumId = intent.getLongExtra(TrackActivity.EXTRA_ALBUM_ID, -1L)
////        val albumIntent = AlbumActivity.create(this, albumId)
//        builder.editIntentAt(builder.intentCount - 1)?.putExtra(ATTR_STAGE, stage)
//        Log.d("patata", "on save")
//    }
//
//    override fun getSupportParentActivityIntent(): Intent? {
//        val intent = Intent()
//        intent.putExtra(ATTR_STAGE, stage)
//        return intent
//    }





    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.d("patata", "on save")
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d("patata", "on restore")
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        const val ATTR_IMG = "attr_img"
        const val ATTR_STAGE = "attr_stage"
    }
}