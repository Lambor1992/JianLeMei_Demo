package com.jlm.app.jianlemei_demo.service.radio;

import android.media.MediaPlayer;

/**
 * Created by Administrator on 2016/3/15.
 */
public class MyMediaPlayer extends MediaPlayer {
    private static MyMediaPlayer myMediaPlayer;

    private MyMediaPlayer() {
        super();
    }

    public static MyMediaPlayer newInstance() {
        if (myMediaPlayer == null) {
            return new MyMediaPlayer();
        } else return newInstance();
    }
}
