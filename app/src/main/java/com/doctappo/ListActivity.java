package com.doctappo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import fragments.ListFragment;
import fragments.MapFragment;
import models.ActiveModels;
import util.CommonClass;

public class ListActivity extends CommonActivity {

    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        allowBack();

        if (!common.containKeyInSession("nearby_enable")){
            common.setSessionBool("nearby_enable",true);
        }
        if (getIntent().getExtras().containsKey("search")){
            setHeaderTitle(getIntent().getExtras().getString("search"));
        }else if (ActiveModels.CATEGORY_MODEL != null){
            setHeaderTitle(ActiveModels.CATEGORY_MODEL.getTitle());
        }
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                    }
                });

        final ActionBar actionBar = getSupportActionBar();
        // Specify that tabs should be displayed in the action bar.
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();

    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_list);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_map);

    }
    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        private String tabTitles[] = new String[] { getString(R.string.tab_list), getString(R.string.tab_map)};
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            if (i == 0){
                fragment = new ListFragment();
            }else if(i == 1){
                fragment = new MapFragment();
            }
            if(fragment!=null) {
                Bundle args = new Bundle();
                if (getIntent().getExtras().containsKey("cat_id")) {
                    args.putString("cat_id", getIntent().getExtras().getString("cat_id"));
                }
                if (getIntent().getExtras().containsKey("search")){
                    args.putString("search",getIntent().getExtras().getString("search"));
                }
                if (getIntent().getExtras().containsKey("lat")){
                    args.putString("lat",getIntent().getExtras().getString("lat"));
                }
                if (getIntent().getExtras().containsKey("lon")){
                    args.putString("lon",getIntent().getExtras().getString("lon"));
                }
                if (getIntent().getExtras().containsKey("locality")){
                    args.putString("locality",getIntent().getExtras().getString("locality"));
                }
                if (getIntent().getExtras().containsKey("locality_id")){
                    args.putString("locality_id",getIntent().getExtras().getString("locality_id"));
                }
                fragment.setArguments(args);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
