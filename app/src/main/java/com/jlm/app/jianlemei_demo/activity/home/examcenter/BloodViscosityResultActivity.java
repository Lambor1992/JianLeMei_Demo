package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.db.bean.BloodViscosityBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;

import java.sql.SQLException;

public class BloodViscosityResultActivity extends Activity {

    private static float bloodN;
    private TextView xn_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuenian_result);
        String UserName = getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString("UserInfo_username","user1");

        Intent intent = getIntent();
        bloodN = intent.getFloatExtra("bloodN", 0);
        try {
            new ExamSQLHelper(this).getDao(BloodViscosityBean.class).create(new BloodViscosityBean((float)bloodN,UserName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initView();
        setValue();

    }

    private void initView() {
        xn_value = (TextView) findViewById(R.id.xn_value);

    }

    private void setValue() {
        xn_value.setText(bloodN + "");

    }
}
