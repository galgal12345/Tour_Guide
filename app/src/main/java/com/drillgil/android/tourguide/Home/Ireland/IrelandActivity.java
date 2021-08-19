package com.drillgil.android.tourguide.Home.Ireland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragmentPagerAdapter;
import com.drillgil.android.tourguide.R;

public class IrelandActivity extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ireland);

        pager = findViewById(R.id.view_pager_ireland);
        viewPager = new IrelandFragmentPagerAdapter(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);

    }
}