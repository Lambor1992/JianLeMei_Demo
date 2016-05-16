package com.jlm.app.jianlemei_demo.fragment.examcenter;


import android.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class EyeExamFragment extends Fragment {


    @Bind(R.id.fg_social_title)
    TabPageIndicator Title;
    @Bind(R.id.fg_social_content)
    ViewPager Content;

    public EyeExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social, container, false);
        ButterKnife.bind(this, view);
        List<Fragment> list_fragment = new ArrayList<Fragment>();
        list_fragment.add(new Eye1Fragment());
        list_fragment.add(new Eye2Fragment());
        list_fragment.add(new Eye3Fragment());
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getActivity().getFragmentManager(), list_fragment, new String[]{"色盲检测", "视力检测", "散光检测"});
        Content.setAdapter(adapter);
        Title.setViewPager(Content);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
