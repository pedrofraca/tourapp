package io.github.pedrofraca.tourapp.adapter;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.pedrofraca.tourapp.activity.FullScreenImagesActivity;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by pedrofraca on 14/07/15.
 */
public class TourStageImagesAdapter extends PagerAdapter {

    private ArrayList<String> mImgsURL;
    private String mTitleForFullscreen;
    private boolean mShouldOpenFullScreen = false;

    public TourStageImagesAdapter(ArrayList<String> imgsUrl){
        mImgsURL = imgsUrl;
        mShouldOpenFullScreen = false;
    }

    public TourStageImagesAdapter(ArrayList<String> imgsUrl,String titleForFullScreenActivity){
        mImgsURL = imgsUrl;
        mTitleForFullscreen = titleForFullScreenActivity;
        mShouldOpenFullScreen = true;
    }

    @Override
    public int getCount() {
        return mImgsURL.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public View instantiateItem(final ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        Picasso.with(container.getContext()).load(mImgsURL.get(position)).into(photoView);
        if(mShouldOpenFullScreen){
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float v, float v1) {
                    FullScreenImagesActivity.launch(container.getContext(), mImgsURL, mTitleForFullscreen);
                }
            });
        }
        // Now just add PhotoView to ViewPager and return it
        container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
        return photoView;
    }

}
