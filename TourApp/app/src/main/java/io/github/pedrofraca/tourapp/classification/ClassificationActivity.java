package io.github.pedrofraca.tourapp.classification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import io.github.pedrofraca.tourapp.R;

public class ClassificationActivity extends AppCompatActivity {
    public static final String ATTR_STAGE = "attr_stage";
    private ViewPager mViewPager;

    public static void launch(Activity callerActivity, String stage) {
        Intent intent = new Intent(callerActivity, ClassificationActivity.class);
        intent.putExtra(ATTR_STAGE, stage);
        callerActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasification);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        //new GetClasificationAsyncTask(this,getIntent().getExtras().getString(ATTR_STAGE)).execute();
    }
//
//    private void setupViewPager(ViewPager viewPager, TourStageClasification clasification) {
//        Adapter adapter = new Adapter(getSupportFragmentManager());
//        adapter.addFragment(new ClasificationListFragment().newInstance(clasification.getStage()), getString(R.string.stage));
//        adapter.addFragment(new ClasificationListFragment().newInstance(clasification.getGeneral()), getString(R.string.general));
//        adapter.addFragment(new ClasificationListFragment().newInstance(clasification.getMountain()), getString(R.string.mountain));
//        adapter.addFragment(new ClasificationListFragment().newInstance(clasification.getRegularity()), getString(R.string.regularity));
//        adapter.addFragment(new ClasificationListFragment().newInstance(clasification.getTeam()), getString(R.string.team));
//        viewPager.setAdapter(adapter);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);
//    }
//
//    @Override
//    public void onClasificationReceived(TourStageClasification clasification) {
//        setupViewPager(mViewPager,clasification);
//    }
//
//    @Override
//    public void onClasificationError(Exception error) {
//        error.printStackTrace();
//    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
