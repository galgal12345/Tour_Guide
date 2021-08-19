package com.drillgil.android.tourguide.Home.Greece;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment1;
import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment2;
import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment3;

import org.jetbrains.annotations.NotNull;

public class GreeceFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;


    public GreeceFragmentPagerAdapter(FragmentManager supportFragmentManager, int i) {
        super(supportFragmentManager, i);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                GreeceFragment1 tab1 = new GreeceFragment1();
                return tab1;
            case 1:
                GreeceFragment2 tab2 = new GreeceFragment2();
                return tab2;
            case 2:
                GreeceFragment3 tab3 = new GreeceFragment3();
                return tab3;

        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
