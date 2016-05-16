package com.jlm.app.jianlemei_demo.utils.User;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/3/28.
 */
public class Tea implements Parcelable {
    String TeaName;
    String TeaContent;
    int Icon;

    public Tea() {

    }

    public Tea(String TeaName, int Icon, String TeaContent) {
        this.TeaContent = TeaContent;
        this.TeaName = TeaName;
        this.Icon = Icon;
    }

    protected Tea(Parcel in) {
        TeaName = in.readString();
        TeaContent = in.readString();
        Icon = in.readInt();
    }

    public static final Creator<Tea> CREATOR = new Creator<Tea>() {
        @Override
        public Tea createFromParcel(Parcel in) {
            return new Tea(in);
        }

        @Override
        public Tea[] newArray(int size) {
            return new Tea[size];
        }
    };

    public String getTeaName() {
        return TeaName;
    }

    public void setTeaName(String teaName) {
        TeaName = teaName;
    }

    public String getTeaContent() {
        return TeaContent;
    }

    public void setTeaContent(String teaContent) {
        TeaContent = teaContent;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TeaName);
        dest.writeString(TeaContent);
        dest.writeInt(Icon);
    }
}

