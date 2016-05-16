package com.jlm.app.jianlemei_demo.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.jlm.app.jianlemei_demo.R;

public class DatechooseActivity extends AppCompatActivity {

    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_date_choose);
        datePicker = (DatePicker) findViewById(R.id.mine_data_choose_datapicker);
        findViewById(R.id.mine_data_datepicker_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).edit().putString(Mine_userinfoActivity.UserInfo_birthday, datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth()).apply();
                DatechooseActivity.this.setResult(200, getIntent());
                finish();//必须手动finish
            }
        });
        findViewById(R.id.mine_data_datepicker_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
