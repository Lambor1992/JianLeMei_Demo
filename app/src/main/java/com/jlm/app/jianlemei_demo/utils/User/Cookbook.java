package com.jlm.app.jianlemei_demo.utils.User;

import java.util.List;




/**
 * Created by Administrator on 2016/3/29.
 */
public class Cookbook {
    public int id;
    //食谱名
    public String title;
    //小图片的地址  用来在列面中展示
    public String littlePic;
    //大图片的地址 所组成的ArrayList 用来在内容中展示,可鞥有多张
    public List<Object> bigPic;
    // 总观看人数，在进入详细界面时增加1
    public Integer totalView;
    //回复人数
    public Integer review;
    //是否是视力保健食谱
    public boolean isVision;
    //是否是听力保健食谱
    public boolean isAudiology;
    //是否是控制血压的食谱
    public boolean isBloodPress;
    private String address;
    private String cookhtml;
    public int getId() {
		return id;
	}

	public String getCookhtml() {
		return cookhtml;
	}

	public void setCookhtml(String cookhtml) {
		this.cookhtml = cookhtml;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLittlePic() {
		return littlePic;
	}

	public void setLittlePic(String littlePic) {
		this.littlePic = littlePic;
	}

	public List<Object> getBigPic() {
		return bigPic;
	}

	public void setBigPic(List<Object> bigPic) {
		this.bigPic = bigPic;
	}

	public Integer getTotalView() {
		return totalView;
	}

	public void setTotalView(Integer totalView) {
		this.totalView = totalView;
	}

	public Integer getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	public boolean isVision() {
		return isVision;
	}

	public void setVision(boolean isVision) {
		this.isVision = isVision;
	}

	public boolean isAudiology() {
		return isAudiology;
	}

	public void setAudiology(boolean isAudiology) {
		this.isAudiology = isAudiology;
	}

	public boolean isBloodPress() {
		return isBloodPress;
	}

	public void setBloodPress(boolean isBloodPress) {
		this.isBloodPress = isBloodPress;
	}

	public boolean isBloodSugar() {
		return isBloodSugar;
	}

	public void setBloodSugar(boolean isBloodSugar) {
		this.isBloodSugar = isBloodSugar;
	}

	public boolean isWeightControl() {
		return isWeightControl;
	}

	public void setWeightControl(boolean isWeightControl) {
		this.isWeightControl = isWeightControl;
	}

	public boolean isPsychologicalImprovement() {
		return isPsychologicalImprovement;
	}

	public void setPsychologicalImprovement(boolean isPsychologicalImprovement) {
		this.isPsychologicalImprovement = isPsychologicalImprovement;
	}

	public String getUploadData() {
		return uploadData;
	}

	public void setUploadData(String string) {
		this.uploadData = string;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	//是否是控制血糖的食谱
    public boolean isBloodSugar;
    //是否是体重控制的食谱
    public boolean isWeightControl;
    //s是否是心理控制的食谱
    public boolean isPsychologicalImprovement;
    //上传时所在时间，System.currentTimeMillis();直接获取
    public String uploadData;
    //介绍
    public String introduction;
    //用料 包括主料 辅料 json格式 Arrayjson
    public String materials;
    //制作方法
    public String method;
    //小贴士
    public String tips;

   

  
}
