package com.drillgil.android.tourguide.Home.Brazil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragmentPagerAdapter;
import com.drillgil.android.tourguide.R;

public class BrazilActivity extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brazil);

        pager = findViewById(R.id.view_pager_brazil);
        viewPager = new BrazilFragmentPagerAdapter(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);

    }
}