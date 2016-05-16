package com.jlm.app.jianlemei_demo.activity;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.service.WarmingService;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;
import com.jlm.app.jianlemei_demo.utils.WarmingSharePrefrenceHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PromptActivity extends AppCompatActivity {

    private PowerManager pm;
    private PowerManager.WakeLock mWakelock;
    private KeyguardManager km;
    private KeyguardManager.KeyguardLock kl;
    private TextView textView1, textView2, textView3, textView4;
    private Button button;
    private MediaPlayer mediaPlayer;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    String emergency2;
    String emergency1;
    String emergency3;
    String address;
    String Htpaddress;
    String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);
        button = (Button) findViewById(R.id.b);
        textView1 = (TextView) findViewById(R.id.tv1);
        textView2 = (TextView) findViewById(R.id.tv2);
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView1.getTextSize() * 2f);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView2.getTextSize() * 1.8f);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, button.getTextSize() * 1.8f);
        UserName = getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString(Mine_userinfoActivity.UserInfo_realname, "jlmer");
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        mLocationClient.start();
        emergency1 = new WarmingSharePrefrenceHelper(this).getShare().getString(WarmingSharePrefrenceHelper.Emergency1, "");
        emergency2 = new WarmingSharePrefrenceHelper(this).getShare().getString(WarmingSharePrefrenceHelper.Emergency2, "");
        emergency3 = new WarmingSharePrefrenceHelper(this).getShare().getString(WarmingSharePrefrenceHelper.Emergency3, "");
        Log.e("Test__", "__" + emergency1 + "1" + emergency2 + "2" + emergency3);
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music_036);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    if (!"".equals(emergency1)) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + emergency1.split("&&")[1]));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
//                        SmsManager.getDefault().sendTextMessage(emergency1.split("&&")[1], null, emergency1.split("&&")[0] + "\r\n您的家人" + UserName + "在" + address + "摔倒", null, null);
                        VolleySingleton.getVolleySingleton(PromptActivity.this).getRequestQueue().add(
                                new StringRequest(
                                        Htpaddress,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String s) {

                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError volleyError) {

                                            }
                                        }));
                    }
//                    if (!"".equals(emergency2))
//                        SmsManager.getDefault().sendTextMessage(emergency2.split("&&")[1], null, emergency2.split("&&")[0] + "\r\n您的家人" + UserName + "在" + address + "摔倒", null, null);
//                    if (!"".equals(emergency3))
//                        SmsManager.getDefault().sendTextMessage(emergency3.split("&&")[1], null, emergency3.split("&&")[0] + "\r\n您的家人" + UserName + "在" + address + "摔倒", null, null);
                    final TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                    telephonyManager.listen(
                            new PhoneStateListener() {
                                @Override
                                public void onCallStateChanged(int state, String incomingNumber) {
                                    super.onCallStateChanged(state, incomingNumber);
                                    Log.e("Test", "state：" + state + "  incomingNumber:" + incomingNumber);
                                    if (state == TelephonyManager.CALL_STATE_IDLE && incomingNumber.equals(emergency1)) {
                                        Log.e("Test", "挂断或接听");
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_CALL);
                                        intent.setData(Uri.parse("tel:" + "02884558085"));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        telephonyManager.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
                                        finish();
                                    }
                                }
                            },
                            PhoneStateListener.LISTEN_CALL_STATE);
//                    finish();
                }
            });
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromptActivity.this.finish();
            }
        });
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            address = location.getAddrStr();
            try {
                Htpaddress = HttpAdress.BASE_addressAction +
                        "?address=" +
                        URLEncoder.encode(address, "UTF-8") +
                        "&&userid=" +
                        getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getInt(Mine_userinfoActivity.UserInfo_id, 0);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "SimpleTimer");
        mWakelock.acquire();
        km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        kl = km.newKeyguardLock("unLock");
        kl.disableKeyguard();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWakelock.release();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            else
                mediaPlayer.reset();
            mediaPlayer.release();
        }
        startService(new Intent(PromptActivity.this, WarmingService.class));
        super.onDestroy();
    }
}
