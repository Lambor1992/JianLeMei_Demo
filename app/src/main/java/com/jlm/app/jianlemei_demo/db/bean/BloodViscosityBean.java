package com.jlm.app.jianlemei_demo.db.bean;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Administrator on 2016/4/8.
 */
public class BloodViscosityBean {
    @DatabaseField(generatedId = true)
    private int id;

    public Float getBloodViscosity() {
        return bloodViscosity;
    }

    public void setBloodViscosity(Float bloodViscosity) {
        this.bloodViscosity = bloodViscosity;
    }

    @DatabaseField(canBeNull = false)
    public Float bloodViscosity;
    @DatabaseField(canBeNull = false)
    public String recordTime;
    @DatabaseField(canBeNull = false, columnName = "username")
    public String user;

    public BloodViscosityBean() {

    }

    public BloodViscosityBean(Float bloodViscosity, String user) {
        this.bloodViscosity = bloodViscosity;
        this.recordTime = String.valueOf(System.currentTimeMillis());
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
