package com.arioki.thekos.home;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arioki.thekos.ListKos;
import com.arioki.thekos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int int_items =2;

    public Tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.home_tab1,null);
        tabLayout = (TabLayout)x.findViewById(R.id.tab2_tab);
        viewPager = (ViewPager)x.findViewById(R.id.tab2_pager);

        //membuat adapter untuk pager
        viewPager.setAdapter(new Tab1.MyAdapter(getChildFragmentManager()));

        //setup
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter (FragmentManager fm){super(fm);}

        @Override
        public Fragment getItem(int posision){
            switch (posision){
                case 0 : return new Tab1_maps();
                case 1 : return new ListKos();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0 : return "Maps";
                case 1 : return "List";
            }
        return null;
        }
    }
}
