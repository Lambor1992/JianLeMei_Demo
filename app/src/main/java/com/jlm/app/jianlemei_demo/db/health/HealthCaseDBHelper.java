package com.jlm.app.jianlemei_demo.db.health;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jlm.app.jianlemei_demo.utils.HealthCase;

/**
 * Created by zwg on 2016/3/10.
 */
public class HealthCaseDBHelper extends SQLiteOpenHelper {
    public static final int Version = 1;
    public static final String DB_NAME = "healthcase.db";
    private static HealthCaseDBHelper helper;

    private HealthCaseDBHelper(Context context) {
        super(context, DB_NAME, null, Version);
    }

    public static HealthCaseDBHelper getInstance(Context context) {
        if (helper == null) return new HealthCaseDBHelper(context);
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getSQLite(HealthCase.EYE, HealthCase.HEALTH_CASE_E.EYE));
        db.execSQL(getSQLite(HealthCase.EAR, HealthCase.HEALTH_CASE_E.EAR));
        db.execSQL(getSQLite(HealthCase.BLOOD, HealthCase.HEALTH_CASE_E.BLOOD));
        db.execSQL(getSQLite(HealthCase.BLOOD2, HealthCase.HEALTH_CASE_E.BLOOD2));
        db.execSQL(getSQLite(HealthCase.PSYCHOLOGY, HealthCase.HEALTH_CASE_E.PSYCHOLOGY));
        db.execSQL(getSQLite(HealthCase.WEIGHT, HealthCase.HEALTH_CASE_E.WEIGHT));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getSQLite(String tablename, String[] num) {
        if (num == null || num.length == 0) return null;
        int j = num.length;
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(tablename);
        sb.append("(_id INTEGER PRIMARY KEY AUTOINCREMENT");
        for (int i = 0; i < j; i++) {
            sb.append(",");
            sb.append(num[i]);
            sb.append(" VARCHAR");
        }
        sb.append(")");
        return sb.toString();
    }
}
