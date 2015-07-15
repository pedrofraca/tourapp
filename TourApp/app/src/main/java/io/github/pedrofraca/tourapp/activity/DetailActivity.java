package io.github.pedrofraca.tourapp.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import io.github.pedrofraca.tourapp.R;
import io.github.pedrofraca.tourapp.adapter.TourStageImagesAdapter;
import io.github.pedrofraca.tourapp.model.TourStage;


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


        final TourStage stage = getIntent().getParcelableExtra(ATTR_STAGE);
        collapsingToolbar.setTitle(stage.getStartFinish());

        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_detail_view_pager);
        viewPager.setAdapter(new TourStageImagesAdapter(stage.getImages(),stage.getStartFinish()));

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.activity_detail_page_indicator);
        circlePageIndicator.setFillColor(getResources().getColor(R.color.color_primary_dark));
        circlePageIndicator.setViewPager(viewPager);

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
        clasification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClasificationActivity.launch(DetailActivity.this);
            }
        });
    }
}