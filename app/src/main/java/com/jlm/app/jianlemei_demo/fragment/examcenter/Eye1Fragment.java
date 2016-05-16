package com.jlm.app.jianlemei_demo.fragment.examcenter;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Eye1Fragment extends Fragment {


    @Bind(R.id.fg_eye_test_viewpager)
    ViewPager Viewpager;
    @Bind(R.id.fg_eye_number_picker1)
    NumberPicker NumberPicker1;
    @Bind(R.id.fg_eye_number_picker2)
    NumberPicker NumberPicker2;
    private int[] images = {
            R.drawable.semang1, R.drawable.semang2, R.drawable.semang3, R.drawable.semang4};
    private String[] imagevalues = {"08", "25", "06", "02"};

    public Eye1Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eye_1, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        Viewpager.setAdapter(new MyPageAdapter());
        String[] num = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        NumberPicker1.setDisplayedValues(num);
        NumberPicker1.setMinValue(0);
        NumberPicker1.setMaxValue(num.length - 1);
        NumberPicker1.setValue(0);
        NumberPicker2.setDisplayedValues(num);
        NumberPicker2.setMinValue(0);
        NumberPicker2.setMaxValue(num.length - 1);
        NumberPicker2.setValue(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.eye_blind_btn, R.id.eye_sure_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eye_blind_btn:

                break;
            case R.id.eye_sure_btn:
                String num_value1 = NumberPicker1.getDisplayedValues()[NumberPicker1.getValue()];
                String num_value2 = NumberPicker2.getDisplayedValues()[NumberPicker2.getValue()];
                if ((num_value1 + num_value2).equals(imagevalues[Viewpager.getCurrentItem()])) {
                    if (Viewpager.getCurrentItem() == Viewpager.getChildCount() - 1) {
                        Toast.makeText(getActivity(), "恭喜您视力正常！", Toast.LENGTH_SHORT).show();
                    } else
                        Viewpager.setCurrentItem(Viewpager.getCurrentItem() + 1);
                } else {
                    Toast.makeText(getActivity(), "如果您看到的是个位数，您输入的数字前面请加“0”，如果您看不清上面图片里的数字，那么您有色盲的可能。", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.length;
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
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageResource(images[position]);
            container.addView(imageView);
            return imageView;
        }
    }
}
