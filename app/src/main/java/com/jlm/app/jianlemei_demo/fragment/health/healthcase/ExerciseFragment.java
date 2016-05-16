package com.jlm.app.jianlemei_demo.fragment.health.healthcase;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseFragment extends Fragment {


    @Bind(R.id.exercise_title)
    TextView Title;
    @Bind(R.id.exercise_img)
    ImageView Img;
    @Bind(R.id.exercise_content_layout)
    LinearLayout Content;

    String title;
    int imgId;
    String[] content;

    public ExerciseFragment() {
        // Required empty public constructor
    }
    @SuppressLint("ValidFragment")
    public ExerciseFragment(String title, int imgId, String[] content) {
        this.title = title;
        this.imgId = imgId;
        this.content = content;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        ButterKnife.bind(this, view);
        Title.setText(title);
        Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, Title.getTextSize() * 1.2f);
        Img.setImageResource(imgId);
        getContentView(content);
        return view;
    }

    public void getContentView(String[] strings) {
        int len = strings.length;
        for (int i = 0; i < len; i++) {
            if (strings[i].startsWith("步")) {
                TextView textView = new TextView(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                SpannableString spannableString = new SpannableString(strings[i]);
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.title)), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(spannableString);
                Content.addView(textView);
            } else {
                TextView textView = new TextView(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                textView.setText(strings[i]);
                Content.addView(textView);

            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
