package com.drillgil.android.tourguide.Home.Greece;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragmentPagerAdapter;
import com.drillgil.android.tourguide.R;

public class GreeceActivity extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greece);

        pager = findViewById(R.id.view_pager_greece);
        viewPager = new GreeceFragmentPagerAdapter(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);
    }
}