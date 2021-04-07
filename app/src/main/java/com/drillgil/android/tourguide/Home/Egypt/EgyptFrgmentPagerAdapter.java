package com.drillgil.android.tourguide.Home.Egypt;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class EgyptFrgmentPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;


    public EgyptFrgmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                EgyptFragment1 tab1 = new EgyptFragment1();
                return tab1;
            case 1:
                EgyptFragment2 tab2 = new EgyptFragment2();
                return tab2;
            case 2:
                EgyptFragment3 tab3 = new EgyptFragment3();
                return tab3;

        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
