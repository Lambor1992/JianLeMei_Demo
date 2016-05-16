package com.jlm.app.jianlemei_demo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.service.WarmingService;

/**
 * Created by Administrator on 2016/4/11.
 */
public class WarmingSharePrefrenceHelper {
    public String WARMING = "warming";
    public static final String isOn = "oo";
    public static final String Emergency1 = "Emergency1";
    public static final String Emergency2 = "Emergency2";
    public static final String Emergency3 = "Emergency3";
    public Context context;

    public WarmingSharePrefrenceHelper(Context context) {
        this.context = context;
    }

    private SharedPreferences.Editor getEditor() {
        return context.getSharedPreferences(WARMING, Context.MODE_PRIVATE).edit();
    }

    public SharedPreferences getShare() {
        return context.getSharedPreferences(WARMING, Context.MODE_PRIVATE);
    }

    public boolean onWarming() {
        return context.getSharedPreferences(WARMING, Context.MODE_PRIVATE).getBoolean(isOn, false);
    }

    public boolean changeWarmingState() {
        boolean b = onWarming();
        getEditor().putBoolean(isOn, !b).apply();
        Log.e("Test", "____________" + b);
        if (b) {
            context.stopService(new Intent(context, WarmingService.class));
        } else {
            context.startService(new Intent(context, WarmingService.class));
        }
        return !b;
    }

    private void addEmergency1(String name, String phone) {
        getEditor().putString(Emergency1, name + "&&" + phone).apply();

    }

    private void addEmergency2(String name, String phone) {
        getEditor().putString(Emergency2, name + "&&" + phone).apply();
    }

    private void addEmergency3(String name, String phone) {
        getEditor().putString(Emergency3, name + "&&" + phone).apply();
    }

    public void addEmergency(String s, String name, String phone) {
        if (phone.matches("^(13[0-9]|15[01]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$"))
            getEditor().putString(s, name + "&&" + phone).apply();
        else
            Toast.makeText(context, "电话号码输入错误", Toast.LENGTH_SHORT).show();
    }

    public void deleteEmergency1() {
        getEditor().remove(Emergency1).apply();
    }

    public void deleteEmergency2() {
        getEditor().remove(Emergency2).apply();
    }

    public void deleteEmergency3() {
        getEditor().remove(Emergency3).apply();
    }
}
