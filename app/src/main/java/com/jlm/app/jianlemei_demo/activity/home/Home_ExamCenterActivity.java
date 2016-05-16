package com.jlm.app.jianlemei_demo.activity.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.CheckFastActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.EarExamActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.ExamPsychologyActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.EyeExamActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.VitalcapacityActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.HeartRateActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.BloodViscosiyActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.BloodPressActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.OxygenActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home_ExamCenterActivity extends AppCompatActivity {


    @Bind(R.id.examcenter_btn_test_fast)
    Button Fast;
    @Bind(R.id.examcenter_btn_test_vision)
    Button Vision;
    @Bind(R.id.examcenter_btn_test_hearing)
    Button Hearing;
    @Bind(R.id.examcenter_btn_test_bloodpressure)
    Button Bloodpressure;
    @Bind(R.id.examcenter_btn_test_heartrate)
    Button Heartrate;
    @Bind(R.id.examcenter_btn_test_vitalcapacity)
    Button Vitalcapacity;
    @Bind(R.id.examcenter_btn_test_bloodviscosity)
    Button BloodViscosity;
    @Bind(R.id.examcenter_btn_test_oxygen)
    Button Oxygen;
    @Bind(R.id.examcenter_btn_test_mental)
    Button Mental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_examcenter);
        ButterKnife.bind(this);
        Vision.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int w = Vision.getWidth();
                Vision.setCompoundDrawables(null, getPic(w, R.drawable.eye_exam), null, null);
                Hearing.setCompoundDrawables(null, getPic(w, R.drawable.ear_exam), null, null);
                Bloodpressure.setCompoundDrawables(null, getPic(w, R.drawable.xueya_exam), null, null);
                Heartrate.setCompoundDrawables(null, getPic(w, R.drawable.heart_exam), null, null);
                Vitalcapacity.setCompoundDrawables(null, getPic(w, R.drawable.fei_exam), null, null);
                BloodViscosity.setCompoundDrawables(null, getPic(w, R.drawable.breath_exam), null, null);
                Oxygen.setCompoundDrawables(null, getPic(w, R.drawable.xueyang_exam), null, null);
                Mental.setCompoundDrawables(null, getPic(w, R.drawable.xinli_exam), null, null);

            }
        });

    }

    public Drawable getPic(int w, int id) {
        Drawable drawable = ContextCompat.getDrawable(Home_ExamCenterActivity.this, id);
        drawable.setBounds(0, 20, w - 20, w - 20);
        return drawable;
    }

    @OnClick({R.id.examcenter_btn_test_fast, R.id.examcenter_btn_test_vision, R.id.examcenter_btn_test_hearing, R.id.examcenter_btn_test_bloodpressure, R.id.examcenter_btn_test_heartrate, R.id.examcenter_btn_test_vitalcapacity, R.id.examcenter_btn_test_bloodviscosity, R.id.examcenter_btn_test_oxygen, R.id.examcenter_btn_test_mental})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.examcenter_btn_test_fast:
                startActivity(new Intent(this, CheckFastActivity.class));
                break;
            case R.id.examcenter_btn_test_vision:
                startActivity(new Intent(this, EyeExamActivity.class));
                break;
            case R.id.examcenter_btn_test_hearing:
                startActivity(new Intent(this, EarExamActivity.class));
                break;
            case R.id.examcenter_btn_test_bloodpressure:
                startActivity(new Intent(this, BloodPressActivity.class));
                break;
            case R.id.examcenter_btn_test_heartrate:
                startActivity(new Intent(this, HeartRateActivity.class));
                break;
            case R.id.examcenter_btn_test_vitalcapacity:
                startActivity(new Intent(this, VitalcapacityActivity.class));
                break;
            case R.id.examcenter_btn_test_bloodviscosity:
                startActivity(new Intent(this, BloodViscosiyActivity.class));
                break;
            case R.id.examcenter_btn_test_oxygen:
                startActivity(new Intent(this, OxygenActivity.class));
                break;
            case R.id.examcenter_btn_test_mental:
                startActivity(new Intent(this, ExamPsychologyActivity.class));
                break;
        }
    }
}
