package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;


import butterknife.Bind;
import butterknife.ButterKnife;

public class DepressionExamActivity extends Activity {

    @Bind(R.id.exam_depression_layout)
    LinearLayout DepressionLayout;
    RadioGroup[] radioGroup;
    public String[] questions = {"1.我真希望自己哪天突然死去.", "2.小事我也感到非常着急.", "3.遇到一点小事我就感到烦恼.", "4.我感到在生活中自己是个弱者.",
            "5.我感到人活着没有什么意思.", "6.我感到心慌", "7.我对异性毫无兴趣.", "8.我觉得太笨,样样不如别人.", "9.我变得做什么事都拿不定主意.", "10.我想自己去死.",
            "11.我全身没有一点力气.", "12.我讲话的声音变得有气无力,闲话少多了.", "13.我晚上睡眠时间总得说比往常少多了.", "14.我什么事情都不想干.", "15.我感到不高兴、愉快、不痛快.",
            "16.我感到心里难受或心里不舒服.", "17.我对周围的一切都感到没意思.", "18.我感到紧张不安.", "19.我不想吃东西.", "20.我觉得比平时瘦多了."};
    public String[] answers = new String[]{"没有", "偶尔", "有时有", "经常", "总是"};
    private boolean[][] checked = new boolean[questions.length][answers.length];
    private int[] count = new int[]{0, 1, 2, 3, 4};
    private int totalCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression_exam);
        ButterKnife.bind(this);
        radioGroup = new RadioGroup[questions.length];

        for (int i = 0; i < questions.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(questions[i]);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            DepressionLayout.addView(textView);
            radioGroup[i] = new RadioGroup(this);
            radioGroup[i].setOrientation(RadioGroup.HORIZONTAL);
            radioGroup[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            DepressionLayout.addView(radioGroup[i]);
            for (int j = 0; j < answers.length; j++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(answers[j]);
                final int finalI = i;
                final int finalJ = j;
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!buttonView.isPressed()) return;
                        checked[finalI] = new boolean[answers.length];
                        checked[finalI][finalJ] = isChecked;
                    }
                });
                radioGroup[i].addView(radioButton);
            }

        }
        this.findViewById(R.id.exam_depression_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalCount = 0;
                for (boolean[] aChecked : checked) {
                    boolean temp = aChecked[0];
                    for (int j = 0; j < aChecked.length; j++) {
                        temp = temp || aChecked[j];
                        if (j == answers.length - 1) {
                            if (!temp) {
                                Toast.makeText(DepressionExamActivity.this, "还有选项没有选择", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        totalCount += (aChecked[j] ? count[j] : 0);
                    }
                }
                if (totalCount <= 16) {
                    Toast.makeText(DepressionExamActivity.this, "正常", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (totalCount <= 35) {
                    Toast.makeText(DepressionExamActivity.this, "轻度", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (totalCount <= 45) {
                    Toast.makeText(DepressionExamActivity.this, "中度", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(DepressionExamActivity.this, "重度", Toast.LENGTH_SHORT).show();
            }
        });
    }
}