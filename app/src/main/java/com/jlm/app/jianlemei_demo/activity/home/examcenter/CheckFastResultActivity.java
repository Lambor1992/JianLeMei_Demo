package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.db.bean.BloodPressBean;
import com.jlm.app.jianlemei_demo.db.bean.BloodViscosityBean;
import com.jlm.app.jianlemei_demo.db.bean.HeartRateBean;
import com.jlm.app.jianlemei_demo.db.bean.OxygenBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;

import java.sql.SQLException;
import java.util.Calendar;

public class CheckFastResultActivity extends Activity {
    private int heartRate, highRate, bloodY, lowRate;
    private float bloodN;
    private TextView txt_left_back;
    private TextView txt_xy_value, txt_xl_value, txt_nd_value, txt_xyy_value;
    private TextView txt_xy_result, txt_xl_result, txt_nd_result, txt_xyy_result;
    private TextView txt_xy_jf;
    private ImageView img_xy_icon;
    private ImageView img_jb;
    private TextView txt_gx;
    private int sumMax;
    private TextView txt_get_jf, txt_get_num;
    private String user_id;
    private RelativeLayout rel_submit;
    private static final int success = 500;
    private static final int fail = 600;
    private int submitYear, submitMonth, submitDay;
    private int newYear, newMonth, newDay;
    private LinearLayout lin_xy, lin_xl, lin_nd, lin_xyy;
    private TextView txt_dis_tishi;
    private RelativeLayout rel_submit_tishi;

    private DatabaseHelper databaseHelper;
    private Button button_more;//更多结果

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case success:
                    Calendar calendar = Calendar.getInstance();
                    submitYear = calendar.get(Calendar.YEAR);
                    submitMonth = calendar.get(Calendar.MONTH) + 1;
                    submitDay = calendar.get(Calendar.DAY_OF_MONTH);
                    databaseHelper = DatabaseHelper.getInstance(CheckFastResultActivity.this, user_id);
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("year", submitYear);
                    cv.put("month", submitMonth);
                    cv.put("day", submitDay);
                    db.update("subtime", cv, "userid=?", new String[]{user_id});
                    Toast.makeText(CheckFastResultActivity.this, "恭喜您，领取成功！", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case fail:
                    Toast.makeText(CheckFastResultActivity.this, "领取失败！", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_result);
        String UserName = getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString(Mine_userinfoActivity.UserInfo_username, "user1");
        int id = getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getInt(Mine_userinfoActivity.UserInfo_id, 0);

        Intent intent = getIntent();
        heartRate = intent.getIntExtra("heartRate", 0);
        highRate = intent.getIntExtra("highRate", 0);
        bloodN = intent.getFloatExtra("bloodN", 0.5f);
        bloodY = intent.getIntExtra("bloodY", 0);
        lowRate = intent.getIntExtra("lowRate", 0);
        user_id = intent.getStringExtra("userId");
        ExamSQLHelper examSQLHelper = null;
        try {
            examSQLHelper = new ExamSQLHelper(this);
            examSQLHelper.getDao(BloodPressBean.class).create(new BloodPressBean(highRate, lowRate, UserName));
            examSQLHelper = new ExamSQLHelper(this);
            examSQLHelper.getDao(BloodViscosityBean.class).create(new BloodViscosityBean(bloodN, UserName));
            examSQLHelper = new ExamSQLHelper(this);
            examSQLHelper.getDao(OxygenBean.class).create(new OxygenBean(bloodY, UserName));
            examSQLHelper = new ExamSQLHelper(this);
            examSQLHelper.getDao(HeartRateBean.class).create(new HeartRateBean(heartRate, UserName));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (examSQLHelper != null) {
                examSQLHelper.close();
            }
        }


        Log.e("this", heartRate + "=====" + highRate + "======" + bloodN + "======" + bloodY + "======" + lowRate);

        initView();
        setValue();

    }

    private void initView() {

        txt_xy_value = (TextView) findViewById(R.id.txt_xy_value);
        txt_xl_value = (TextView) findViewById(R.id.txt_xl_value);
        txt_nd_value = (TextView) findViewById(R.id.txt_nd_value);
        txt_xyy_value = (TextView) findViewById(R.id.txt_xyy_value);

        txt_xy_result = (TextView) findViewById(R.id.txt_xy_result);
        txt_xl_result = (TextView) findViewById(R.id.txt_xl_result);
        txt_nd_result = (TextView) findViewById(R.id.txt_nd_result);
        txt_xyy_result = (TextView) findViewById(R.id.txt_xyy_result);

        txt_xy_jf = (TextView) findViewById(R.id.txt_xy_jf);
        img_xy_icon = (ImageView) findViewById(R.id.img_xy_icon);
        img_jb = (ImageView) findViewById(R.id.img_jb);
        txt_gx = (TextView) findViewById(R.id.txt_gx);
        txt_get_jf = (TextView) findViewById(R.id.txt_get_jf);
        txt_get_num = (TextView) findViewById(R.id.txt_get_num);
        rel_submit = (RelativeLayout) findViewById(R.id.lin_submit);

        lin_xy = (LinearLayout) findViewById(R.id.lin_xy);
        lin_xl = (LinearLayout) findViewById(R.id.lin_xl);
        lin_nd = (LinearLayout) findViewById(R.id.lin_nd);
        lin_xyy = (LinearLayout) findViewById(R.id.lin_xyy);

        txt_dis_tishi = (TextView) findViewById(R.id.txt_dis_tishi);
        rel_submit_tishi = (RelativeLayout) findViewById(R.id.rel_submit_tishi);
    }


    public void setValue() {
        txt_xy_value.setText(highRate + "/" + lowRate);
        txt_xl_value.setText(heartRate + "");
        txt_nd_value.setText(bloodN + "");
        txt_xyy_value.setText(bloodY + "");

    }
}