package com.pzl.nestedscrolling_api21.demo3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.pzl.nestedscrolling_api21.R;

import java.util.ArrayList;

public class Demo3Activity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Fragment> fragments = new ArrayList<>();
        String[] titles = new String[]{
                "热门店铺", "时尚前沿", "媒体推广",
                "招商引资", "投诉建议", "开店自营",
                "发红包", "财务统计", "众筹",
        };
        for (int i = 0; i <= 8; i++) {
            PageFragment fragment = PageFragment.newInstance();
            fragments.add(fragment);
        }
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.vp);
        adapter = new PagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.setTabTextColors(Color.WHITE, Color.parseColor("#b7b7b7"));
    }

}
