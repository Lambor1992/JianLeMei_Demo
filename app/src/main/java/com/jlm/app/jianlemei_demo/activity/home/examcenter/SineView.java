package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/4/13.
 */
public class SineView extends View {
    private Paint mPaint = null;
    private static float amplifier = 100.0f;
    private static float frequency = 2.0f;    //2Hz
    private static float phase = 45.0f;         //相位
    private int height = 0;
    private int width = 0;
    private static float px = -1, py = -1;
    private boolean sp = false;
    private Handler handler;
    public int time = 50;
    private float progressbar = 360.0f;

    public SineView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public SineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                phase += 20f;
                postInvalidate();
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(time);
                        handler.sendEmptyMessage(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public SineView(Context context, float amplifier, float frequency, float phase) {
        super(context);
        this.frequency = frequency;
        this.amplifier = amplifier;
        this.phase = phase;
        mPaint = new Paint();
    }

    public void setFrequency(float frequency) {
        SineView.frequency = (float) (2 * Math.log1p(frequency) + 2f);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        height = this.getHeight();
        width = this.getWidth();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        amplifier = height / 2;
        mPaint.setAlpha(200);
        mPaint.setStrokeWidth(3);
        float cy = height / 2;
        for (int i = 0; i < width - 1; i++) {
            canvas.drawLine((float) i, cy - amplifier * (float) (Math.sin(phase * 2 * (float) Math.PI / progressbar + 2 * Math.PI * frequency * i / width)),
                    (float) (i + 1), cy - amplifier * (float) (Math.sin(phase * 2 * (float) Math.PI / progressbar + 2 * Math.PI * frequency * (i + 1) / width)),
                    mPaint);
            float point = cy - amplifier * (float) (Math.sin(phase * 2 * (float) Math.PI / progressbar + 2 * Math.PI * frequency * i / width));
            if ((py >= (point - 2.5f)) && (py <= (point + 2.5f)) && (px >= i - 2.5f) && (px <= i + 2.5f))
                sp = true;
        }
    }
}