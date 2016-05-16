package com.jlm.app.jianlemei_demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jlm.app.jianlemei_demo.activity.PromptActivity;

/**
 * Created by admin on 2016/4/10.
 */
public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String ACTTION = intent.getAction();
        switch (ACTTION){
            case "com.jlm.warming":
                Intent intent1=new Intent(context,PromptActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
                break;
        }

    }
}
