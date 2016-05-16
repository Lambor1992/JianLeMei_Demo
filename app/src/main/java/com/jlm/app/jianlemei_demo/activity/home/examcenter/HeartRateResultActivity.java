package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.db.bean.HeartRateBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;

import java.sql.SQLException;

public class HeartRateResultActivity extends Activity {
    private int heartRate;
    private TextView xl_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinlv_result);
        Intent intent = getIntent();
        heartRate = intent.getIntExtra("heartRate", 0);
        String UserName = getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString("UserInfo_username", "user1");

        try {
            new ExamSQLHelper(this).getDao(HeartRateBean.class).create(new HeartRateBean(heartRate, UserName));
        } catch (SQLException e) {
            e.printStackTrace();
        }



        initView();
        setValue();


        if (60 <= heartRate && heartRate <= 100) {
            xl_value.setText("您的心率属于正常范围，请继续保持良好的生活习惯，祝您生活愉快！");
        } else if (heartRate < 60) {
            xl_value.setText("您的心率有点偏低，请保持良好的生活习惯，加强运动，锻炼身体，祝您生活愉快！");
        } else if (heartRate > 100) {
            xl_value.setText("您的心率有点偏高，请保持良好的生活习惯，加强运动，锻炼身体，祝您生活愉快！");
        } else {
            xl_value.setText("您的心率有点不在正常范围，请保持良好的生活习惯，加强运动，锻炼身体，祝您生活愉快！");
        }

    }

    private void initView() {
        xl_value = (TextView) findViewById(R.id.xl_value);

    }

    private void setValue() {
        xl_value.setText(heartRate + "");

    }
}
