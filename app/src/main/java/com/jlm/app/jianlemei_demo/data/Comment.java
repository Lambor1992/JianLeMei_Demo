package com.jlm.app.jianlemei_demo.data;

import java.util.Date;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.jlm.app.jianlemei_demo.data.Comment.java
 * @author: zwg
 * @date: 2016-04-27 11:34
 */
public class Comment {
    private Integer id;
    private String content;
    private Integer userId;
    private Integer doctorId;
    //private Integer ReplyId;
    private Date creatTime;
    private String author;
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "Comment [id=" + id + ", content=" + content + ", userId="
                + userId + ", doctorId=" + doctorId + ", creatTime="
                + creatTime + "]";
    }
    public Integer getId() {
        return id;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
    public Date getCreatTime() {
        return creatTime;
    }
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
