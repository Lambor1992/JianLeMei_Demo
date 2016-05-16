package com.jlm.app.jianlemei_demo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/10.
 */
public class HealthCase {
    public static final String MAKE = "MADE_BY_Z";
    public static final String EYE = "EYE";
    public static final String EAR = "EAR";
    public static final String BLOOD = "BLOOD";
    public static final String BLOOD2 = "BLOOD2";
    public static final String WEIGHT = "WEIGHT";
    public static final String PSYCHOLOGY = "PSYCHOLOGY";
    public static final int[] CASE_NUM = {1000, 1001, 1002, 1003, 1004, 1005};
    public static final String[] CASE_NAME = {"EYE", "EAR", "BLOOD", "BLOOD", "WEIGHT", "PSYCHOLOGY"};

    public static class HEALTH_CASE_C {
        public static final String[] EYE = {"眼保健操", "紧闭双眼", "闭眼移动", "随机移动", "左右移动", "上下移动", "圆圈聚焦", "眨眼润眼", "两个物体"};
        public static final String[] EAR = {"捏耳廓", "捏耳屏", "松耳廓", "拧耳朵", "拉耳廓"};
        public static final String[] BLOOD = {"降压操", "降压运动", "足浴", "茶疗"};
        public static final String[] BLOOD2 = {};
        public static final String[] WEIGHT = {"快速减肥操", "睡前减肥操", "运动"};
        public static final String[] PSYCHOLOGY = {"抑郁症自我治疗", "自闭症治疗", "抗压的方法", "运动"};
    }

    /*请自行对照上面*/
    public static class HEALTH_CASE_E {
        public static final String[] EYE = {"EYE_a", "EYE_b", "EYE_c", "EYE_d", "EYE_e", "EYE_f", "EYE_g", "EYE_h", "EYE_i"};
        public static final String[] EAR = {"EAR_1", "EAR_2", "EAR_3", "EAR_4", "EAR_5"};
        public static final String[] BLOOD = {"BLOOD_1", "BLOOD_2", "BLOOD_3", "BLOOD_4"};
        public static final String[] BLOOD2 = {};
        public static final String[] WEIGHT = {"WEIGHT_1", "WEIGHT_2", "WEIGHT_3", "WEIGHT_4"};
        public static final String[] PSYCHOLOGY = {"PSYCHOLOGY_1", "PSYCHOLOGY_2", "PSYCHOLOGY_3", "PSYCHOLOGY_4"};
    }

    public static class HEALTH_CASE_MAP {
        public static final Map<String, String> EYE = getMAP(HEALTH_CASE_C.EYE, HEALTH_CASE_E.EYE);
        public static final Map<String, String> EAR = getMAP(HEALTH_CASE_C.EAR, HEALTH_CASE_E.EAR);
        public static final Map<String, String> BLOOD = getMAP(HEALTH_CASE_C.BLOOD, HEALTH_CASE_E.BLOOD);
        public static final Map<String, String> BLOOD2 = getMAP(HEALTH_CASE_C.BLOOD2, HEALTH_CASE_E.BLOOD2);
        public static final Map<String, String> WEIGHT = getMAP(HEALTH_CASE_C.WEIGHT, HEALTH_CASE_E.WEIGHT);
        public static final Map<String, String> PSYCHOLOGY = getMAP(HEALTH_CASE_C.PSYCHOLOGY, HEALTH_CASE_E.PSYCHOLOGY);
    }

    protected final static HashMap<String, String> getMAP(String[] C, String[] E) {
        if (C.length != E.length) return null;
        int j = C.length;
        HashMap<String, String> map = new HashMap<>(j);
        for (int i = 0; i < j; i++) {
            map.put(C[i], E[i]);
        }
        return map;
    }


}
