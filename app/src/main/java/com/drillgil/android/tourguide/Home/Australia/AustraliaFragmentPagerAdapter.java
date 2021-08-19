package com.drillgil.android.tourguide.Home.Australia;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment1;
import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment2;
import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment3;

import org.jetbrains.annotations.NotNull;

public class AustraliaFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;


    public AustraliaFragmentPagerAdapter(FragmentManager supportFragmentManager, int i) {
        super(supportFragmentManager, i);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                AustraliaFragment1 tab1 = new AustraliaFragment1();
                return tab1;
            case 1:
                AustraliaFragment2 tab2 = new AustraliaFragment2();
                return tab2;
            case 2:
                AustraliaFragment3 tab3 = new AustraliaFragment3();
                return tab3;

        }

        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
