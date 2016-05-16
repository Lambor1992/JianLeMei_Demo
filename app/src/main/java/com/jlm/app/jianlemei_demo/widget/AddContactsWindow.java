package com.jlm.app.jianlemei_demo.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.WarmingSharePrefrenceHelper;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zwg on 2016/3/21.
 */
public class AddContactsWindow extends PopupWindow {

    EditText ContactName;
    EditText ContactPhone;
    Context context;

    public AddContactsWindow(final Activity context, final String s) {
        this.context = context;
        View v = LayoutInflater.from(context).inflate(R.layout.popup_add_contact_layout, null);
        ContactName = (EditText) v.findViewById(R.id.popup_add_contact_name);
        ContactPhone = (EditText) v.findViewById(R.id.popup_add_contact_phone);
        v.findViewById(R.id.popup_add_contact_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarmingSharePrefrenceHelper(context).addEmergency(s, ContactName.getText().toString(), ContactPhone.getText().toString());
                if (context instanceof RefreshView) {
                    ((RefreshView) context).refreshView();
                }
                dismiss();
            }
        });
        v.findViewById(R.id.popup_add_contact_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            this.showAsDropDown(parent, 0, 0, Gravity.CENTER);
        } else {
            this.dismiss();
        }
    }


    public interface RefreshView {
        void refreshView();
    }
}
