package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;

public class Emotion_result_Activity extends AppCompatActivity {

    TextView emotion_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_result);
        emotion_result=(TextView)findViewById(R.id.Emotion_tv);
        Intent intent=getIntent();
        int a=intent.getIntExtra("result",0);
        if (a<=20){
            emotion_result.setText("你情绪良好、自信心强，具有较强的美感、道德感和理智感。你有一定的社会活动能力，能理解周围的人们的心情，顾全大局。你一定是一个性情爽朗、受人欢迎的人。");
        }else if (a>=21&&a<=40){
            emotion_result.setText("情绪基本稳定，但较为深沉，对事情的考虑过于冷静，处事淡漠消极，不善于发挥自己的个性。你的自信心受到压抑，办事热情忽高忽低，易瞻前顾后、踌躇不前。");
        }else if (a>=41&&a<=50){
            emotion_result.setText("情绪不佳，日常烦恼太多，使自己的心情处于紧张和矛盾之中。");
        }else if (a>50){
            emotion_result.setText("危险信号，务必请心理医生作进一步诊断。");
        }
    }
}