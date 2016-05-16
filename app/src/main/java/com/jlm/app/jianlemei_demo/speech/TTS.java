package com.jlm.app.jianlemei_demo.speech;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.jlm.app.jianlemei_demo.R;

import java.io.File;

public class TTS {
    private Context context;
    private SpeechSynthesizer mTts;
    private SynthesizerListener synthesizerListener = new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {
        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {
        }

        @Override
        public void onCompleted(SpeechError speechError) {

        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {
        }
    };

    public TTS(Context context) {
        this.context = context;
        mTts = SpeechSynthesizer.createSynthesizer(context, null);
        initTts();
    }


    public void initTts() {
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        mTts.setParameter(SpeechConstant.SPEED, "50");
        mTts.setParameter(SpeechConstant.PITCH, "50");
        mTts.setParameter(SpeechConstant.VOLUME, "100");
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        String s = Environment.getExternalStorageDirectory() + "/" + "jlm/news/iflytek.pcm";
        File file = new File(s);
        if (file.isDirectory()) {
            file.delete();
        }
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, s);
    }

    public void Speech(String s) {
        mTts.startSpeaking(s, synthesizerListener);
    }

    public void Speech(String s, SynthesizerListener synthesizerListener) {
        if (synthesizerListener != null) {
            this.synthesizerListener = synthesizerListener;
        }
        mTts.startSpeaking(s, this.synthesizerListener);
    }

    public void Pause() {
        mTts.pauseSpeaking();
    }

    public void Stop() {
        if (mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }
    }

    public void Destroy() {
        if (mTts != null) {
            mTts.stopSpeaking();
            mTts.destroy();
        }
    }

}

