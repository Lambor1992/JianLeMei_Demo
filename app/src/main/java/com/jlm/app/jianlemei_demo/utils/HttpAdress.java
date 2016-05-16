package com.jlm.app.jianlemei_demo.utils;

/**
 * Created by Administrator on 2016/3/16.
 */
public class HttpAdress {
    public static final String BASE = "http://121.42.169.210:8085/JianLeMei/";
    public static final String BASE_export = BASE + "showConsultAction_showConsult";
    public static final String BASE_ads = BASE + "showNewsAction";
    public static final String BASE_cookbook = BASE + "cookAction_showCookList";
    public static final String BASE_cookbookbyType = BASE + "cookAction_showCookByType?qType=";
    public static final String BASE_consult = BASE + "healthConsultAction_showHealthConsultList";
    public static final String HTTP_loginAction_login = BASE + "loginAction_login";
    public static final String HTTP_findSickaction = BASE + "bodyAction_findSick";
    public static final String HTTP_findAccompany_action = BASE + "bodyAction_findAccompany";
    public static final String HTTP_bodyAction_findSymptom = BASE + "bodyAction_findSymptom";
    public static final String HTTP_getUserInfoByUsername = BASE + "userInfoAction_getUserInfoByUsername";
    public static final String HTTP_registerAction = BASE + "registerAction";
    public static final String BASE_goodsAction = BASE + "goodsAction?qType=";
    public static final String BASE_radioAction = BASE + "radioAction";
    public static final String BASE_noticeAction_sendNotice = BASE + "noticeAction_sendNotice";

    public static final String BASE_commentAction_saveComment = BASE + "commentAction_saveComment";
    public static final String BASE_commentAction_saveCommentForRadio = BASE + "commentAction_saveCommentForRadio";


    public static final String BASE_commentAction_queryAllCommentByDoctorId = BASE + "commentAction_queryAllCommentByDoctorId";
    public static final String BASE_commentAction_queryAllCommentByRadioId = BASE + "commentAction_queryAllCommentByRadioId";
    public static final String BASE_addressAction = BASE + "addressAction";//后面接address= && userid
    public static final String BASE_updateApp = BASE + "appUpdateAction_updateApp";//后面接address= && userid
    public static final String Tel = "4001389589";
}
