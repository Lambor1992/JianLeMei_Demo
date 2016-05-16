package com.jlm.app.jianlemei_demo.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;

/**
 * Created by Administrator on 2016/4/11.
 */
public class SOSDetailActivity extends Activity {
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosdetail);
        imageView = (ImageView) findViewById(R.id.sos_img);
        textView = (TextView) findViewById(R.id.sos_txt);
        Intent intent = getIntent();
        imageView.setImageResource(intent.getIntExtra("pic", 0));
        textView.setText(intent.getStringExtra("txt"));


    }
}
