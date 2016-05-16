package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;

public class EarExamActivity extends Activity {
    SeekBar seekBar;
    SineView sineView;
    AudioTrackManager audioTrackManager;
    Button hear_can, hear_cannot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ear_test);
        sineView = (SineView) findViewById(R.id.test);
        audioTrackManager = new AudioTrackManager();
        seekBar = (SeekBar) findViewById(R.id.seekbar3);

        hear_can = (Button) findViewById(R.id.hear_can);
        hear_cannot = (Button) findViewById(R.id.hear_cannot);


        hear_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EarExamActivity.this, "您听力很好，请注意保持", Toast.LENGTH_SHORT).show();
            }
        });

        hear_cannot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EarExamActivity.this, "您听力有所衰退，请注意听力保健", Toast.LENGTH_SHORT).show();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sineView.setFrequency(progress * 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                paly(seekBar.getProgress() * 2);
            }
        });

    }





    public void paly(int i) {
        audioTrackManager.start(i);
        audioTrackManager.play();
    }


    public void test(View view) {
        paly(seekBar.getProgress());
    }
}
