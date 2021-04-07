package com.drillgil.android.tourguide.Home.UnitedStates;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment1;
import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment2;
import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment3;


public class UnitedStatesFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;

    public UnitedStatesFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                UnitedStatesFragment1 tab1 = new UnitedStatesFragment1();
                return tab1;
            case 1:
                UnitedStatesFragment2 tab2 = new UnitedStatesFragment2();
                return tab2;
            case 2:
                UnitedStatesFragment3 tab3 = new UnitedStatesFragment3();
                return tab3;

        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
