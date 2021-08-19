package com.drillgil.android.tourguide.Home.Japan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragmentPagerAdapter;
import com.drillgil.android.tourguide.R;

public class JapanActivity extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japan);

        pager = findViewById(R.id.view_pager_japan);
        viewPager = new JapanFragmentPagerAdapter(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);
    }
}