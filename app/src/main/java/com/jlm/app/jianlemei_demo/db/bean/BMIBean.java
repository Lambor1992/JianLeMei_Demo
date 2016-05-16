package com.jlm.app.jianlemei_demo.db.bean;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Administrator on 2016/4/8.
 */
public class BMIBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    public Float BMI;
    @DatabaseField(canBeNull = false)
    public String recordTime;
    @DatabaseField(canBeNull = false, columnName = "username")
    public String user;

    public BMIBean() {

    }

    public BMIBean(Float BMI) {
        this.BMI = BMI;
        this.recordTime = String.valueOf(System.currentTimeMillis());
        this.user = "user1";
    }

    public BMIBean(boolean isMan, int age, float high, float weight) {
        this.recordTime = String.valueOf(System.currentTimeMillis());
        this.user = "user1";
        this.BMI = weight / high / high;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getBMI() {
        return BMI;
    }

    public void setBMI(Float BMI) {
        this.BMI = BMI;
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
