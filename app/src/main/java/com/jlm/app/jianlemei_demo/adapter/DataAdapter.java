package com.jlm.app.jianlemei_demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.j256.ormlite.dao.Dao;

/**
 * Created by Administrator on 2016/4/10.
 */
public class DataAdapter {
    public static class SingleDataAdapter extends BaseAdapter {
        private Context context;
        public SingleDataAdapter(Context context) {
            this.context = context;
            setData();
        }

        private void setData() {
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return 0;
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
            return null;
        }
    }
}
