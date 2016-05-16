package com.jlm.app.jianlemei_demo.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by admin on 2016/4/7.
 */
public class WarmingService extends Service implements SensorEventListener {
    private static final int UPTATE_INTERVAL_TIME = 100;
    private long lastUpdateTime = 0;
    private SensorManager sensorManager;
    private boolean flag = true;
    private double lastaa = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        flag = true;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {// 取消监听器
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            synchronized (this) {
                long currentUpdateTime = System.currentTimeMillis();
                long timeInterval = currentUpdateTime - lastUpdateTime;
                double aa;
                if (flag) {
                    if (timeInterval > UPTATE_INTERVAL_TIME) {
                        lastUpdateTime = currentUpdateTime;
                        double a = Math.sqrt(Math.abs(event.values[0])
                                * Math.abs(event.values[0]) + Math.abs(event.values[1])
                                * Math.abs(event.values[1]) + Math.abs(event.values[2])
                                * Math.abs(event.values[2]));
                        aa = a - 9.8f;
                        if (lastaa == 0) {
                            lastaa = aa;
                            return;
                        }
                        if (Math.abs(aa) > 7.0)
                            Log.e("Test", "First________aa_________" + aa + ">>>" + (Math.abs(aa) > 7.0)
                                            + "\nFirst__values0>:-----[" + event.values[0] + ">>>" + (Math.abs(event.values[0]) < 1)
                                            + "\nFirst__values1>:-----[" + event.values[1] + ">>>" + (event.values[1] > -5.0 || Math.abs(event.values[1]) < 1.5)
                                            + "\nFirst__values2>:-----[" + event.values[2] + ">>>" + (Math.abs(event.values[2]) < 1.0 || Math.abs(event.values[2]) > 15)
                                            + "\nFirst__event.values[0] * event.values[1])_______>>>:" + (Math.abs(event.values[0] * event.values[1]) < 0.45)
                            );
                        if ((Math.abs(event.values[0]) < 1.0)
                                && (event.values[1] > -5.0 || Math.abs(event.values[1]) < 1.5)
                                && (Math.abs(event.values[2]) < 1.0 || Math.abs(event.values[2]) > 15)) {
                            if (Math.abs(event.values[0] * event.values[1]) < 0.45) {
                                Log.e("Test", "Secend____________________________" + "aa:" + aa
                                        + "\nSecend____________________________" + " lastaa:" + lastaa);
                                lastaa = aa;
                                Intent intent = new Intent();
                                intent.setAction("com.jlm.warming");
                                sendBroadcast(intent);
                                flag = false;
                            }
                        }
                    }
                }
//            list.remove(0);
//            list.add((float) Math.sqrt(Math.pow(event.values[0], 2) + Math.pow(event.values[1], 2) + Math.pow(event.values[2], 2)));
//            float tag1 = (list.get(0) - list.get(1)) / list.get(0);
//            float tag2 = (list.get(1) - list.get(2)) / list.get(1);
//            float tag3 = (list.get(2) - list.get(3)) / list.get(2);
//            float tag4 = (list.get(3) - list.get(4)) / list.get(3);
//            if (tag1 > 0.2f && event.values[2] > 3) {
//                if (tag2 > 0.1f && (tag3 > 0.2f || tag4 > 0.1f)) {
//                    if (flag) {
//                        Intent intent = new Intent();
//                        intent.setAction("com.jlm.warming");
//                        sendBroadcast(intent);
//                        flag = false;

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}

