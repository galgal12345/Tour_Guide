package com.drillgil.android.tourguide.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;

import com.cuberto.liquid_swipe.LiquidPager;
import com.drillgil.android.tourguide.R;


public class MainActivity extends AppCompatActivity {

    LiquidPager pager;
    PagerAdapter viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pager = findViewById(R.id.pager);
        viewPager = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);


    }


}