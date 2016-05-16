package com.jlm.app.jianlemei_demo.activity.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.adapter.FragmentPagerAdapter;
import com.jlm.app.jianlemei_demo.adapter.TabPageIndicatorAdapter;
import com.jlm.app.jianlemei_demo.fragment.video.Home_Video_1Fragment;
import com.jlm.app.jianlemei_demo.fragment.video.Home_Video_2Fragment;
import com.viewpagerindicator.TabPageIndicator;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Home_VideoActivity extends AppCompatActivity  {

    @Bind(R.id.home_video_tab)
    TabPageIndicator VideoTab;
    @Bind(R.id.home_video_viewpager)
    ViewPager VideoViewpager;
    public ArrayList<Fragment> list_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_video_layout);
        ButterKnife.bind(this);
        list_fragment = new ArrayList<>();
        list_fragment.add(new Home_Video_1Fragment());
        list_fragment.add(new Home_Video_2Fragment());
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getFragmentManager(), list_fragment, new String[]{"", ""});
        VideoViewpager.setAdapter(adapter);
        VideoTab.setViewPager(VideoViewpager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
