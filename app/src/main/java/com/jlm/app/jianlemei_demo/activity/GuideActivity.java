package com.jlm.app.jianlemei_demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jlm.app.jianlemei_demo.R;

public class GuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
    }
}
