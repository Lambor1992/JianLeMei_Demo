package com.jlm.app.jianlemei_demo.db.health;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jlm.app.jianlemei_demo.db.bean.BMIBean;
import com.jlm.app.jianlemei_demo.db.bean.BloodPressBean;
import com.jlm.app.jianlemei_demo.db.bean.BloodViscosityBean;
import com.jlm.app.jianlemei_demo.db.bean.HearingBean;
import com.jlm.app.jianlemei_demo.db.bean.HeartRateBean;
import com.jlm.app.jianlemei_demo.db.bean.HeightBean;
import com.jlm.app.jianlemei_demo.db.bean.OxygenBean;
import com.jlm.app.jianlemei_demo.db.bean.RespiratoryRateBean;
import com.jlm.app.jianlemei_demo.db.bean.TemperatureBean;
import com.jlm.app.jianlemei_demo.db.bean.VisionBean;
import com.jlm.app.jianlemei_demo.db.bean.VitalCapacityBean;
import com.jlm.app.jianlemei_demo.db.bean.WeightBean;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/4/8.
 */
public class ExamSQLHelper extends OrmLiteSqliteOpenHelper {
    private static final String databaseName = "examdata.db";
    private static final int databaseVersion = 1;

    public ExamSQLHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, BloodPressBean.class);
            Log.e("Test", "BloodPressBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, BloodViscosityBean.class);
            Log.e("Test", "BloodViscosityBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, BMIBean.class);
            Log.e("Test", "BMIBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, HearingBean.class);
            Log.e("Test", "HearingBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, HeartRateBean.class);
            Log.e("Test", "HeartRateBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, OxygenBean.class);
            Log.e("Test", "OxygenBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, RespiratoryRateBean.class);
            Log.e("Test", "RespiratoryRateBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, TemperatureBean.class);
            Log.e("Test", "TemperatureBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, VisionBean.class);
            Log.e("Test", "VisionBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, VitalCapacityBean.class);
            Log.e("Test", "VitalCapacityBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, WeightBean.class);
            Log.e("Test", "WeightBean" + "创建成功");

            TableUtils.createTableIfNotExists(connectionSource, HeightBean.class);
            Log.e("Test", "HeightBean" + "创建成功");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//        try {
//            TableUtils.dropTable(connectionSource, BloodPressBean.class, true);
//            onCreate(database, connectionSource);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


    @Override
    public void close() {
        super.close();
    }
}
