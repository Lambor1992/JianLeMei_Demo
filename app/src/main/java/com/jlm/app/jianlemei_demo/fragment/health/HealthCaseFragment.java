package com.jlm.app.jianlemei_demo.fragment.health;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.health.HealthCaseActivity;
import com.jlm.app.jianlemei_demo.activity.health.HealthCaseFootActivity;
import com.jlm.app.jianlemei_demo.fragment.health.mytrack.trackshow.MyTrackActivity;
import com.jlm.app.jianlemei_demo.utils.HealthCase;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jlm.app.jianlemei_demo.utils.HealthCase.CASE_NUM;

public class HealthCaseFragment extends Fragment {
    private static HealthCaseFragment fragment;

    @Bind(R.id.tv_fg_health_case_eye)
    TextView CaseEye;
    @Bind(R.id.tv_fg_health_case_ear)
    TextView CaseEar;
    @Bind(R.id.tv_fg_health_case_blood)
    TextView CaseBlood;
    @Bind(R.id.tv_fg_health_case_blood2)
    TextView CaseBlood2;
    @Bind(R.id.tv_fg_health_case_weight)
    TextView CaseWeight;
    @Bind(R.id.tv_fg_health_case_psychology)
    TextView CasePsychology;
    View x;

    private Button myStrack;

    String mData;
    private static final String mFinished = "已完成";

    public HealthCaseFragment() {
    }

    public static HealthCaseFragment newInstance() {
        if (fragment == null) return new HealthCaseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_health_case, container, false);
        ButterKnife.bind(this, view);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("healthcase", Context.MODE_PRIVATE);
        x = view.findViewById(R.id.fg_health_case_layout);

        myStrack = (Button) view.findViewById(R.id.mytrack);
        ViewTreeObserver vto = x.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(x.getHeight() - 40, x.getHeight() - 40);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                layoutParams.setMargins(0, 10, 0, 10);
                view.findViewById(R.id.fg_health_case_foot_round).setLayoutParams(layoutParams);
            }
        });
//
        mData = sharedPreferences.getString("data", "");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.e("Test", "" + dateFormat.format(new Date(System.currentTimeMillis())));
        if (mData.equals(dateFormat.format(new Date(System.currentTimeMillis())))) {
            CaseEye.setText(mFinished + sharedPreferences.getInt("eye", 0) + "/" + 9);
            CaseEar.setText(mFinished + sharedPreferences.getInt("ear", 0) + "/" + 5);
            CaseBlood.setText(mFinished + sharedPreferences.getInt("blood", 0) + "/" + 2);
            CaseBlood2.setText(mFinished + sharedPreferences.getInt("boold1", 0) + "/" + 0);
            CaseWeight.setText(mFinished + sharedPreferences.getInt("weight", 0) + "/" + 2);
            CasePsychology.setText(mFinished + sharedPreferences.getInt("psychology", 0) + "/" + 1);
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putString("data", dateFormat.format(new Date(System.currentTimeMillis())));
            editor.apply();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fg_health_case_foot_round, R.id.fg_health_case_eye, R.id.fg_health_case_ear, R.id.fg_health_case_blood, R.id.fg_health_case_blood2, R.id.fg_health_case_weight, R.id.fg_health_case_psychology,R.id.mytrack})
    public void onClick(View view) {
        int id = 0;
        Intent intent = new Intent();


        switch (view.getId()) {
            case R.id.fg_health_case_foot_round:
                intent.setClass(getActivity(), HealthCaseFootActivity.class);
                break;
            case R.id.fg_health_case_eye:
                intent.setClass(getActivity(), HealthCaseActivity.class);
                intent.putExtra(HealthCase.MAKE, HealthCase.HEALTH_CASE_C.EYE);
                id = CASE_NUM[0];
                break;
            case R.id.fg_health_case_ear:
                intent.setClass(getActivity(), HealthCaseActivity.class);
                intent.putExtra(HealthCase.MAKE, HealthCase.HEALTH_CASE_C.EAR);
                id = CASE_NUM[1];
                break;

            case R.id.fg_health_case_blood:
                intent.setClass(getActivity(), HealthCaseActivity.class);
                intent.putExtra(HealthCase.MAKE, HealthCase.HEALTH_CASE_C.BLOOD);
                id = CASE_NUM[2];
                break;
            case R.id.fg_health_case_blood2:
                intent.setClass(getActivity(), HealthCaseActivity.class);
                intent.putExtra(HealthCase.MAKE, HealthCase.HEALTH_CASE_C.BLOOD2);
                id = CASE_NUM[3];
                break;
            case R.id.fg_health_case_weight:
                intent.setClass(getActivity(), HealthCaseActivity.class);
                intent.putExtra(HealthCase.MAKE, HealthCase.HEALTH_CASE_C.WEIGHT);
                id = CASE_NUM[4];
                break;
            case R.id.fg_health_case_psychology:
                intent.setClass(getActivity(), HealthCaseActivity.class);
                intent.putExtra(HealthCase.MAKE, HealthCase.HEALTH_CASE_C.PSYCHOLOGY);
                id = CASE_NUM[5];
                break;
            case R.id.mytrack:
               intent.setClass(getActivity(),MyTrackActivity.class);
                break;
        }
        startActivityForResult(intent, id);
    }

    /**
     * 所有的Activity对象的返回值都是由这个方法来接收
     * requestCode:    表示的是启动一个Activity时传过去的requestCode值 对应startActivityForResult(intent, 0);中的0
     * resultCode：表示的是启动后的Activity回传值时的resultCode值
     * data：表示的是启动后的Activity回传过来的Intent对象
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("healthcase", Context.MODE_PRIVATE);
        if (requestCode == CASE_NUM[0]) {
            CaseEye.setText(mFinished + sharedPreferences.getInt("eye", 0) + "/" + 9);
        } else if (requestCode == CASE_NUM[1]) {
            CaseEar.setText(mFinished + sharedPreferences.getInt("ear", 0) + "/" + 5);

        } else if (requestCode == CASE_NUM[2]) {
            CaseBlood.setText(mFinished + sharedPreferences.getInt("blood", 0) + "/" + 2);

        } else if (requestCode == CASE_NUM[3]) {
            CaseBlood2.setText(mFinished + sharedPreferences.getInt("boold1", 0) + "/" + 0);

        } else if (requestCode == CASE_NUM[4]) {
            CaseWeight.setText(mFinished + sharedPreferences.getInt("weight", 0) + "/" + 2);

        } else if (requestCode == CASE_NUM[5]) {
            CasePsychology.setText(mFinished + sharedPreferences.getInt("psychology", 0) + "/" + 1);
        }
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
