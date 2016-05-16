package com.jlm.app.jianlemei_demo.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.AddressChooseActivity;

import java.util.ArrayList;

/**
 * Created by zwg on 2016/3/21.
 */
public class AddressPopupWindow extends PopupWindow {

    public AddressPopupWindow(final Activity context, final ArrayList<String> list, final String city) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.popup_address_layout, null);
        TextView mTvLocation = (TextView) v.findViewById(R.id.popup_address_location);
        String location = mTvLocation.getText().toString();
        mTvLocation.setText(location + city);
        GridView mGridView = (GridView) v.findViewById(R.id.popup_address_gridview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.popup_address_item, list);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (context instanceof setAddree) {
                    ((setAddree) (context)).setAdd(list.get(position));
                }
                dismiss();
            }
        });
        v.findViewById(R.id.popup_address_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivityForResult(new Intent(context, AddressChooseActivity.class), 110);
                dismiss();
            }
        });

        this.setContentView(v);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable(new ColorDrawable(0));
        this.setAnimationStyle(R.anim.fade_in);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent, 0, 0, Gravity.BOTTOM);
        } else {
            this.dismiss();
        }
    }

    public interface setAddree {
        void setAdd(String string);
    }
}
