package com.jlm.app.jianlemei_demo.db.bean;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Administrator on 2016/4/8.
 */
public class HeartRateBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    public Float hearRate;
    @DatabaseField(canBeNull = false)
    public String recordTime;
    @DatabaseField(canBeNull = false, columnName = "username")
    public String user;

    public HeartRateBean() {

    }

    public HeartRateBean(int hearRate, String user) {
        this.hearRate = (float) hearRate;
        this.recordTime = String.valueOf(System.currentTimeMillis());
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getHearRate() {
        return hearRate;
    }

    public void setHearRate(Float hearRate) {
        this.hearRate = hearRate;
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
