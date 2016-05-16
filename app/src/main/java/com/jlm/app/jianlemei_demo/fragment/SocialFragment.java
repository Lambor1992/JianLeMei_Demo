package com.jlm.app.jianlemei_demo.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.adapter.FragmentPagerAdapter;
import com.jlm.app.jianlemei_demo.adapter.TabPageIndicatorAdapter;
import com.jlm.app.jianlemei_demo.fragment.social.SocialElderlyUniversityFragment;
import com.jlm.app.jianlemei_demo.fragment.social.SocialExportFragment;
import com.jlm.app.jianlemei_demo.fragment.social.SocialFriendsFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SocialFragment extends Fragment {
    private static SocialFragment fragment;
    @Bind(R.id.fg_social_title)
    TabPageIndicator FgSocialTitle;
    @Bind(R.id.fg_social_content)
    ViewPager FgSocialContent;

    private String[] TITLE = {"老年大学", "专家咨询", "朋友圈"};

    public SocialFragment() {
    }

    public static SocialFragment newInstance() {
        if (fragment == null) return new SocialFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);
        ButterKnife.bind(this, view);
        List<Fragment> list_fragment = new ArrayList<Fragment>();
        list_fragment.add(new SocialElderlyUniversityFragment());
        list_fragment.add(new SocialExportFragment());
        list_fragment.add(new SocialFriendsFragment());
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getActivity().getFragmentManager(), list_fragment, TITLE);
        FgSocialContent.setAdapter(adapter);
        FgSocialTitle.setViewPager(FgSocialContent);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}


