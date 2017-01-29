package com.arioki.thekos.home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arioki.thekos.area.area_tab1;
import com.arioki.thekos.area.area_tab2;
import com.arioki.thekos.R;
import com.arioki.thekos.conf.ListAreaItem;

public class Tab2_Area extends AppCompatActivity  {

    private ListAreaItem item;
    public static final String KEY_ITEM = "item";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2_area);

        item = (ListAreaItem) getIntent().getSerializableExtra(KEY_ITEM);

        //deklarasi tab
        toolbar = (Toolbar) findViewById(R.id.area_toolbar);
        tabLayout = (TabLayout) findViewById(R.id.area_tabs);
        viewPager = (ViewPager) findViewById(R.id.area_viewPager);

        setupToolbar();
        init();
    }

    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }

    private void init(){
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 2;
        final Context context;

        public ViewPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position){
                case 0:
                    fragment = area_tab1.newInstance();
                    break;
                case 1:
                    fragment = area_tab2.newInstance();
                    break;
            }

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Tab1";
                case 1:
                    return "Tab 6";

            }

            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else if (getFragmentManager().getBackStackEntryCount() == 1) {
            moveTaskToBack(false);
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                if (getFragmentManager().getBackStackEntryCount() == 0) {
                    super.onBackPressed();
                } else if(getFragmentManager().getBackStackEntryCount() == 1){
                    moveTaskToBack(false);
                }else {
                    getFragmentManager().popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
