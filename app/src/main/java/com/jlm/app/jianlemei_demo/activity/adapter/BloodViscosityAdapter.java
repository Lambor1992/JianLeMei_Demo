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
import com.jlm.app.jianlemei_demo.db.bean.BloodViscosityBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/10.
 */
public class BloodViscosityAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<BloodViscosityBean> list = new ArrayList<>();
    Context context;

    public BloodViscosityAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<BloodViscosityBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public BloodViscosityBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_data_single, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            holder.setView(getItem(position));
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.item_data_single_img)
        ImageView Img;
        @Bind(R.id.item_data_single_data)
        TextView Data;
        @Bind(R.id.item_data_single_pinglun)
        TextView Pinglun;
        @Bind(R.id.item_data_single_info)
        TextView Bloodviscosity;
        @Bind(R.id.item_data_single_delete)
        TextView delete;
        BloodViscosityBean bloodViscosity;

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
                                        helper.getDao(BloodViscosityBean.class).delete(bloodViscosity);
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

        public void setView(BloodViscosityBean bloodViscosity) {
            Img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Img.setLayoutParams(new LinearLayout.LayoutParams(Img.getHeight(), Img.getHeight()));
                }
            });
            this.bloodViscosity = bloodViscosity;
            this.Pinglun.setText(bloodPressException());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] s = format.format(new Date(Long.valueOf(bloodViscosity.getRecordTime()))).split(" ");
            this.Data.setText(s[0] + "\r\n" + s[1]);
            this.Bloodviscosity.setText("血液粘度:" + bloodViscosity.getBloodViscosity());

        }

        public String bloodPressException() {
            float count = bloodViscosity.getBloodViscosity();
            if (count > 1f) {
                return "偏高";
            } else if (count < 0f) {
                return "偏低";
            } else return "正常";
        }
    }
}

