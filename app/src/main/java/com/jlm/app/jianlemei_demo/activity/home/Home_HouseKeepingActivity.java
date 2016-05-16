package com.jlm.app.jianlemei_demo.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.home.housekeeping.Baojie_Activity;
import com.jlm.app.jianlemei_demo.activity.home.housekeeping.Baomu_Activity;
import com.jlm.app.jianlemei_demo.activity.home.housekeeping.Ganxi_Activity;
import com.jlm.app.jianlemei_demo.activity.home.housekeeping.Huanshui_Activity;
import com.jlm.app.jianlemei_demo.activity.home.housekeeping.Kaisuo_Activity;
import com.jlm.app.jianlemei_demo.activity.home.housekeeping.Shutong_Activity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home_HouseKeepingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_house_keeping);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.button_baomu, R.id.button_baojie, R.id.button_huanshui, R.id.button_ganxi, R.id.button_kaisuo, R.id.button_shutong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_baomu:
                startActivity(new Intent(this, Baomu_Activity.class));
                break;
            case R.id.button_baojie:
                startActivity(new Intent(this, Baojie_Activity.class));
                break;
            case R.id.button_huanshui:
                startActivity(new Intent(this, Huanshui_Activity.class));
                break;
            case R.id.button_ganxi:
                startActivity(new Intent(this, Ganxi_Activity.class));
                break;
            case R.id.button_kaisuo:
                startActivity(new Intent(this, Kaisuo_Activity.class));
                break;
            case R.id.button_shutong:
                startActivity(new Intent(this, Shutong_Activity.class));
                break;
        }
    }
}
