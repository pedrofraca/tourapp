package io.github.pedrofraca.tourapp.images

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import io.github.pedrofraca.tourapp.images.FullScreenImagesActivity.Companion.launch
import uk.co.senab.photoview.PhotoView
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener

/**
 * Created by pedrofraca on 14/07/15.
 */
class StageImagesAdapter : PagerAdapter {
    private var mImgsURL: List<String>
    private var mTitleForFullscreen: String? = null
    private var mShouldOpenFullScreen = false

    constructor(imgsUrl: List<String>) {
        mImgsURL = imgsUrl
        mShouldOpenFullScreen = false
    }

    constructor(imgsUrl: List<String>, titleForFullScreenActivity: String?) {
        mImgsURL = imgsUrl
        mTitleForFullscreen = titleForFullScreenActivity
        mShouldOpenFullScreen = true
    }

    override fun getCount(): Int {
        return mImgsURL.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val photoView = PhotoView(container.context)
        Picasso.with(container.context).load(mImgsURL[position]).into(photoView)
        if (mShouldOpenFullScreen) {
            photoView.onPhotoTapListener = OnPhotoTapListener { view, v, v1 -> launch(container.context, mImgsURL, mTitleForFullscreen) }
        }
        // Now just add PhotoView to ViewPager and return it
        container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT)
        return photoView
    }
}