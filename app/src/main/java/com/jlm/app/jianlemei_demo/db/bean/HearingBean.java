package com.jlm.app.jianlemei_demo.db.bean;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Administrator on 2016/4/8.
 */
public class HearingBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    public Float hearmingH;

    public Float getHearmingL() {
        return hearmingL;
    }

    public void setHearmingL(Float hearmingL) {
        this.hearmingL = hearmingL;
    }

    @DatabaseField(canBeNull = false)
    public Float hearmingL;
    @DatabaseField(canBeNull = false)
    public String recordTime;
    @DatabaseField(canBeNull = false, columnName = "username")
    public String user;

    public HearingBean() {

    }

    public HearingBean(Float hearmingH, Float hearmingL, String user) {
        this.hearmingH = hearmingH;
        this.hearmingL = hearmingL;
        this.recordTime = String.valueOf(System.currentTimeMillis());
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getHearmingH() {
        return hearmingH;
    }

    public void setHearmingH(Float hearmingH) {
        this.hearmingH = hearmingH;
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
