package com.jlm.app.jianlemei_demo.activity.health;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.widget.RoundProgressBar;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EarActivity extends Activity {
    private Intent intent;
    @Bind(R.id.health_case_ear_title)
    TextView Title;
    @Bind(R.id.health_case_ear_img)
    ImageView Img;
    @Bind(R.id.health_case_ear_content)
    TextView Content;
    MediaPlayer mediaPlayer = new MediaPlayer();
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case_ear);
        ButterKnife.bind(this);
        intent = getIntent();

        Title.setText(intent.getStringExtra("health"));
        Img.setImageResource(intent.getIntExtra("res", 0));
        Content.setText(intent.getStringExtra("content"));
        final RoundProgressBar Bar = (RoundProgressBar) findViewById(R.id.health_case_ear_round);
        Title.setTextSize(Content.getTextSize() + 0.1f);
        Bar.setProgress(3);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bar.setProgress(2);
            }
        }, 1000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bar.setProgress(1);
            }
        }, 2000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bar.setVisibility(View.GONE);
            }
        }, 3200);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bar.setProgress(0);
                try {
                    AssetFileDescriptor FD = EarActivity.this.getAssets().openFd("protect8_8.mp3");
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
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }
}
