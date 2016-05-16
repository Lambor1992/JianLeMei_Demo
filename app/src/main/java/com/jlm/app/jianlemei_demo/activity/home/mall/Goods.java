package com.jlm.app.jianlemei_demo.activity.home.mall;

/**
 * Created by Administrator on 2016/4/15.
 */
public class Goods {
    private Integer id;
    private String name;
    private Float price;
    private Float discountprice;//折扣
    private String dicountReason;
    private String describe;//简介
    private String picture;
    private String gid;//商品号
    private Integer type;
    private Integer qType;
    private Boolean isHot;
    private String detailAddress;
    private Boolean isDiscount;
    private Boolean isCanuseEntegral;
    private Boolean isNew;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public Boolean getIsHot() {
        return isHot;
    }
    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }
    public String getDetailAddress() {
        return detailAddress;
    }
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
    public Boolean getIsDiscount() {
        return isDiscount;
    }
    public void setIsDiscount(Boolean isDiscount) {
        this.isDiscount = isDiscount;
    }
    public Boolean getIsCanuseEntegral() {
        return isCanuseEntegral;
    }
    public void setIsCanuseEntegral(Boolean isCanuseEntegral) {
        this.isCanuseEntegral = isCanuseEntegral;
    }
    public Boolean getIsNew() {
        return isNew;
    }
    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public String getGid() {
        return gid;
    }
    public void setGid(String gid) {
        this.gid = gid;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    public void setDiscountprice(Float discountprice) {
        this.discountprice = discountprice;
    }
    public Float getDiscountprice() {
        return discountprice;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getPicture() {
        return picture;
    }
    public void setqType(Integer qType) {
        this.qType = qType;
    }
    public Integer getqType() {
        return qType;
    }
    public void setDicountReason(String dicountReason) {
        this.dicountReason = dicountReason;
    }
    public String getDicountReason() {
        return dicountReason;
    }
}

