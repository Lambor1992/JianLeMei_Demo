package com.jlm.app.jianlemei_demo.activity.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.adapter.HealthConsultAdapter;


public class Home_ConsultationActivity extends AppCompatActivity {
    private ListView mListView;
    private HealthConsultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_consult);
        this.mListView = (ListView) findViewById(R.id.consult_listview);
        adapter = new HealthConsultAdapter(this);
        mListView.setAdapter(adapter);
    }
}
