package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.widget.TitleBar;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ExamPsychologyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        TitleBar titleBar = new TitleBar(this);
        titleBar.setTitleName("心理测试");
        titleBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(titleBar);

        ListView listView = new ListView(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"抑郁症", "情绪", "自闭症"}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(ExamPsychologyActivity.this, DepressionExamActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(ExamPsychologyActivity.this, EmotionActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(ExamPsychologyActivity.this, AutismActivity.class));
                        break;
                }
            }
        });
        linearLayout.addView(listView);
        setContentView(linearLayout);

    }

}
