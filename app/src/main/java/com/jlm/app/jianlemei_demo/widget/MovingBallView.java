package com.jlm.app.jianlemei_demo.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;

/**
 * Created by Administrator on 2016/3/30.
 */
public class MovingBallView extends View {
    public static Block block;

    public Handler handler;
    //定义方向
    public static int dir = -1;
    //上下左右
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;
    public static final int RADOM = 0;
    private int w;
    private int h;

    public MovingBallView(Context context, int w, int h) {
        super(context);
        this.w = w;
        this.h = h;
        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case HORIZONTAL:
                        MovingBallView.block.moveHORIZONTAL();
                        break;
                    case VERTICAL:
                        MovingBallView.block.moveVERTICAL();
                        break;
                    case RADOM:
                        break;
                    default:
                        MovingBallView.block.RADOM();
                        break;
                }
            }

        };
    }

    enum MoveState {
        HORIZONTAL, VERTICAL, RADOM
    }

    class Block {
        private int x = 20, y = 20;
        public MovingBallView movingBallView;

        public Block(MovingBallView movingBallView) {
            this.movingBallView = movingBallView;
        }

        //向左移动
        public void moveHORIZONTAL() {
            x += 10;
            movingBallView.invalidate();
        }

        //向右移动
        public void moveVERTICAL() {
            y += 10;
            movingBallView.invalidate();
        }

        //下落方法
        public void RADOM() {
            x = (int) (Math.random() * w);
            y = (int) (Math.random() * h);
            movingBallView.invalidate();//重新绘制
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}