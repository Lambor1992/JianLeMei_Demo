package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;


public class Feihuoliang_Thread extends Thread {
    private AudioRecord ar;
    private int bs;
    private static int SAMPLE_RATE_IN_HZ = 44100;
    private boolean isRun = false;
    private Handler mhandle;
    Object mLock;

    public Feihuoliang_Thread(Handler mhandle) {
        super();
        this.mhandle = mhandle;
        mLock = new Object();
        bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
                AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT);
        ar = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ,
                AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT,
                bs);
    }
    public void run() {
        ar.startRecording();
        byte[] buffer = new byte[bs];
        isRun = true;
        while (isRun) {
            int r = ar.read(buffer, 0, bs);
            long v = 0;
            for (int i = 0; i < buffer.length; i++) {
                v += buffer[i] * buffer[i];
            }
            double volume2 = Math.abs(v) / (double) r;
            if (volume2 > 2800) {
                Message msg = mhandle.obtainMessage(1,
                        String.valueOf(v / (float) r));
                mhandle.sendMessage(msg);
            }

            synchronized (mLock) {
                try {
                    mLock.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            ar.stop();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        ar.release();
    }

    public void pause() {
        if (isRun) {
            isRun = false;
        }

    }

    public void start() {
        if (!isRun) {
            if (!this.isAlive()) {
                super.start();
            }
        }
    }

}
