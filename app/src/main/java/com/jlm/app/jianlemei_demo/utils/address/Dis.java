package com.jlm.app.jianlemei_demo.utils.address;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zwg on 2016/3/21.
 */
public class Dis implements Parcelable {
    private static final long serialVersionUID = 1L;
    public String Id;
    public String DisName;
    public String CityID;
    public String DisSort;

    public Dis() {
    }

    protected Dis(Parcel in) {
        Id = in.readString();
        DisName = in.readString();
        CityID = in.readString();
        DisSort = in.readString();
    }

    public static final Creator<Dis> CREATOR = new Creator<Dis>() {
        @Override
        public Dis createFromParcel(Parcel in) {
            return new Dis(in);
        }

        @Override
        public Dis[] newArray(int size) {
            return new Dis[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDisName() {
        return DisName;
    }

    public void setDisName(String disName) {
        DisName = disName;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getDisSort() {
        return DisSort;
    }

    public void setDisSort(String disSort) {
        DisSort = disSort;
    }


    @Override
    public String toString() {
        return "Id:" + Id + "/tDisName:" + DisName + "/tCityID:" + CityID + "/tDisSort" + DisSort;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(DisName);
        dest.writeString(CityID);
        dest.writeString(DisSort);
    }
}
