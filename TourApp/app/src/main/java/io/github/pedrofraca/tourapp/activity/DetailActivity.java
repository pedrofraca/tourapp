package io.github.pedrofraca.tourapp.activity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import io.github.pedrofraca.tourapp.R;
import io.github.pedrofraca.tourapp.classification.ClassificationActivity;
import io.github.pedrofraca.tourapp.stage.StageParcelable;


public class DetailActivity extends AppCompatActivity {

    public static final String ATTR_IMG="attr_img";
    public static final String ATTR_STAGE = "attr_stage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView mTourImageView = (ImageView) findViewById(R.id.tour_image);

        ViewCompat.setTransitionName(mTourImageView, ATTR_IMG);

        Picasso.with(this).load(getIntent().getStringExtra(ATTR_IMG)).into(mTourImageView);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);


        final StageParcelable stage = getIntent().getParcelableExtra(ATTR_STAGE);
        collapsingToolbar.setTitle(stage.getName());

        //ViewPager viewPager = (ViewPager) findViewById(R.id.activity_detail_view_pager);
        //viewPager.setAdapter(new TourStageImagesAdapter(stage.getImages(),stage.getDescription()));

//        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.activity_detail_page_indicator);
//        circlePageIndicator.setFillColor(getResources().getColor(R.color.color_primary_dark));
//        circlePageIndicator.setViewPager(viewPager);

        TextView mStageWinner = (TextView) findViewById(R.id.activity_detail_stage_winner);
        mStageWinner.setText(stage.getWinner());

        TextView mStageDetails = (TextView) findViewById(R.id.activity_detail_stage_details);

        if(stage.completed()){
            mStageDetails.setText(Html.fromHtml(getString(R.string.details_info, stage.getKm(),
                    stage.getAverageSpeed(),
                    stage.getLeader())));
        } else {
            mStageDetails.setText(getString(R.string.details_info_simple, stage.getKm()));
        }

        View clasification = findViewById(R.id.activity_detail_clasification_button);
        if(!stage.completed()){
            clasification.setVisibility(View.GONE);
        }
        clasification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassificationActivity.launch(DetailActivity.this, stage.getStage());
            }
        });

//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("restore","restore");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
