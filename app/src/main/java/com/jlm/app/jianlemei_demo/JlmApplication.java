package com.jlm.app.jianlemei_demo;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

/**
 * Created by Administrator on 2016/4/9.
 */
public class JlmApplication extends Application {
    @Override
    public void onCreate() {
        SpeechUtility.createUtility(this, "appid=" + getString(R.string.app_id));

        super.onCreate();
    }
}
