package com.jlm.app.jianlemei_demo.service.radio;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class VideoService extends Service {
    public static final String eye_1_1 = "com.jlm.app.jianlemei_demo.service.video_eye_1_1";
    public static final String eye_1_2 = "com.jlm.app.jianlemei_demo.service.video_eye_1_2";
    public static final String eye_1_3 = "com.jlm.app.jianlemei_demo.service.video_eye_1_3";
    public static final String eye_1_4 = "com.jlm.app.jianlemei_demo.service.video_eye_1_4";

    private String uri_play;
    private MediaPlayer player = new MediaPlayer();
    private MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder implements VideosEvent {

        @Override
        public boolean isplaying() {
            return player.isPlaying();
        }

        @Override
        public boolean isOnline() {
            return false;
        }

        @Override
        public int getDuration() {
            if (!player.isPlaying()) return 0;
            int i = player.getDuration();
            return i;
        }

        @Override
        public int getCurrentPosition() {
            if (!player.isPlaying()) return 0;
            int i = player.getCurrentPosition();
            return i;
        }

        @Override
        public void playNest() {

        }

        @Override
        public void seekTo(int seekTo) {
            player.seekTo(seekTo);
        }

        @Override
        public boolean play() {
            if (player.isPlaying()) {
                player.pause();
                return false;
            } else {
                player.start();
                return true;
            }
        }

        @Override
        public boolean play(String address) {
            Log.e("Test", "__" + address);
            if (player != null) {
                Log.e("Test", "__" + player);

                if (player.isPlaying()) {
                    player.reset();

                }
                player.release();
            }
            player = new MediaPlayer();
            try {
                Log.e("Test", "__" + player);
                player.setDataSource(address);
                player.prepare();
                player.start();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public VideoService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() == null) return super.onStartCommand(intent, flags, startId);
        String str = intent.getAction();
        switch (str) {
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initplayer();
        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
    }

    @Override
    public void onDestroy() {
        player.reset();
        player = null;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    public void initplayer() {
        if (Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            uri_play = Environment.getExternalStorageDirectory().toString() + "/Music/libai.mp3";
            try {
                player.setDataSource("/storage/emulated/0/jlm/1254853.mp3");
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    interface VideosEvent {
        boolean isplaying();

        boolean isOnline();

        int getDuration();

        int getCurrentPosition();

        void playNest();

        void seekTo(int seekTo);

        boolean play();

        boolean play(String address);
    }
}
