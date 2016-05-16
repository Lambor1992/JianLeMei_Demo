package com.jlm.app.jianlemei_demo.db.health;

public class Sick {
    private Integer sickId;
    private String sickName;
    private String sickInfo;

    public Sick() {
    }

    public Integer getSickId() {
        return this.sickId;
    }

    public void setSickId(Integer sickId) {
        this.sickId = sickId;
    }

    public String getSickName() {
        return this.sickName;
    }

    public void setSickName(String sickName) {
        this.sickName = sickName;
    }

    public String getSickInfo() {
        return this.sickInfo;
    }

    public void setSickInfo(String sickInfo) {
        this.sickInfo = sickInfo;
    }
}