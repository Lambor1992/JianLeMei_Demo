package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jlm.app.jianlemei_demo.R;

public class CheckFastActivity extends Activity implements View.OnClickListener {
    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_fast);


        initView();

    }

    private void initView() {

        this.findViewById(R.id.btn_tjb_start).setOnClickListener(this);
        this.findViewById(R.id.txt_tjb_principle).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_tjb_start:
                startActivity(new Intent(CheckFastActivity.this, AllCheckActivity.class).putExtra("userId", "zhangsan"));
                break;
            case R.id.txt_tjb_principle:
                startActivity(new Intent(CheckFastActivity.this, CheckPrincipleActivity.class));
                break;
        }
    }
}
