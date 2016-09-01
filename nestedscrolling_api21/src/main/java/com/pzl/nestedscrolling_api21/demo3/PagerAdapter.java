package com.pzl.nestedscrolling_api21.demo3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author zl.peng
 * @version [1.0, 2016-08-30]
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private String[] titles;

    public PagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        PageFragment fragment = (PageFragment) fragments.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
