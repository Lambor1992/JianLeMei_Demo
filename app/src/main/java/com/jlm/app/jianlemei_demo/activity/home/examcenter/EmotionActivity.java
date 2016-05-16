package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;

public class EmotionActivity extends AppCompatActivity {

    public final static String[] PSYCHIC_EMOTION_QUESTION = {"1.看到自己最近一次拍摄的照片,你有何想法?",
            "2.你是否想到若干年之后会有什么使自己极为不安的事?",
            "3.你是否被朋友、同事或同学起过绰号、挖苦过?",
            "4.你上床以后,是否经常再起来一次,看看门窗是否关好,水龙头是否拧紧等?",
            "5.你对与你关系最密切的人是否满意?",
            "6.半夜的时候,你是否经常觉得有什么值得害怕的事?",
            "7.你是否经常因梦见什么可怕的事而惊醒?",
            "8.你是否曾经有多次做同一个梦的情况?",
            "9.有没有一种食物使你吃后呕吐?",
            "10.除去看见的世界外,你心里有没有另外的世界?",
            "11.你心里是否时常觉得你不是现在的父母所生?",
            "12.你是否曾经觉得有一个人爱你或尊重你?",
            "13.你是否常常觉得你的家庭对你不好,但是你又的确知道他们实际上对你很好?",
            "14.你是否觉得没有人十分了解你?",
            "15.你在早晨起来的时候最经常的感觉是什么?",
            "16.每到秋天,你经常的感觉是什么?",
            "17.你在高处的时候,是否觉得站不稳?",
            "18.你平时是否觉得自己很强健?",
            "19.你是否一回家就立刻把房门关上?",
            "20.你坐在小房间里把门关上后,是否觉得心里不安?",
            "21.当一件事需要你作决定时,你是否觉得很难?",
            "22.你是否常常用抛硬币、翻纸牌、抽签之类的游戏来测凶吉?",
            "23.你是否常常因为碰到东西而跌倒?",
            "24.你是否需要一个多小时才能入睡,或醒的比你希望的早一个小时?",
            "25.你是否曾看到、听到或感觉到别人觉察不到的东西?",
            "26.你是否觉得自己有超乎常人的能力?",
            "27.你是否曾经觉得因有人跟着你走而心里不安?",
            "28.你是否觉得有人在注意你的言行?",
            "29.当你一个人走夜路时,是否觉得前面暗藏着危险?",
            "30.你对比人自杀有什么想法?"};
    public final static String[][] PSYCHIC_EMOTION_ANSWERS = {{"A.觉得不称心", "B.觉得很好", "C.觉得可以"},
            {"A.经常想到", "B.从来没有想过", "C.偶尔想到过"},
            {"A.这是常有的事", "B.从来没有", "C.偶尔有过"},
            {"A.经常如此", "B.从不如此", "C.偶尔如此"},
            {"A.不满意", "B.非常满意", "C.基本满意"},
            {"A.经常", "B.从来没有", "极少有这种情况"},
            {"A.经常", "B.没有", "C.极少"},
            {"A.有", "B.没有", "C.记不清"},
            {"A.有", "B.没有", "C.记不清"},
            {"A.有", "B.没有", "C.记不清"},
            {"A.时常", "B.没有", "C.偶尔有"},
            {"A.是", "B.否", "C.说不清"},
            {"A.是", "B.否", "C.偶尔"},
            {"A.是", "B.否", "C.说不清楚"},
            {"A.忧郁", "B.快乐", "C.说不清楚"},
            {"A.秋雨霏霏或枯叶遍地", "B.秋高气爽或艳阳天", "C.不清楚"},
            {"A.是", "B.否", "C.有时是这样"},
            {"A.否", "B.是", "C.不清楚"},
            {"A.是", "B.否", "C.不清楚"},
            {"A.是", "B.否", "C.偶尔是"},
            {"A.是", "B.否", "C.偶尔是"},
            {"A.是", "B.否", "C.偶尔"},
            {"A.是", "B.否", "C.偶尔"},
            {"A.经常这样", "B.从不这样", "C.偶尔这样"},
            {"A.经常这样", "B.从不这样", "C.偶尔这样"},
            {"A.是", "B.否", "C.不清楚"},
            {"A.是", "B.否", "C.不清楚"},
            {"A.是", "B.否", "C.不清楚"},
            {"A.是", "B.否", "C.偶尔"},
            {"A.可以理解", "B.否、不可思议", "C.不清楚"}};
    private boolean[][] PSYCHIC_EMOTION_CHECKED = new boolean[PSYCHIC_EMOTION_QUESTION.length][PSYCHIC_EMOTION_ANSWERS_LEN];
    public static final int PSYCHIC_EMOTION_ANSWERS_LEN = 3;
    public static final int[] PSYCHIC_EMOTION_QUESTION_COUNT = new int[]{2, 0, 1};
    public static final int PSYCHIC_EMOTION_QUESTION_LEN = PSYCHIC_EMOTION_QUESTION.length;
    /**
     * A 2分 B 0分 C 1分
     * 总分0~20分，表情你情绪良好、自信心强，具有较强的美感、道德感和理智感。你有一定的社会活动能力，能理解周围的人们的心情，顾全大局。你一定是一个性情爽朗、受人欢迎的人。
     * 总分21~40分，说明你情绪基本稳定，但较为深沉，对事情的考虑过于冷静，处事淡漠消极，不善于发挥自己的个性。你的自信心受到压抑，办事热情忽高忽低，易瞻前顾后、踌躇不前。
     * 总分在41分以上，说明你情绪不佳，日常烦恼太多，使自己的心情处于紧张和矛盾之中。
     * 得分50分以上，则是一种危险信号，务必请心理医生作进一步诊断。
     */


    private RadioGroup[] radioGroups = new RadioGroup[PSYCHIC_EMOTION_QUESTION.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case_emotion);
        LinearLayout layout = (LinearLayout) findViewById(R.id.emotion_ll);
        Button submitBtn = (Button) findViewById(R.id.emotion_b);
        for (int i = 0; i < PSYCHIC_EMOTION_QUESTION_LEN; i++) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(PSYCHIC_EMOTION_QUESTION[i]);
            if (layout != null) {
                layout.addView(textView);
            }
            radioGroups[i] = new RadioGroup(this);
            radioGroups[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            radioGroups[i].setOrientation(LinearLayout.HORIZONTAL);
            if (layout != null) {
                layout.addView(radioGroups[i]);
            }
            for (int j = 0; j < 3; j++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                radioButton.setText(PSYCHIC_EMOTION_ANSWERS[i][j]);
                final int finalI = i;
                final int finalJ = j;
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!buttonView.isPressed()) return;
                        PSYCHIC_EMOTION_CHECKED[finalI] = new boolean[PSYCHIC_EMOTION_ANSWERS_LEN];
                        PSYCHIC_EMOTION_CHECKED[finalI][finalJ] = isChecked;
                    }
                });
                if (radioButton.getText().length() > 5) {
                    radioGroups[i].setOrientation(LinearLayout.VERTICAL);
                }
                radioGroups[i].addView(radioButton);
            }
        }
        if (submitBtn != null) {
            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int totalCount = 0;
                    for (boolean[] aChecked : PSYCHIC_EMOTION_CHECKED) {
                        boolean temp = aChecked[0];
                        for (int j = 0; j < aChecked.length; j++) {
                            temp = temp || aChecked[j];
                            if (j == PSYCHIC_EMOTION_ANSWERS_LEN - 1) {
                                if (!temp) {
                                    Toast.makeText(EmotionActivity.this, "还有选项没有选择", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            totalCount += (aChecked[j] ? PSYCHIC_EMOTION_QUESTION_COUNT[j] : 0);
                        }
                    }
                    Intent intent = new Intent(EmotionActivity.this, Emotion_result_Activity.class);
                    intent.putExtra("result", totalCount);
                    startActivity(intent);
                }
            });
        }
    }
}
