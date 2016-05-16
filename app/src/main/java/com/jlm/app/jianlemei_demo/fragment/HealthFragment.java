package com.jlm.app.jianlemei_demo.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.adapter.FragmentPagerAdapter;
import com.jlm.app.jianlemei_demo.adapter.TabPageIndicatorAdapter;
import com.jlm.app.jianlemei_demo.fragment.health.HealthCaseFragment;
import com.jlm.app.jianlemei_demo.fragment.health.HealthCunsultFragment;
import com.jlm.app.jianlemei_demo.fragment.health.HealthFoodFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;


public class HealthFragment extends Fragment {
    private static HealthFragment fragment;
    private TabPageIndicator mTabLayout;
    private ViewPager mViewPager;
    private String[] TITLE = {"健康方案", "健康资讯", "健康饮食"};
    private ArrayList<Fragment> list_fragment = new ArrayList<Fragment>(TITLE.length);
    private FragmentPagerAdapter adapter = null;

    public HealthFragment() {
    }

    public static HealthFragment newInstance() {
        if (fragment == null) return new HealthFragment();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, container, false);
        mTabLayout = (TabPageIndicator) view.findViewById(R.id.fg_health_tab_title);
        mViewPager = (ViewPager) view.findViewById(R.id.fg_health_tab_content);
        list_fragment.add(HealthCaseFragment.newInstance());
        list_fragment.add(HealthCunsultFragment.newInstance());
        list_fragment.add(HealthFoodFragment.newInstance());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new TabPageIndicatorAdapter(getActivity().getFragmentManager(), list_fragment, TITLE);
        mViewPager.setAdapter(adapter);
        mTabLayout.setViewPager(mViewPager);
        mTabLayout.setCurrentItem(2);
        mTabLayout.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
