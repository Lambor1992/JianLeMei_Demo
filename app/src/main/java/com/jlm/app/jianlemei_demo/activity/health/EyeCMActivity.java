package com.jlm.app.jianlemei_demo.activity.health;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.jlm.app.jianlemei_demo.R;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/28.
 */
public class EyeCMActivity extends Activity {
    private Handler mHandler = new Handler();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int max = 15;
    private String[] list = new String[max * 2];
    private String up = "cc_up.mp3";
    private String down = "cc_down.mp3";
    private String right = "cc_right.mp3";
    private String left = "cc_left.mp3";
    private String blank = "blank.mp3";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case_eye3);
        intent = getIntent();
        setMediaPlayer();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initPlayer(0);
            }
        }, 5000);


    }

    public void setMediaPlayer() {
        int len = max * 2;
        for (int i = 0; i < len; i++) {
            if (i % 2 == 1) {
                list[i] = blank;
            } else
                switch ((int) (Math.random() * 4)) {
                    case 0:
                        list[i] = up;
                        break;
                    case 1:
                        list[i] = down;
                        break;
                    case 2:
                        list[i] = right;
                        break;
                    case 3:
                        list[i] = left;
                        break;
                }
        }
    }

    public void initPlayer(final int index) {
        if (index == list.length) {
            if (mediaPlayer != null) {
                mediaPlayer.reset();
                mediaPlayer.release();
            }
            setResult(0, intent.putExtra("back", true));
            finish();
            return;
        }
        try {
            mediaPlayer.reset();
            mediaPlayer = new MediaPlayer();
            AssetFileDescriptor FD_1 = getAssets().openFd(list[index]);
            mediaPlayer.setDataSource(FD_1.getFileDescriptor(), FD_1.getStartOffset(), FD_1.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    initPlayer(index + 1);
                }
            });
            mediaPlayer.start();
        } catch (NullPointerException | IllegalStateException e) {
        } catch (IOException e) {
            e.printStackTrace();
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
