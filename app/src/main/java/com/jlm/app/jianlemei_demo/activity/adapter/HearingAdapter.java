package com.jlm.app.jianlemei_demo.activity.adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.db.bean.BloodPressBean;
import com.jlm.app.jianlemei_demo.db.bean.HearingBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HearingAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<HearingBean> list = new ArrayList<>();
    Context context;

    public HearingAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<HearingBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public HearingBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_data_double_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setView(getItem(position));
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.item_data_double_pinglun)
        TextView pingLun;
        @Bind(R.id.item_data_double_data)
        TextView data;
        @Bind(R.id.item_data_double_info1)
        TextView info1;
        @Bind(R.id.item_data_double_info2)
        TextView info2;
        @Bind(R.id.item_data_double_delete)
        TextView delete;
        @Bind(R.id.item_data_double_img)
        ImageView Img;
        HearingBean bean;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context).setIcon(R.drawable.icon)
                            .setTitle("删除选中数据")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ExamSQLHelper helper = null;
                                    try {
                                        helper = new ExamSQLHelper(context);
                                        helper.getDao(HearingBean.class).delete(bean);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    } finally {
                                        if (helper != null) {
                                            helper.close();
                                            if (context instanceof Activity) {
                                                context.startActivity(new Intent(context, context.getClass()));
                                                ((Activity) context).finish();
                                            }
                                            dialog.dismiss();
                                        }
                                    }
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
            });
        }

        public void setView(HearingBean bean) {
            Img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Img.setLayoutParams(new LinearLayout.LayoutParams(Img.getHeight(), Img.getHeight()));
                }
            });
            this.bean = bean;
            this.pingLun.setText(bloodPressException());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] s = format.format(new Date(Long.valueOf(bean.getRecordTime()))).split(" ");
            this.data.setText(s[0] + "\r\n" + s[1]);
            this.info1.setText("高频:" + bean.getHearmingH());
            this.info2.setText("低频:" + bean.getHearmingL());

        }

        public String bloodPressException() {
            float h = bean.getHearmingH();
            float l = bean.getHearmingL();
            return "正常";
        }
    }

}
