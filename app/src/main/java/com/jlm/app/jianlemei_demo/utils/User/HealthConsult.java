package com.jlm.app.jianlemei_demo.utils.User;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/4/5.
 */

public class HealthConsult implements Parcelable {
    private Integer id;
    private String title;
    private String content;
    //点击新闻列表 需要的地址
    private String address;
    //是否是视力保健相关
    public boolean isVision;
    //是否是听力保健相关
    public boolean isAudiology;
    //是否是控制血压的相关
    public boolean isBloodPress;
    //是否是控制血糖的相关
    public boolean isBloodSugar;
    //是否是体重控制的相关
    public boolean isWeightControl;
    //s是否是心理控制的相关
    public boolean isPsychologicalImprovement;

    protected HealthConsult(Parcel in) {
        title = in.readString();
        content = in.readString();
        address = in.readString();
        isVision = in.readByte() != 0;
        isAudiology = in.readByte() != 0;
        isBloodPress = in.readByte() != 0;
        isBloodSugar = in.readByte() != 0;
        isWeightControl = in.readByte() != 0;
        isPsychologicalImprovement = in.readByte() != 0;
    }

    public static final Creator<HealthConsult> CREATOR = new Creator<HealthConsult>() {
        @Override
        public HealthConsult createFromParcel(Parcel in) {
            return new HealthConsult(in);
        }

        @Override
        public HealthConsult[] newArray(int size) {
            return new HealthConsult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(address);
        dest.writeByte((byte) (isVision ? 1 : 0));
        dest.writeByte((byte) (isAudiology ? 1 : 0));
        dest.writeByte((byte) (isBloodPress ? 1 : 0));
        dest.writeByte((byte) (isBloodSugar ? 1 : 0));
        dest.writeByte((byte) (isWeightControl ? 1 : 0));
        dest.writeByte((byte) (isPsychologicalImprovement ? 1 : 0));
    }
}
