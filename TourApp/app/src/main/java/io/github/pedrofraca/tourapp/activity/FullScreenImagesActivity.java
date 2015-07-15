package io.github.pedrofraca.tourapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import io.github.pedrofraca.tourapp.R;
import io.github.pedrofraca.tourapp.adapter.TourStageImagesAdapter;

/**
 * Created by pedrofraca on 14/07/15.
 */
public class FullScreenImagesActivity extends AppCompatActivity {

    public static final String ATTR_IMAGES="ATTR_IMAGES";
    public static final String ATTR_TITLE="ATTR_TITLE";

    public static void launch(Context callerActivity,ArrayList<String> images,String title){
        Intent intent = new Intent(callerActivity,FullScreenImagesActivity.class);
        intent.putStringArrayListExtra(ATTR_IMAGES,images);
        intent.putExtra(ATTR_TITLE, title);
        callerActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_images);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getIntent().getStringExtra(ATTR_TITLE));
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra(ATTR_IMAGES);
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_fullscreen_view_pager);
        viewPager.setAdapter(new TourStageImagesAdapter(stringArrayListExtra));

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.activity_fullscreen_page_indicator);
        circlePageIndicator.setFillColor(getResources().getColor(R.color.text_dark_gray));
        circlePageIndicator.setStrokeColor(getResources().getColor(R.color.text_dark_gray));
        circlePageIndicator.setViewPager(viewPager);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
}
