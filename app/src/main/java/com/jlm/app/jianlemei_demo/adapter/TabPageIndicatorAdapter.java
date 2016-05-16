package com.jlm.app.jianlemei_demo.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

import java.util.List;

/**
 * Created by zwg on 2016/3/10.
 */

public class TabPageIndicatorAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;
    private String[] list_Title;

    public TabPageIndicatorAdapter(FragmentManager fm, List<Fragment> list_fragmen,
                                   String[] list_Title) {
        super(fm);
        this.list_fragment = list_fragmen;
        this.list_Title = list_Title;
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title[position];
    }

    @Override
    public int getCount() {
        return list_Title.length;
    }
}

