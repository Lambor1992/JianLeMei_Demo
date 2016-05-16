package com.jlm.app.jianlemei_demo.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.jlm.app.jianlemei_demo.data.Integration.java
 * @author: zwg
 * @date: 2016-04-28 15:55
 */
public class Integration {
    private static final String TAG = "Integration";
    public static final String[] project = {"血压测量", "检查视力", "心率测量", "肺活量测量", "每天5000步", "眼保健操", "随机移动", "左右移动", "圆圈聚焦", "降压操", "睡前减肥操"};
    public static final int[] integration = getIngration();
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static int[] getIngration() {
        int[] ints = new int[project.length];
        return ints;
    }

    public static class IntegrationShare {
        private Context context;

        public IntegrationShare(Context context) {
            this.context = context;
        }

        private boolean isToday() {
            return this.context.getSharedPreferences(TAG, Context.MODE_PRIVATE).getString("date", "").equals(format.format(new Date(System.currentTimeMillis())));
        }
        private void commitToNet() {
            VolleySingleton.getVolleySingleton(this.context).getRequestQueue().add(new StringRequest("", new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }));
        }
        public void clear(){
            this.context.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit().clear().apply();
        }
        public boolean add(String z) {
            int userId = context.getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getInt(Mine_userinfoActivity.UserInfo_id, 0);
            if (isToday()) {
                boolean is = this.context.getSharedPreferences(TAG, Context.MODE_PRIVATE).getBoolean(z, false);
                if (is) {
                    return false;
                } else {
                    this.context.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit()
                            .putString("date", format.format(new Date(System.currentTimeMillis())))
                            .putBoolean(z, true)
                            .apply();
                   commitToNet();
                    return true;
                }
            } else {
                clear();
                this.context.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit()
                        .putString("date", format.format(new Date(System.currentTimeMillis())))
                        .putBoolean(z, true)
                        .apply();
                commitToNet();
                return true;
            }
        }
    }


}

enum IntegrationProject {
    a, b, c, d, e, f, h, i, j;
}