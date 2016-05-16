package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

/**
 * Created by Administrator on 2016/4/13.
 */
public class AudioTrackManager {
    public static final int RATE = 44100;
    public static final float MAXVOLUME = 100f;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int DOUBLE = 3;

    AudioTrack audioTrack;
    float volume;
    int channel;
    int length;
    int waveLen;
    int Hz;
    byte[] wave;

    public AudioTrackManager() {
        wave = new byte[RATE];
    }

    public void start(int rate) {
        stop();
        if (rate > 0) {
            Hz = rate;
            Log.e("TEst", "__Hz" + Hz);

            waveLen = RATE / Hz;
            Log.e("TEst", "__Hz" + Hz + "____waveLen");

            length = waveLen * Hz;
            Log.e("TEst", "__Hz" + Hz + "____waveLen" + waveLen + "__len" + length);

            audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, RATE,
                    AudioFormat.CHANNEL_CONFIGURATION_STEREO,
                    AudioFormat.ENCODING_PCM_8BIT,
                    length,
                    AudioTrack.MODE_STREAM);
            wave = SinWave.sin(wave, waveLen, length);
            if (audioTrack != null) {
                audioTrack.play();
            }


        } else {
            return;
        }

    }

    public void play() {
        if (audioTrack != null) {
            audioTrack.write(wave, 0, length);
        }
    }

    public void stop() {
        if (audioTrack != null) {
            audioTrack.stop();
            audioTrack.release();
            audioTrack = null;
        }
    }

    public void setVolume(float volume) {
        this.volume = volume;
        if (audioTrack != null) {
            switch (channel) {
                case LEFT:
                    audioTrack.setStereoVolume(volume / MAXVOLUME, 0f);
                    break;
                case RIGHT:
                    audioTrack.setStereoVolume(0f, volume / MAXVOLUME);
                    break;
                case DOUBLE:
                    audioTrack.setStereoVolume(volume / MAXVOLUME, volume / MAXVOLUME);
                    break;
            }
        }
    }

    public void setChannel(int channel) {
        this.channel = channel;
        setVolume(volume);
    }
}
