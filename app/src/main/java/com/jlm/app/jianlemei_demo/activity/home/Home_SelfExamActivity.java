package com.jlm.app.jianlemei_demo.activity.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.db.health.SelfExam;

public class Home_SelfExamActivity extends AppCompatActivity {
    RadioButton ManRadioBtn, WomanRadioBtn;
    Spinner ageSpinner;
    LinearLayout Layout;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_selfexam);
        final ListView listView = (ListView) findViewById(R.id.home_self_exam_listView);
        Layout = (LinearLayout) findViewById(R.id.self_exam_layout);
        ManRadioBtn = (RadioButton) findViewById(R.id.home_self_exam_m);
        WomanRadioBtn = (RadioButton) findViewById(R.id.home_self_exam_w);
        ageSpinner = (Spinner) findViewById(R.id.home_self_exam_age);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    age = 0;
                } else if (position < 2) {
                    age = 1;
                } else if (position < 6) {
                    age = 2;
                } else {
                    age = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                age = 0;
            }
        });
        if (listView != null) {
            listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, SelfExam.bodys));
        }
        if (listView != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (ageSpinner.getItemAtPosition(ageSpinner.getSelectedItemPosition()).equals("0")) {
                        new AlertDialog.Builder(Home_SelfExamActivity.this).setIcon(R.drawable.icon).setTitle("")
                                .setMessage("选择您的年龄和性别")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
                        return;
                    }
                    startActivity(new Intent(Home_SelfExamActivity.this, SelfExamActivity.class)
                            .putExtra("body", SelfExam.bodys[position])
                            .putExtra("sex", ManRadioBtn.isChecked() ? 1 : 0)
                            .putExtra("age", age));
                }
            });
        }
//        if (listView != null) {
//            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(AbsListView view, int scrollState) {
//                    switch (scrollState) {
//                        case 0:
//                            Layout.setVisibility(listView.getFirstVisiblePosition() != 0 ? View.GONE : View.VISIBLE);
//                            break;
//                    }
//                }
//
//                @Override
//                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                }
//            });
//        }
    }
}
