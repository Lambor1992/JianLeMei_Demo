package com.jlm.app.jianlemei_demo.activity.health;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;

public class HealthCaseFootActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case_foot);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}


