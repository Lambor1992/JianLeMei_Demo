package com.jlm.app.jianlemei_demo.service.falldetection;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FallDetectionService extends Service {
    public FallDetectionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
