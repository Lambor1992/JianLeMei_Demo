package com.jlm.app.jianlemei_demo.fragment.social;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.widget.AutoScrollViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SocialElderlyUniversityFragment extends Fragment {
    @Bind(R.id.elderlyuniversity_viewpager)
    AutoScrollViewPager Viewpager;

    public SocialElderlyUniversityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social_elderly_university, container, false);
        ButterKnife.bind(this, view);
        Viewpager.setAdapter(new PagerAdapter() {
            int[] recs = {R.drawable.elderlyuniversity_dance,
                    R.drawable.elderlyuniversity_draw,
                    R.drawable.elderlyuniversity_know,
                    R.drawable.elderlyuniversity_news,
                    R.drawable.elderlyuniversity_write};

            @Override
            public int getCount() {
                return 5;
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageResource(recs[position]);
                container.addView(imageView);
                return imageView;
            }
        });
        Viewpager.startAutoScroll();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Viewpager.stopAutoScroll();
        ButterKnife.unbind(this);
    }
}
