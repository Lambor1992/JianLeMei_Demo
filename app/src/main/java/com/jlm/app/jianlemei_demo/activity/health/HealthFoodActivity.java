package com.jlm.app.jianlemei_demo.activity.health;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.adapter.HealthCookFoodAdapter;
import com.jlm.app.jianlemei_demo.widget.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/29.
 */
public class HealthFoodActivity extends Activity {
    @Bind(R.id.listview)
    ListView mListview;
    @Bind(R.id.listview_title)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ButterKnife.bind(this);
        String state = getIntent().getStringExtra("state");
        titleBar.setTitleName("养生食谱");
        titleBar.setVisibility(View.VISIBLE);
        HealthCookFoodAdapter adapter = new HealthCookFoodAdapter(this, true);
        mListview.setAdapter(new HealthCookFoodAdapter(this, state));
    }
}
