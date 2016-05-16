package com.jlm.app.jianlemei_demo.db.bean;

import android.content.Context;

import com.j256.ormlite.field.DatabaseField;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;

/**
 * Created by zwg on 2016/4/8.
 * 血压
 */

public class BloodPressBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    public Float diastolicBloodPressure;
    @DatabaseField(canBeNull = false)
    public Float systolicBloodPressure;
    @DatabaseField(canBeNull = false)
    public String recordTime;
    @DatabaseField(canBeNull = false, columnName = "username")
    public String user;

    public BloodPressBean() {
        this.recordTime = String.valueOf(System.currentTimeMillis());
        this.user = "user1";
    }

    public BloodPressBean(String dis, String sys, String user) {
        this.diastolicBloodPressure = Float.valueOf(dis);
        this.systolicBloodPressure = Float.valueOf(sys);
        this.recordTime = String.valueOf(System.currentTimeMillis());
        this.user = user;
    }

    public BloodPressBean(int dis, int sys, String user) {
        this.diastolicBloodPressure = (float) dis;
        this.systolicBloodPressure = (float) sys;
        this.recordTime = String.valueOf(System.currentTimeMillis());
        this.user = user;
    }

    public Float getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(Float diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public Float getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(Float systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
