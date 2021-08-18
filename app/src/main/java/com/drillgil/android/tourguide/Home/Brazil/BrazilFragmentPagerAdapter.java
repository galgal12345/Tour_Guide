package com.drillgil.android.tourguide.Home.Brazil;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment1;
import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment2;
import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragment3;

import org.jetbrains.annotations.NotNull;

public class BrazilFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;


    public BrazilFragmentPagerAdapter(FragmentManager fm, int i) {
        super(fm, i);

    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                BrazilFragment1 tab1 = new BrazilFragment1();
                return tab1;
            case 1:
                BrazilFragment2 tab2 = new BrazilFragment2();
                return tab2;
            case 2:
                BrazilFragment3 tab3 = new BrazilFragment3();
                return tab3;

        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }


}
