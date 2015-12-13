package com.dev407.officedictionary2;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

import com.dev407.officedictionary2.fragments.PopularFragment;
import com.dev407.officedictionary2.fragments.TrendingFragment;
import com.dev407.officedictionary2.fragments.UpvotedFragment;
import com.dev407.officedictionary2.models.dummy.DummyContent;
import com.dev407.officedictionary2.models.dummy.DummyContentPopular;
import com.dev407.officedictionary2.models.dummy.DummyContentUpvoted;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity  implements TrendingFragment.OnListFragmentInteractionListener, PopularFragment.OnListFragmentInteractionListener, UpvotedFragment.OnListFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private static final String TAG = "DashboardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        toast("Dummy Trending Content got" + item.id + item.content + item.details);
    }

    private void toast(String text) {
        Toast.makeText(this,
                String.format("Item clicked: %s", text), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onListFragmentInteraction(DummyContentPopular.DummyItem item) {
        toast("Dummy Popular Content got" + item.id + item.content + item.details);
    }

    @Override
    public void onListFragmentInteraction(DummyContentUpvoted.DummyItem item) {
        toast("Dummy Upvoted Content got" + item.id + item.content + item.details);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return TrendingFragment.newInstance(position + 1);
            Log.d(TAG,"postion" + position);
            if(position == 0)
                return TrendingFragment.newInstance(1, position + 1);
            else if(position == 1)
                return PopularFragment.newInstance(1, position + 1);
            else
                return UpvotedFragment.newInstance(1, position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Trending";
                case 1:
                    return "Popular";
                case 2:
                    return "Upvoted";
            }
            return null;
        }
    }
}
