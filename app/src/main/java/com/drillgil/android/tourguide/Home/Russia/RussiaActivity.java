package com.drillgil.android.tourguide.Home.Russia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesFragmentPagerAdapter;
import com.drillgil.android.tourguide.R;

public class RussiaActivity extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_russia);

        pager = findViewById(R.id.view_pager_russia);
        viewPager = new RussiaFragmentPagerAdapter(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);

    }
}