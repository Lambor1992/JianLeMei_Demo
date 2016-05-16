
package com.jlm.app.jianlemei_demo.db.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class Radio implements Parcelable {
    private Integer id;
    private String title;
    private String radioAddress;
    private String radioInfo;
    private String doctorInfo;
    private String picAddress;
    private String count_zane;
    private String count_online;
    private String mp3Address;

    protected Radio(Parcel in) {
        title = in.readString();
        radioAddress = in.readString();
        radioInfo = in.readString();
        doctorInfo = in.readString();
        picAddress = in.readString();
        count_zane = in.readString();
        count_online = in.readString();
        mp3Address = in.readString();
    }

    public Radio(JSONObject jsonObject) {
        title = jsonObject.optString("title");
        radioAddress = jsonObject.optString("radioAddress");
        radioInfo = jsonObject.optString("radioInfo");
        doctorInfo = jsonObject.optString("doctorInfo");
        picAddress = jsonObject.optString("picAddress");
        count_zane = jsonObject.optString("count_zane");
        count_online = jsonObject.optString("count_online");
        mp3Address = jsonObject.optString("mp3Address");
        id = jsonObject.optInt("id");
    }

    public static final Creator<Radio> CREATOR = new Creator<Radio>() {
        @Override
        public Radio createFromParcel(Parcel in) {
            return new Radio(in);
        }

        @Override
        public Radio[] newArray(int size) {
            return new Radio[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(String doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public String getCount_zane() {
        return count_zane;
    }

    public void setCount_zane(String count_zane) {
        this.count_zane = count_zane;
    }

    public String getCount_online() {
        return count_online;
    }

    public void setCount_online(String count_online) {
        this.count_online = count_online;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return radioAddress;
    }

    public void setAddress(String address) {
        this.radioAddress = address;
    }

    public void setRadioInfo(String radioInfo) {
        this.radioInfo = radioInfo;
    }

    public String getRadioInfo() {
        return radioInfo;
    }

    public void setRadioAddress(String radioAddress) {
        this.radioAddress = radioAddress;
    }

    public String getRadioAddress() {
        return radioAddress;
    }

    public void setMp3Address(String mp3Address) {
        this.mp3Address = mp3Address;
    }

    public String getMp3Address() {
        return mp3Address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(radioAddress);
        dest.writeString(radioInfo);
        dest.writeString(doctorInfo);
        dest.writeString(picAddress);
        dest.writeString(count_zane);
        dest.writeString(count_online);
        dest.writeString(mp3Address);
    }
}

 

