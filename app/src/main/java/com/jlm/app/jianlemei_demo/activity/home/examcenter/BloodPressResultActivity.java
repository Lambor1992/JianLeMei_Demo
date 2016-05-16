package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.db.bean.BloodPressBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;

import java.sql.SQLException;

public class BloodPressResultActivity extends Activity {
    private int highRate, lowRate;
    private TextView xy_value, xy_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String UserName = getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString("UserInfo_username","user1");
        setContentView(R.layout.activity_blood_press_result);
        Intent intent = getIntent();
        highRate = intent.getIntExtra("highRate", 0);
        lowRate = intent.getIntExtra("lowRate", 0);
        initView();
        setValue();
        try {
            new ExamSQLHelper(this).getDao(BloodPressBean.class).create(new BloodPressBean(highRate, lowRate, UserName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (75 <= lowRate && lowRate <= 85 || 115 < highRate && highRate < 125) {
            xy_result.setText("您的血压属于正常范围，请继续保持良好的生活习惯，祝您生活愉快！");
        } else if (lowRate < 75) {
            xy_result.setText("您有点低血压的症状，请保持良好的生活习惯，加强运动，锻炼身体，祝您生活愉快！");
        } else if (highRate > 125) {
            xy_result.setText("您有点高血压的症状，请保持良好的生活习惯，加强运动，锻炼身体，祝您生活愉快！");
        } else {
            xy_result.setText("您的血压有点不在正常范围，请保持良好的生活习惯，加强运动，锻炼身体，祝您生活愉快！");
        }
    }

    private void initView() {
        xy_value = (TextView) findViewById(R.id.xy_value);
        xy_result = (TextView) findViewById(R.id.xy_result);
    }

    private void setValue() {
        xy_value.setText(highRate + "/" + lowRate);
    }
}
