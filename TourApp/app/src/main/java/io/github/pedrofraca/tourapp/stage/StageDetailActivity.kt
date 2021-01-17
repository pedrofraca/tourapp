package io.github.pedrofraca.tourapp.stage

import android.os.Bundle
import android.text.Html
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
        Picasso.with(this).load(stage.profileImgUrl).into(stageProfileImageView)

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

    }

    companion object {
        const val ATTR_IMG = "attr_img"
        const val ATTR_STAGE = "attr_stage"
    }
}