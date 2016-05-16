package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;


public class VitalcapacityActivity extends Activity {

    private int count = 0;
    public static float sum = 0;// 总肺活量
    public int imagenum = 1;
    private TextView tv_show_heal_pulmonary;// 肺活量显示
    private boolean startFlag = false;// 吹气开始
    private static String postSum = "0";// 完成上传后的数据
    Feihuoliang_Thread rThread = null;
    private ImageView img_fengche;
    private AnimationDrawable anim;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feihuoliang);
        img_fengche = (ImageView) findViewById(R.id.img_fengche);
        tv_show_heal_pulmonary = (TextView) findViewById(R.id.tv_show_heal_pulmonary);
        img_fengche.setBackgroundResource(R.drawable.fengche_donghua);
        anim = (AnimationDrawable) img_fengche.getBackground();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if (rThread.isAlive()) {
            try {
                rThread.stop();
            } catch (Exception e) {
                // throw new RuntimeException(e);
            }
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        rThread = new Feihuoliang_Thread(mhandle);
        if (!rThread.isAlive()) {
            rThread.start();
            anim.stop();
        }
        super.onStart();
    }

    @Override
    protected void onPause() {
        rThread.pause();
        if (rThread.isAlive()) {
            rThread.pause();
        }
        super.onPause();
    }

    public Handler mhandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String vmsg = (String) msg.obj;
                    double fs = Double.parseDouble(vmsg);
                    System.out.println("当前的fs：" + fs);
                    if (fs > 3100) {
                        startFlag = true;
                        anim.start();
                    }
                    if (startFlag) {
                        if (fs < 6000) {
                            anim.start();
                            if (fs < 5000) {
                                if (fs < 4000) {
                                    sum += 10;
                                } else {
                                    sum += 25;
                                }
                            } else {
                                sum += 35;
                            }
                        } else {
                            sum += 50;
                        }
                        if (sum > 100) {
                            tv_show_heal_pulmonary.setText(sum + "");
                        }
                    }
                    if (fs < 2900) {
                        if (startFlag) {
                            if (sum > 100) {

                                anim.stop();
                                Toast.makeText(getApplicationContext(),
                                        "恭喜您，肺活量结束！", Toast.LENGTH_SHORT).show();


                            }
                            sum = 0;
                            startFlag = false;
                            anim.stop();
                        }
                    }


            }
        }

    };

}
