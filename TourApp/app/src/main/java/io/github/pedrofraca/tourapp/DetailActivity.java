package io.github.pedrofraca.tourapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {

    public static final String ATTR_IMG="attr_img";
    public static final String ATTR_SHADOW="attr_img";

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
        collapsingToolbar.setTitle("PATATA");
    }
}
