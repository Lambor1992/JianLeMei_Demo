package com.jlm.app.jianlemei_demo.db.bean;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Administrator on 2016/4/8.
 */
public class OxygenBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    public Float oxygen;
    @DatabaseField(canBeNull = false)
    public String recordTime;
    @DatabaseField(canBeNull = false, columnName = "username")
    public String user;

    public OxygenBean() {

    }

    public OxygenBean(int oxygen, String user) {
        this.oxygen = (float) oxygen;
        this.recordTime = String.valueOf(System.currentTimeMillis());
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getOxygen() {
        return oxygen;
    }

    public void setOxygen(Float oxygen) {
        this.oxygen = oxygen;
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
