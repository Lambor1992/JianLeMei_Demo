package com.jlm.app.jianlemei_demo.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Vibrator;

public class ToolUtil {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static void saveSeesion(String id, String str, Context content) {
        // 实例化SharedPreferences对象,参数1是存储文件的名称，参数2是文件的打开方式，当文件不存在时，直接创建，如果存在，则直接使用
        SharedPreferences mySharePreferences = content.getSharedPreferences(
                "health", Activity.MODE_PRIVATE);
        // 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = mySharePreferences.edit();
        // 用putString的方法保存数据
        editor.putString(id, str);
        // 提交数据
        editor.commit();

    }

    public static void saveSeesionInt(String id, int position, Context content) {
        // 实例化SharedPreferences对象,参数1是存储文件的名称，参数2是文件的打开方式，当文件不存在时，直接创建，如果存在，则直接使用
        SharedPreferences mySharePreferences = content.getSharedPreferences(
                "health", Activity.MODE_PRIVATE);
        // 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = mySharePreferences.edit();
        // 用putString的方法保存数据
        editor.putInt(id, position);
        // 提交数据
        editor.commit();

    }

    public static String getSession(Context context, String id) {
        SharedPreferences mySharePerferences = context.getSharedPreferences(
                "health", Activity.MODE_PRIVATE);
        // 用getString获取值
        String userID = mySharePerferences.getString(id, "");
        return userID;
    }

    public static int getSessionInt(Context context, String id) {
        SharedPreferences mySharePerferences = context.getSharedPreferences(
                "health", Activity.MODE_PRIVATE);
        // 用getString获取值
        int position = mySharePerferences.getInt(id, 0);
        return position;
    }

    /**
     * 获取apk的版本号 currentVersionCode
     *
     * @param ctx
     * @return
     */
    public static int getAPPVersionCodeFromAPP(Context ctx) {
        int currentVersionCode = 0;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            String appVersionName = info.versionName; // 版本名
            currentVersionCode = info.versionCode; // 版本号
            System.out.println(currentVersionCode + " " + appVersionName);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch blockd
            e.printStackTrace();
        }
        return currentVersionCode;
    }

    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }

    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    /**
     * 15.     * final Activity activity  ：调用该方法的Activity实例
     * 16.     * long milliseconds ：震动的时长，单位是毫秒
     * 17.     * long[] pattern  ：自定义震动模式 。数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
     * 18.     * boolean isRepeat ： 是否反复震动，如果是true，反复震动，如果是false，只震动一次
     * 19.
     */

    public static void Vibrate(final Context activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity
                .getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    public static void Vibrate(final Activity activity, long[] pattern,
                               boolean isRepeat) {
        Vibrator vib = (Vibrator) activity
                .getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
    }
}