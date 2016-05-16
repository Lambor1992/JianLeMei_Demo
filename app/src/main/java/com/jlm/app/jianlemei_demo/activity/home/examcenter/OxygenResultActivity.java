package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.db.bean.OxygenBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;

import java.sql.SQLException;

public class OxygenResultActivity extends Activity {
    private static int bloodY;
    private TextView xy_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xueyang_result);
        Intent intent = getIntent();
        bloodY = intent.getIntExtra("bloodY", 0);
        String UserName = getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString("UserInfo_username","user1");

        try {
            new ExamSQLHelper(this).getDao(OxygenBean.class).create(new OxygenBean(bloodY, UserName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initView();
        setValue();

    }

    private void initView() {
        xy_value = (TextView) findViewById(R.id.xy_value);

    }

    private void setValue() {
        xy_value.setText(bloodY + "");

    }
}
