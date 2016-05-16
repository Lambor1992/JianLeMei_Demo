package com.jlm.app.jianlemei_demo.db.bean;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Administrator on 2016/4/8.
 */
public class RespiratoryRateBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    public Float respiratoryRate;
    @DatabaseField(canBeNull = false)
    public String recordTime;
    @DatabaseField(canBeNull = false, columnName = "username")
    public String user;

    public RespiratoryRateBean() {

    }

    public RespiratoryRateBean(Float respiratoryRate, long time, String user) {
        this.respiratoryRate = respiratoryRate;
        this.recordTime = String.valueOf(time);
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Float respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
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
