package io.github.pedrofraca.tourapp.classification

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import io.github.pedrofraca.tourapp.R
import io.github.pedrofraca.tourapp.stage.StageDetailActivity

class ClassificationActivity : AppCompatActivity() {
    private lateinit var viewModel: ClassificationViewModel
    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clasification)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)
        mViewPager = findViewById<View>(R.id.viewpager) as ViewPager

        viewModel = ViewModelProviders.of(this,
                ClassificationViewModelFactory(this)).get(ClassificationViewModel::class.java)

        viewModel.getClassificationForStage(intent.extras?.getInt(ATTR_STAGE).toString())
                .observe(this, androidx.lifecycle.Observer {
                    val adapter = Adapter(supportFragmentManager);
                    adapter.addFragment( ClassificationListFragment().newInstance(ArrayList(it.stage)), getString(R.string.stage));
                    adapter.addFragment( ClassificationListFragment().newInstance(ArrayList(it.general)), getString(R.string.general));
                    adapter.addFragment( ClassificationListFragment().newInstance(ArrayList(it.mountain)), getString(R.string.mountain));
                    adapter.addFragment( ClassificationListFragment().newInstance(ArrayList(it.regularity)), getString(R.string.regularity));
                    adapter.addFragment( ClassificationListFragment().newInstance(ArrayList(it.team)), getString(R.string.team));

                    mViewPager?.adapter = adapter;

                    val tabLayout = findViewById<TabLayout>(R.id.tabs);
                    tabLayout.setupWithViewPager(mViewPager);

                })
    }

    override fun getSupportParentActivityIntent(): Intent? {
        val intent = Intent()
        intent.setClass(this, StageDetailActivity.javaClass)
        intent.putExtra(StageDetailActivity.ATTR_STAGE, "stage")
        return intent
    }

    internal class Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mFragments: MutableList<Fragment> = ArrayList()
        private val mFragmentTitles: MutableList<String> = ArrayList()

        fun addFragment(fragment: Fragment, title: String) {
            mFragments.add(fragment)
            mFragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitles[position]
        }
    }

    companion object {
        const val ATTR_STAGE = "attr_stage"
        @JvmStatic
        fun launch(callerActivity: Activity, stage: Int?) {
            val intent = Intent(callerActivity, ClassificationActivity::class.java)
            intent.putExtra(ATTR_STAGE, stage)
            callerActivity.startActivity(intent)
        }
    }
}