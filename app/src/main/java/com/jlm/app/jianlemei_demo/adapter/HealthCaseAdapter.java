package com.jlm.app.jianlemei_demo.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.HealthCase;

/**
 * Created by zwg on 2016/3/12.
 */
public class HealthCaseAdapter extends BaseAdapter {
    private String[] str = new String[]{};
    private int layout;
    private LayoutInflater inflater;
    private boolean[] checked;
    private int[] backgroundRes = null;
    private Context context;

    /*
    if you didn't have layout,you can make layout =0;
     */
    public HealthCaseAdapter(String[] str, int layout, Context context) {
        this.changeData(str);
        this.context = context;
        checked = new boolean[str.length];
        if (layout != 0) this.layout = layout;
        else this.layout = R.layout.aaa_textview_layout_item;
        this.inflater = LayoutInflater.from(context);

    }

    public void changeData(String[] str) {
        this.str = str;
        setBackgroundRes();
        notifyDataSetChanged();
    }

    public void setBackgroundRes() {
        if (this.str[0].equals(HealthCase.HEALTH_CASE_C.EYE[0])) {
            backgroundRes = new int[]{R.drawable.selector_content_icon_eye_1, R.drawable.selector_content_icon_eye_2, R.drawable.selector_content_icon_eye_3,
                    R.drawable.selector_content_icon_eye_4, R.drawable.selector_content_icon_eye_5, R.drawable.selector_content_icon_eye_6,
                    R.drawable.selector_content_icon_eye_7, R.drawable.selector_content_icon_eye_8, R.drawable.selector_content_icon_eye_9};
        } else if (this.str[0].equals(HealthCase.HEALTH_CASE_C.EAR[0])) {
            backgroundRes = new int[]{R.drawable.selector_content_icon_ear_1, R.drawable.selector_content_icon_ear_2, R.drawable.selector_content_icon_ear_3,
                    R.drawable.selector_content_icon_ear_4, R.drawable.selector_content_icon_ear_5};
        } else if (this.str[0].equals(HealthCase.HEALTH_CASE_C.BLOOD[0])) {
            backgroundRes = new int[]{R.drawable.selector_content_icon_boold_1, R.drawable.selector_content_icon_boold_2, R.drawable.selector_content_icon_boold_3,
                    R.drawable.selector_content_icon_boold_4};
        } else if (this.str[0].equals(HealthCase.HEALTH_CASE_C.WEIGHT[0])) {
            backgroundRes = new int[]{R.drawable.selector_content_icon_weigh_1, R.drawable.selector_content_icon_weigh_2, R.drawable.selector_content_icon_weigh_4};
        } else if (this.str[0].equals(HealthCase.HEALTH_CASE_C.PSYCHOLOGY[0])) {
            backgroundRes = new int[]{R.drawable.selector_content_icon_psychology_1, R.drawable.selector_content_icon_psychology_2, R.drawable.selector_content_icon_psychology_3,
                    R.drawable.selector_content_icon_psychology_4};
        }
    }

    public void changeData(boolean[] checked) {
        if (this.checked.length != checked.length) return;
        this.checked = checked;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public String getItem(int position) {
        return str[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layout, null);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.aaa_tv);
            viewHolder.bg = (CheckBox) convertView.findViewById(R.id.aaa_cb);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(getItem(position));
        viewHolder.bg.setChecked(checked[position]);
        Drawable drawable = ContextCompat.getDrawable(context, backgroundRes[position]);
        viewHolder.bg.setBackground(drawable);
        return convertView;
    }


    class ViewHolder {
        TextView tv;

        public CheckBox getBg() {
            return bg;
        }

        public void setBg(CheckBox bg) {
            this.bg = bg;
        }

        CheckBox bg;

        ViewHolder() {
        }

        public TextView getTv() {
            return tv;
        }

        public void setTv(TextView tv) {
            this.tv = tv;
        }
    }

}
