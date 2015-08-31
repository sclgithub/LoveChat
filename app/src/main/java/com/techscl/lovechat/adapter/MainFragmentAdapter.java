package com.techscl.lovechat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 15/7/30.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles;

    public MainFragmentAdapter(FragmentManager fm, List<Fragment> f, String[] t) {
        super(fm);
        fragments = f;
        titles = t;
    }


    @Override
    public Fragment getItem(int i) {
        if (fragments != null && fragments.size() > 0)
            return fragments.get(i);
        return null;
    }

    @Override
    public int getCount() {

        return 4;
    }

    public CharSequence getPageTitle(int position) {

        return titles[position];
    }


}
