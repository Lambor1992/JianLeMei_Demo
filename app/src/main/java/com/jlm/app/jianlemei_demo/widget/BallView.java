package com.jlm.app.jianlemei_demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.jlm.app.jianlemei_demo.R;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/3/30.
 */
public class BallView extends View {
    private Paint paint;        //定义画笔
    private float x = 50;      //圆点默认X坐标
    private float y = 50;      //圆点默认Y坐标
    private int r = 40;         //圆点默认半径
    private int ballColour = Color.RED; //圆点默认颜色
    private int STEPLENGTH = 10;
    private int STEPLENGTH2 = 2;

    public int getState() {
        return state;
    }

    private int state;
    private int time;
    private final Handler handler;

    public void setState(final int state) {
        this.state = state;


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(time);
                        handler.sendEmptyMessage(state);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /*
    w和h是父控件的宽和高
     */
    private int w;
    private int h;

    public BallView(Context context, Handler handler) {
        super(context);
        this.handler = new MyHanlder(context);
    }

    public BallView(Context context, AttributeSet attr) {
        super(context, attr);
        initAttributeSet(context, attr);
        initPaint();
        handler = new MyHanlder(context);

    }

    public void setBankgroundBounds(int width, int hight) {
        this.w = width - 2 * r;
        this.h = hight - 2 * r;
        switch (state) {
            case 0:
                STEPLENGTH = width / 120;
                STEPLENGTH2 = hight / 600;
                break;
            case 1:
                STEPLENGTH = width / 300;
                STEPLENGTH2 = hight / 90;
                break;
            case 2:
                time *= 5;
                break;
        }
    }

    private void initAttributeSet(Context context, AttributeSet attr) {
        TypedArray a = context.obtainStyledAttributes(attr, R.styleable.BallView);
        x = a.getInt(R.styleable.BallView_ballx, 50);
        y = a.getInt(R.styleable.BallView_bally, 50);
        r = a.getInt(R.styleable.BallView_ballr, 20);
        ballColour = a.getColor(R.styleable.BallView_ballColour, Color.RED);
        STEPLENGTH = a.getInt(R.styleable.BallView_moveDistance, 10);
        STEPLENGTH2 = a.getInt(R.styleable.BallView_moveSecendType, 2);
        state = a.getInt(R.styleable.BallView_moveType, 0);
        time = a.getInt(R.styleable.BallView_moveTime, 500);
        a.recycle();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ballColour);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (x < r) {
            x = r;
        }
        if (y < r) {
            y = r;
        }
        canvas.drawCircle(x, y, r, paint);
    }

    class MyHanlder extends Handler {
        WeakReference<Context> mActivity;

        public MyHanlder(Context context) {
            this.mActivity = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Figure();
                    postInvalidate();
                    break;
                case 1:
                    Figure();
                    postInvalidate();
                    break;
                case 2:
                    x = (float) (Math.random() * w);
                    y = (float) (Math.random() * h);
                    postInvalidate();
                    break;
            }
        }

        public void Figure() {
            if (((int) (x += STEPLENGTH)) > w) {
                STEPLENGTH = 0 - STEPLENGTH;
                x += (2 * STEPLENGTH);
            } else if (((int) (x += STEPLENGTH)) < r) {
                STEPLENGTH = 0 - STEPLENGTH;
                x += (2 * STEPLENGTH);
            } else {
                x += STEPLENGTH;
            }
            if (((int) (y += STEPLENGTH2)) > h) {
                STEPLENGTH2 = 0 - STEPLENGTH2;
                y += (2 * STEPLENGTH2);
            } else if (((int) (y += STEPLENGTH2)) < r) {
                STEPLENGTH2 = 0 - STEPLENGTH2;
                y += (2 * STEPLENGTH2);
            } else {
                y += STEPLENGTH2;
            }
        }
    }
}