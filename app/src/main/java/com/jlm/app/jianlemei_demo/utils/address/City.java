package com.jlm.app.jianlemei_demo.utils.address;

/**
 * Created by zwg on 2016/3/21.
 */
public class City {
    public String CityID;
    public String name;
    public String ProID;
    public String CitySort;
    public City(){
        
    }
    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCitySort() {
        return CitySort;
    }

    public void setCitySort(String citySort) {
        CitySort = citySort;
    }

    public String getProID() {
        return ProID;
    }

    public void setProID(String proID) {
        ProID = proID;
    }
}
