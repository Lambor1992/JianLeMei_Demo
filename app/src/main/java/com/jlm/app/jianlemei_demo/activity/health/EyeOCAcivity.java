package com.jlm.app.jianlemei_demo.activity.health;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.HealthCase;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zwg on 2016/3/28.
 * O means open
 * C means Close
 */
public class EyeOCAcivity extends Activity {
    @Bind(R.id.health_case_eye2_tv)
    TextView Tv;
    @Bind(R.id.health_case_eye2_layout)
    LinearLayout Layout;
    @Bind(R.id.health_case_eye2_img)
    ImageView imageView;
    private Intent intent;
    private Handler mHandler = new Handler();
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case_eye2);
        ButterKnife.bind(this);
        intent = getIntent();
        String str = intent.getStringExtra("health");
        if (str.equals(HealthCase.HEALTH_CASE_C.EYE[1])) {
            Tv.setText("根据听到指令睁开或闭上双眼");
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        AssetFileDescriptor FD = EyeOCAcivity.this.getAssets().openFd("bizheng.mp3");
                        mediaPlayer.setDataSource(FD.getFileDescriptor(), FD.getStartOffset(), FD.getLength());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            setResult(0, intent.putExtra("back", true));
                            finish();
                        }
                    });
                }
            }, 4000);


        } else if (str.equals(HealthCase.HEALTH_CASE_C.EYE[7])) {
            Tv.setText("根据指令眨动双眼");
            mHandler.postDelayed(new Runnable() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void run() {
                    Tv.setVisibility(View.GONE);
                    Layout.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                }
            }, 4000);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Object ob ;
                    AnimationDrawable anim ;
                    ob = imageView.getDrawable();
                    if (ob != null) {
                        anim = (AnimationDrawable) ob;
                        anim.start();
                    }
                }
            }, 5500);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setResult(0, intent.putExtra("back", true));
                    finish();
                }
            }, 60000);

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            try {
                mediaPlayer.reset();
            } catch (IllegalStateException e) {
            } finally {
                mediaPlayer.release();
            }
        }
    }
}
