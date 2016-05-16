package com.jlm.app.jianlemei_demo.utils;

public class Doctor {
    private Integer id;
    private String icon;
    private String name;
    private String position;// 职位
    private String hospital;
    private String expert;// 擅长
    private String address;//
    private String doctorInfo;

    public String getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(String doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    public Doctor(String icon, String name, String position, String hospital,
                  String expert, String address) {
        this.icon = icon;

        this.name = name;
        this.position = position;
        this.hospital = hospital;
        this.expert = expert;
        this.address = address;
    }

    public Doctor() {
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}