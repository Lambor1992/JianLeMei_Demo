package com.jlm.app.jianlemei_demo.service.radio;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class HealthCaseService extends Service {


    public HealthCaseService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) return super.onStartCommand(intent, flags, startId);
        if (intent.getAction() == null) return super.onStartCommand(intent, flags, startId);
        String action = intent.getAction();
        switch (action){

        }
        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder extends Binder implements HealthCaseEvent {

        @Override
        public void play() {

        }

        @Override
        public boolean isPlaying() {
            return false;
        }

        @Override
        public void seekTo() {

        }
    }

    interface HealthCaseEvent {
        void play();

        boolean isPlaying();

        void seekTo();
    }
}
