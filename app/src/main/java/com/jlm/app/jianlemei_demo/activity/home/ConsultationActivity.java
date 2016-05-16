package com.jlm.app.jianlemei_demo.activity.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.speech.TTS;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConsultationActivity extends Activity {
    private String Word;
    private boolean Word_ = false;
    private TTS tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_consultation);
        String address = getIntent().getStringExtra("address");
        final WebView mWebView = (WebView) findViewById(R.id.web_consult);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(address);
        initConsult(address);
        tts = new TTS(this);
    }

    public void initConsult(final String address) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Word = deleteNoUseFromString(request(address));
            }
        }).start();
    }

    public String request(String httpUrl) {
        BufferedReader reader;
        String result = null;
        StringBuilder sbf = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String deleteNoUseFromString(String string) {
        Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(string);
        string = matcher.replaceAll("");
        string = string.replace("div,img{width:;}", "").replaceAll("&nbsp;", "");
        string = string.trim();
        string = string.split("字体大小设置:小中大")[1];
        Word_ = true;
        return string.split("function")[0];
    }

    public void onClickPlay(View view) {
        if (Word_) {
            tts.Speech(Word, new SynthesizerListener() {
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
                    Word_ = true;
                }

                @Override
                public void onEvent(int i, int i1, int i2, Bundle bundle) {

                }
            });
            Word_ = false;
        }
    }

    public void onClickPause(View view) {
        tts.Pause();
        Word_ = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.Stop();
            tts.Destroy();
        }
    }
}
