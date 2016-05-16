package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.fragment.examcenter.EyeExamFragment;

public class EyeExamActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_exam);
        getFragmentManager().beginTransaction().add(R.id.eye_exam_layout, new EyeExamFragment()).commit();
    }
}
