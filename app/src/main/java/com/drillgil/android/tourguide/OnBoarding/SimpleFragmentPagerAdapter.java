package com.drillgil.android.tourguide.OnBoarding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.drillgil.android.tourguide.OnBoarding.OnBoardingFragment1;
import com.drillgil.android.tourguide.OnBoarding.OnBoardingFragment2;
import com.drillgil.android.tourguide.OnBoarding.OnBoardingFragment3;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;

    public SimpleFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                return tab1;
            case 1:
                OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                return tab2;
            case 2:
                OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                return tab3;

        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
