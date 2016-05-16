package com.jlm.app.jianlemei_demo.activity.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewTreeObserver;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.HealthCase;
import com.jlm.app.jianlemei_demo.widget.BallView;

/**
 * Created by zwg on 2016/3/28.
 * Open-moving
 */
public class EyeOMAcivity extends Activity {
    private Intent intent;
    private Handler handler = new Handler();
    private long make = 30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_move);
        intent = getIntent();
        String tag = intent.getStringExtra("health");
        final BallView ballView = (BallView) this.findViewById(R.id.health_case_eye_move);
        ballView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ballView.setBankgroundBounds(ballView.getWidth(), ballView.getHeight());
            }
        });
        if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[3])) {
            //随机移动2
            ballView.setState(2);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[4])) {
            //左右移动1
            ballView.setState(0);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[5])) {
            //上下移动0
            ballView.setState(1);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setResult(0, intent.putExtra("back", true));
                finish();
            }
        }, make);
    }
}
