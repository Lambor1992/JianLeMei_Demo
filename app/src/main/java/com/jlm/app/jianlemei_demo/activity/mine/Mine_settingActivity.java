package com.jlm.app.jianlemei_demo.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.LoginActivity;
import com.jlm.app.jianlemei_demo.utils.AppUpdatexUtils;
import com.jlm.app.jianlemei_demo.utils.WarmingSharePrefrenceHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Mine_settingActivity extends AppCompatActivity {
    @Bind(R.id.mine_setting_click_warming)
    Switch mSwith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_stting);
        ButterKnife.bind(this);
        mSwith.setChecked(new WarmingSharePrefrenceHelper(this).onWarming());
    }

    @OnClick({R.id.mine_setting_click_contact, R.id.mine_setting_click_about, R.id.mine_setting_click_check, R.id.mine_setting_click_warming, R.id.mine_setting_out_login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_setting_click_contact:
                startActivity(new Intent(this, SettingContactUsActivity.class));
                break;
            case R.id.mine_setting_click_about:
                startActivity(new Intent(this, SettingAboutUsActivity.class));
                break;
            case R.id.mine_setting_click_check:
                AppUpdatexUtils.intiAppUpdate2Utils(this);
                break;
            case R.id.mine_setting_click_warming:
                new WarmingSharePrefrenceHelper(this).changeWarmingState();
                break;
            case R.id.mine_setting_out_login_btn:
                getSharedPreferences("isLogin", Context.MODE_PRIVATE).edit().putBoolean("is", false).apply();
                getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).edit().clear().apply();
                setResult(1, new Intent().putExtra("outLogin", true));
                finish();
                break;
        }
    }
}
