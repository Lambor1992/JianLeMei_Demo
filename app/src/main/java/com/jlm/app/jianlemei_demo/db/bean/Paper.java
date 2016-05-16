package com.jlm.app.jianlemei_demo.db.bean;

import java.io.Serializable;
/*
 * 长寿报实体
 * yhz 2015.11.9
 */
public class Paper implements Serializable{
	private int paperId;//id
	private String paperTitle;//标题
	private  int expertId;
	private String paperImg;//图片
	private String paperContext;//内容
	private int adId;
	public String getExpert() {
		return expert;
	}
	public void setExpert(String expert) {
		this.expert = expert;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	private int paperIshow;//是否是最新
	private String paperTime;//时间
	private String userInvitcode;//邀请码
	private String paperRemarks;//备注
	private String expert;//
	private String ad;
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public int getExpertId() {
		return expertId;
	}
	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}
	public String getPaperImg() {
		return paperImg;
	}
	public void setPaperImg(String paperImg) {
		this.paperImg = paperImg;
	}
	public String getPaperContext() {
		return paperContext;
	}
	public void setPaperContext(String paperContext) {
		this.paperContext = paperContext;
	}
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public int getPaperIshow() {
		return paperIshow;
	}
	public void setPaperIshow(int paperIshow) {
		this.paperIshow = paperIshow;
	}
	public String getPaperTime() {
		return paperTime;
	}
	public void setPaperTime(String paperTime) {
		this.paperTime = paperTime;
	}
	public String getUserInvitcode() {
		return userInvitcode;
	}
	public void setUserInvitcode(String userInvitcode) {
		this.userInvitcode = userInvitcode;
	}
	public String getPaperRemarks() {
		return paperRemarks;
	}
	public void setPaperRemarks(String paperRemarks) {
		this.paperRemarks = paperRemarks;
	}
	
}
