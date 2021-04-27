package com.drillgil.android.tourguide.Home.UnitedStates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

//import com.cuberto.liquid_swipe.LiquidPager;
import com.drillgil.android.tourguide.OnBoarding.SimpleFragmentPagerAdapter;
import com.drillgil.android.tourguide.R;

public class UnitedStatesActivity extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_united_states);

        pager = findViewById(R.id.view_pager_united_states);
        viewPager = new UnitedStatesFragmentPagerAdapter(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);

    }
}