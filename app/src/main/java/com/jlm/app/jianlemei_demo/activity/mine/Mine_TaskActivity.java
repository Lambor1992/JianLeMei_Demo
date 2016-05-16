package com.jlm.app.jianlemei_demo.activity.mine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.home.mall.MallActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Mine_TaskActivity extends AppCompatActivity {

    @Bind(R.id.mine_task_username)
    TextView Username;
    @Bind(R.id.mine_task_integral)
    TextView Integral;
    @Bind(R.id.mine_task_goto_mall)
    Button GonToMall;
    @Bind(R.id.mine_task_listview)
    ListView TaskListview;
    String[] task_name = {"血压测量", "检查视力", "心率测量", "肺活量测量", "每天5000步", "眼保健操", "随机移动", "左右移动", "圆圈聚焦", "降压操", "睡前减肥操"};
    String[] score = {"+5", "+3", "+3", "+3", "+5", "+2", "+1", "+1", "+1", "+3", "+5"};
    boolean[] finished = new boolean[task_name.length];
    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return task_name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(Mine_TaskActivity.this).inflate(R.layout.item_mine_task_layout, null);
            TextView textView1 = (TextView) convertView.findViewById(R.id.task_name);
            TextView textView2 = (TextView) convertView.findViewById(R.id.task_score);
            TextView textView3 = (TextView) convertView.findViewById(R.id.task_condition);
            textView1.setText(task_name[position]);
            textView2.setText(score[position]);
            textView3.setText(finished[position] ? "未完成" : "已完成");
            return convertView;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_task);
        ButterKnife.bind(this);
        TaskListview.setAdapter(baseAdapter);
        Username.setText(getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString(Mine_userinfoActivity.UserInfo_username, ""));
    }

    @OnClick({R.id.mine_task_integral, R.id.mine_task_goto_mall})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_task_integral:
                new AlertDialog.Builder(this).setTitle("今日积分").setIcon(R.drawable.icon)
                        .setItems(new String[]{"今日还什么都没做，快去做点什么吧"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
                break;
            case R.id.mine_task_goto_mall:
                startActivity(new Intent(this, MallActivity.class).putExtra("qType", "4"));
                break;
        }
    }
}
