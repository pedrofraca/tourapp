package io.github.pedrofraca.tourapp.images

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.viewpagerindicator.CirclePageIndicator
import io.github.pedrofraca.tourapp.R
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by pedrofraca on 14/07/15.
 */
class FullScreenImagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_images)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(ATTR_TITLE)
        val stringArrayListExtra = intent.getStringArrayListExtra(ATTR_IMAGES)
        val viewPager = findViewById<View>(R.id.activity_fullscreen_view_pager) as ViewPager
        viewPager.adapter = stringArrayListExtra?.let { StageImagesAdapter(it) }
        val circlePageIndicator = findViewById<View>(R.id.activity_fullscreen_page_indicator) as CirclePageIndicator
        circlePageIndicator.fillColor = resources.getColor(R.color.text_dark_gray)
        circlePageIndicator.strokeColor = resources.getColor(R.color.text_dark_gray)
        circlePageIndicator.setViewPager(viewPager)


//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
    }

    companion object {
        const val ATTR_IMAGES = "ATTR_IMAGES"
        const val ATTR_TITLE = "ATTR_TITLE"
        @JvmStatic
        fun launch(callerActivity: Context, images: List<String>, title: String?) {
            val intent = Intent(callerActivity, FullScreenImagesActivity::class.java)
            intent.putStringArrayListExtra(ATTR_IMAGES, ArrayList(images))
            intent.putExtra(ATTR_TITLE, title)
            callerActivity.startActivity(intent)
        }
    }
}