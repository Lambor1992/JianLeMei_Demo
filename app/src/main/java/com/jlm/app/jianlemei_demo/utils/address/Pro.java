package com.jlm.app.jianlemei_demo.utils.address;

/**
 * Created by zwg on 2016/3/21.
 */
public class Pro {
    public String ProID;
    public String name;
    public String ProSort;
    public String Promark;

    public Pro() {
    }

    public String getProID() {
        return ProID;
    }

    public void setProID(String proID) {
        ProID = proID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProSort() {
        return ProSort;
    }

    public void setProSort(String proSort) {
        ProSort = proSort;
    }

    public String getPromark() {
        return Promark;
    }

    public void setPromark(String promark) {
        Promark = promark;
    }

    @Override
    public String toString() {
        return "ProID:" + getProID() + "<->name:" + name + "<->ProSort:" + ProSort + "<->Peomark:" + Promark;
    }
}
