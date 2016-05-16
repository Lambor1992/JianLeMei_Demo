package com.jlm.app.jianlemei_demo.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;


/**
 * Created by Administrator on 2016/3/24.
 */
public class TitleBar extends LinearLayout {
    private Context context;
    private TextView titletv;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View v = LayoutInflater.from(context).inflate(R.layout.title, this, true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        CharSequence text = a.getText(R.styleable.TitleBar_titlename);
        if (text != null) {
            titletv = (TextView) findViewById(R.id.title_tv);
            titletv.setText(a.getText(R.styleable.TitleBar_titlename));
            titletv.setTextSize(TypedValue.COMPLEX_UNIT_PX, ((TextView) findViewById(R.id.title_back_tv)).getTextSize() * 1.3f);
        }
        a.recycle();

        ImageView backimg = (ImageView) findViewById(R.id.title_back_image);
        TextView backtv = (TextView) findViewById(R.id.title_back_tv);
        backimg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Test","_______"+v.getId());
                filishActivity();
            }
        });
        backtv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Test","_______"+v.getId());

                filishActivity();
            }
        });
    }

    public void filishActivity() {
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public void setTitleSize(int size) {
        titletv = (TextView) findViewById(R.id.title_tv);
        titletv.setTextSize(size);
    }

    public void setTitleName(String titleName) {
        if (titleName != null) {
            titletv = (TextView) findViewById(R.id.title_tv);
            titletv.setText(titleName);
            titletv.setTextSize(TypedValue.COMPLEX_UNIT_PX, titletv.getTextSize() * 1.2f);
        }
    }
}
