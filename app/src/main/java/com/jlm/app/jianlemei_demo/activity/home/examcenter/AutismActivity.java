package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;

public class AutismActivity extends AppCompatActivity {

    public static final String[] PSYCHIC_AUTISM_QUESTION = {"1.没有眼神接触.",
            "2.不适当地大笑或傻笑.",
            "3.对于别人的脸部表情、情绪或肢体语言无法了解.",
            "4.无法调适声量迎合不同的社交场合(例如声量太大).",
            "5.不喜欢/抗拒改变日常生活规律.",
            "6.游戏方式缺乏创意,也缺乏角色扮演的能力.",
            "7.怪异行为/让旁人觉得古怪的行为(例如:无故在课堂上拍掌).",
            "8.满不在乎的态度(例如不理睬来访的客人).",
            "9.只在大人坚持和相助的情况下才参与接待来访的客人.",
            "10.兴趣范围狭隘,对某样东西/事物着迷.",
            "11.对同样的课题滔滔不绝.",
            "12.没有与他人分享他的兴趣爱好.",
            "13.像鹦鹉般地模仿他人说话.",
            "14.呆板而重复地模仿电视、录像里的行为举动和讲话方式.",
            "15.自始自终和别人说话,不顾他人是否有兴趣听或者有回应.",
            "16.不能自然与适应地和其他孩子玩乐.",
            "17.可以很快和很好地做一些事情,但却不能胜任需要应用一些社会知识的工作.",
            "18.喜欢旋转东西和喜欢自身旋转或注视会旋转的东西.",
            "19.喜欢独自玩很长时间的积木或画画."};
    public static final String[] PSYCHIC_AUTISM_ANSWERS = {"1.经常有", "2.有时有", "3.没有"};
    public static final int[] PSYCHIC_AUTISM_QUESTION_COUNT = new int[]{1, 0, 0};
    public static final boolean[][] checked = new boolean[PSYCHIC_AUTISM_QUESTION.length][PSYCHIC_AUTISM_ANSWERS.length];
    public static final int size = PSYCHIC_AUTISM_QUESTION.length;

    LinearLayout linearLayout;
    RadioGroup[] radioGroups = new RadioGroup[PSYCHIC_AUTISM_QUESTION.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case_autism);
        linearLayout = (LinearLayout) findViewById(R.id.autism_linearLayout);
        Button button = (Button) findViewById(R.id.autism_button);
        for (int i = 0; i < size; i++) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            textView.setText(PSYCHIC_AUTISM_QUESTION[i]);
            linearLayout.addView(textView);
            radioGroups[i] = new RadioGroup(this);
            radioGroups[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            radioGroups[i].setOrientation(RadioGroup.HORIZONTAL);
            linearLayout.addView(radioGroups[i]);

            for (int j = 0; j < PSYCHIC_AUTISM_ANSWERS.length; j++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                radioButton.setText(PSYCHIC_AUTISM_ANSWERS[j]);
                final int finalI = i;
                final int finalJ = j;
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!buttonView.isPressed()) return;
                        checked[finalI] = new boolean[PSYCHIC_AUTISM_ANSWERS.length];
                        checked[finalI][finalJ] = isChecked;
                    }
                });
                radioGroups[i].addView(radioButton);
            }
        }
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int totalCount = 0;
                    for (boolean[] aChecked : checked) {
                        boolean temp = aChecked[0];
                        for (int j = 0; j < aChecked.length; j++) {
                            temp = temp || aChecked[j];
                            if (j == PSYCHIC_AUTISM_ANSWERS.length - 1) {
                                if (!temp) {
                                    Toast.makeText(AutismActivity.this, "还有选项没有选择", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            totalCount += (aChecked[j] ? PSYCHIC_AUTISM_QUESTION_COUNT[j] : 0);
                        }
                    }
                    if (totalCount > 10) {
                        Toast.makeText(AutismActivity.this, "疑似", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(AutismActivity.this, "正常", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
