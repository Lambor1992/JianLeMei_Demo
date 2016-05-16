package com.jlm.app.jianlemei_demo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.fragment.ExaminationDataFragment;
import com.jlm.app.jianlemei_demo.fragment.HealthFragment;
import com.jlm.app.jianlemei_demo.fragment.HomeFragment;
import com.jlm.app.jianlemei_demo.fragment.MineFragment;
import com.jlm.app.jianlemei_demo.fragment.SocialFragment;
import com.jlm.app.jianlemei_demo.service.WarmingService;
import com.jlm.app.jianlemei_demo.utils.AppUpdatexUtils;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;
import com.jlm.app.jianlemei_demo.utils.WarmingSharePrefrenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.main_img_1)
    ImageView mainImg1;
    @Bind(R.id.main_img_2)
    ImageView mainImg2;
    @Bind(R.id.main_img_3)
    ImageView mainImg3;
    @Bind(R.id.main_img_4)
    ImageView mainImg4;
    @Bind(R.id.main_img_5)
    ImageView mainImg5;
    private FragmentManager manager = getFragmentManager();
    private long Time = 0;
    private int layout_id = R.id.main_framelayout;
    Drawable health_check;
    Drawable health_normal;
    Drawable data_normal;
    Drawable data_check;
    Drawable home_normal;
    Drawable home_check;
    Drawable social_normal;
    Drawable social_check;
    Drawable mine_normal;
    Drawable mine_check;

    private HealthFragment health;
    private ExaminationDataFragment data;
    private HomeFragment home = HomeFragment.newInstance();
    private MineFragment mine;
    private SocialFragment social;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setResult(0, new Intent().putExtra("Normal", "Normal"));
        manager = getFragmentManager();
        manager.beginTransaction().add(layout_id, home).commit();

        if (new WarmingSharePrefrenceHelper(this).onWarming()) {
            startService(new Intent(this, WarmingService.class));
        }
        initView();
        AppUpdatexUtils.intiAppUpdatexUtils(this);
        try {
            checkUserInfo();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void checkUserInfo() throws UnsupportedEncodingException {
        VolleySingleton.getVolleySingleton(this).addToRequestQueue(
                new StringRequest(
                        HttpAdress.HTTP_getUserInfoByUsername +
                                "?username=" +
                                URLEncoder.encode(getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE)
                                        .getString(Mine_userinfoActivity.UserInfo_username, ""), "UTF-8"), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            SharedPreferences.Editor editor = getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).edit();
                            editor.putString(Mine_userinfoActivity.UserInfo_username, jsonObject.optString("username"))
                                    .putString(Mine_userinfoActivity.UserInfo_realname, jsonObject.optString("realname"))
                                    .putString(Mine_userinfoActivity.UserInfo_birthday, jsonObject.optString("user_birth"))
                                    .putString(Mine_userinfoActivity.UserInfo_addr, jsonObject.optString("user_addr"))
                                    .putInt(Mine_userinfoActivity.UserInfo_age, jsonObject.optInt("user_age"))
                                    .putString(Mine_userinfoActivity.UserInfo_bg, jsonObject.optString("user_bg"))
                                    .putString(Mine_userinfoActivity.UserInfo_province, jsonObject.optString("user_province"))
                                    .putString(Mine_userinfoActivity.UserInfo_city, jsonObject.optString("user_city"))
                                    .putString(Mine_userinfoActivity.UserInfo_county, jsonObject.optString("user_county"))
                                    .putInt(Mine_userinfoActivity.UserInfo_id, jsonObject.optInt("id"))
                                    .putString(Mine_userinfoActivity.UserInfo_identifycode, jsonObject.optString("identifycode"))
                                    .putString(Mine_userinfoActivity.UserInfo_invitecode, jsonObject.optString("invitecode"))
                                    .putString(Mine_userinfoActivity.UserInfo_invitepeople, jsonObject.optString("invitepeople"))
                                    .putString(Mine_userinfoActivity.UserInfo_photo, jsonObject.optString("user_photo"))
                                    .putString(Mine_userinfoActivity.UserInfo_tel, jsonObject.optString("tel"))
                                    .apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
    }

    private void initView() {

        health_check = ContextCompat.getDrawable(this, R.drawable.main_bar_health_check);
        health_normal = ContextCompat.getDrawable(this, R.drawable.main_bar_health_normal);

        data_normal = ContextCompat.getDrawable(this, R.drawable.main_bar_data_normal);
        data_check = ContextCompat.getDrawable(this, R.drawable.main_bar_data_check);

        home_normal = ContextCompat.getDrawable(this, R.drawable.main_bar_home_normal);
        home_check = ContextCompat.getDrawable(this, R.drawable.main_bar_home_check);

        social_normal = ContextCompat.getDrawable(this, R.drawable.main_bar_social_normal);
        social_check = ContextCompat.getDrawable(this, R.drawable.main_bar_social_check);

        mine_normal = ContextCompat.getDrawable(this, R.drawable.main_bar_mine_normal);
        mine_check = ContextCompat.getDrawable(this, R.drawable.main_bar_mine_check);


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                health = HealthFragment.newInstance();
                data = ExaminationDataFragment.newInstance();
                mine = MineFragment.newInstance();
                social = SocialFragment.newInstance();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.add(layout_id, data);
                fragmentTransaction.add(layout_id, social);
                fragmentTransaction.add(layout_id, mine);
                fragmentTransaction.add(layout_id, health);
                fragmentTransaction.hide(data);
                fragmentTransaction.hide(social);
                fragmentTransaction.hide(mine);
                fragmentTransaction.hide(health);
                fragmentTransaction.commit();
            }
        }.execute();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (System.currentTimeMillis() - Time > 2000) {
            Toast.makeText(MainActivity.this, "再次点击返回键退出", Toast.LENGTH_LONG).show();
            Time = System.currentTimeMillis();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @OnClick({R.id.main_btn_1, R.id.main_btn_2, R.id.main_btn_3, R.id.main_btn_4, R.id.main_btn_5})
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        try {
            fragmentTransaction.hide(health);
            fragmentTransaction.hide(data);
            fragmentTransaction.hide(home);
            fragmentTransaction.hide(social);
            fragmentTransaction.hide(mine);
        } finally {
            switch (view.getId()) {
                case R.id.main_btn_1:
                    fragmentTransaction.show(health);
                    mainImg1.setImageDrawable(health_check);
                    mainImg2.setImageDrawable(data_normal);
                    mainImg3.setImageDrawable(home_normal);
                    mainImg4.setImageDrawable(social_normal);
                    mainImg5.setImageDrawable(mine_normal);
                    break;
                case R.id.main_btn_2:
                    fragmentTransaction.show(data);
                    mainImg1.setImageDrawable(health_normal);
                    mainImg2.setImageDrawable(data_check);
                    mainImg3.setImageDrawable(home_normal);
                    mainImg4.setImageDrawable(social_normal);
                    mainImg5.setImageDrawable(mine_normal);
                    break;
                case R.id.main_btn_3:
                    fragmentTransaction.show(home);
                    mainImg1.setImageDrawable(health_normal);
                    mainImg2.setImageDrawable(data_normal);
                    mainImg3.setImageDrawable(home_check);
                    mainImg4.setImageDrawable(social_normal);
                    mainImg5.setImageDrawable(mine_normal);
                    break;
                case R.id.main_btn_4:
                    fragmentTransaction.show(social);
                    mainImg1.setImageDrawable(health_normal);
                    mainImg2.setImageDrawable(data_normal);
                    mainImg3.setImageDrawable(home_normal);
                    mainImg4.setImageDrawable(social_check);
                    mainImg5.setImageDrawable(mine_normal);
                    break;
                case R.id.main_btn_5:
                    fragmentTransaction.show(mine);
                    mainImg1.setImageDrawable(health_normal);
                    mainImg2.setImageDrawable(data_normal);
                    mainImg3.setImageDrawable(home_normal);
                    mainImg4.setImageDrawable(social_normal);
                    mainImg5.setImageDrawable(mine_check);
                    break;
            }
        }
        fragmentTransaction.commit();
    }
}
