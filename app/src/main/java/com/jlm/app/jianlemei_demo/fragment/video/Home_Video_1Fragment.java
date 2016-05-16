package com.jlm.app.jianlemei_demo.fragment.video;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.home.Home_VideoActivity;
import com.jlm.app.jianlemei_demo.db.bean.Radio;
import com.jlm.app.jianlemei_demo.utils.AppUpdatexUtils;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home_Video_1Fragment extends Fragment {
    @Bind(R.id.home_video_seekbar)
    SeekBar homeVideoSeekbar;
    @Bind(R.id.video_player_progress)
    TextView videoPlayerProgress;
    @Bind(R.id.video_player_duration)
    TextView videoPlayerDuration;

    @Bind(R.id.home_video_play)
    ImageView homeVideoPlay;
    @Bind(R.id.fg_home_video_img)
    ImageView homeVideoBG;
    @Bind(R.id.home_video_online_count)
    TextView OnlineCount;
    @Bind(R.id.home_video_radio_name)
    TextView homeRadioName;
    private RadioHanlder myHandler;
    private File fileSaveDir;
    private Handler handler = new Handler();

    public static class RadioHanlder extends Handler {
        WeakReference<Activity> mActivityReference;
        WeakReference<Home_Video_1Fragment> mFragmentReference;

        RadioHanlder(Activity activity, Home_Video_1Fragment fragment) {
            mActivityReference = new WeakReference<>(activity);
            mFragmentReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            final Activity activity = mActivityReference.get();
            final Home_Video_1Fragment fragment = mFragmentReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case PLAY_RADIO_FROM_LIST:
                        final int which = msg.arg1;
                        final boolean toplay = msg.arg2 == 1;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if (fragment.locationHasResouce(fragment.radioList_Net.get(which))) {
                                    fragment.playMp3fromLocation(toplay);
                                } else {
                                    fragment.playMP3fromNet(which);
                                }
                            }
                        }).start();
                        Toast.makeText(activity, "加载中，请稍后", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    }

    private Drawable start;
    private Drawable pause;
    public ArrayList<Radio> radioList_Net = new ArrayList<>();
    public ArrayList<Radio> radioLIst_Local = new ArrayList<>();
    private static final String SD = Environment.getExternalStorageDirectory() + "/jlm/radio/";
    public MediaPlayer mediaPlayer;
    private int MediaLen;
    private Radio radio;
    private static final int PLAY_RADIO_FROM_LIST = 1;

    public Home_Video_1Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home_video_1, container, false);
        ButterKnife.bind(this, view);
        myHandler = new RadioHanlder(getActivity(), this);
        start = ContextCompat.getDrawable(getActivity(), R.drawable.selector_player_button_start);
        pause = ContextCompat.getDrawable(getActivity(), R.drawable.selector_player_button_stop);
        homeVideoPlay.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(homeVideoPlay.getHeight(), homeVideoPlay.getHeight());
                params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                homeVideoPlay.setLayoutParams(params);
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRadioList();
    }

    public void initRadioList() {
        radioList_Net.clear();
        VolleySingleton.getVolleySingleton(getActivity()).getRequestQueue().add(new JsonArrayRequest(HttpAdress.BASE_radioAction, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        radioList_Net.add(new Radio(jsonArray.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (radioList_Net.size() != 0) {
                    playMP3fromArrayList(0);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        }));
        radioLIst_Local.clear();

    }

    public boolean play() {
        if (mediaPlayer == null) return false;
        else if (mediaPlayer.isPlaying()) return false;
        else {
            mediaPlayer.start();
            homeVideoPlay.setImageDrawable(pause);
            return true;
        }
    }

    public boolean pause() {
        if (mediaPlayer == null) return false;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            homeVideoPlay.setImageDrawable(start);
            return false;
        } else return false;
    }

    @OnClick({R.id.home_video_play, R.id.fg_home_vedio_list_btn, R.id.fg_home_vedio_zan_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_video_play:
                if (mediaPlayer == null) return;
                if (mediaPlayer.isPlaying()) {
                    pause();
                } else {
                    play();
                }
                break;
            case R.id.fg_home_vedio_list_btn:
                final String[] names = getRadioNames();
                new AlertDialog.Builder(getActivity()).setTitle("选择电台").setIcon(R.drawable.icon)
                        .setItems(names, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, final int which) {
                                for (int i = 0; i < names.length; i++) {
                                    if (i == which) {
                                        if (radio == null || radio != radioList_Net.get(which)) {
                                            radio = radioList_Net.get(which);
                                            Message message = new Message();
                                            message.what = PLAY_RADIO_FROM_LIST;
                                            message.arg1 = which;
                                            message.arg2 = 1;
                                            myHandler.sendMessage(message);
                                        }
                                    }
                                }
                            }
                        }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

                break;
            case R.id.fg_home_vedio_zan_btn:

                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String[] getRadioNames() throws NullPointerException {
        ArrayList<String> ss = new ArrayList<>(radioList_Net.size());
        for (int i = 0; i < radioList_Net.size(); i++) {
            if (!Objects.equals(radioList_Net.get(i).getAddress(), "") | !Objects.equals(radioList_Net.get(i).getTitle(), ""))
                ss.add(radioList_Net.get(i).getTitle());
        }
        return ss.toString().replace("[", "").replace("]", "").replace(" ", "").split(",");
    }

    public void playMP3fromArrayList(final int index) {
        if (radioList_Net == null) return;
        if (radioList_Net.size() != 0) {
            radio = radioList_Net.get(index >= radioList_Net.size() ? 0 : index);
            reUploadUI();
            try {
                if (mediaPlayer != null) {
                    mediaPlayer.reset();
                    mediaPlayer = null;
                    System.gc();
                }
            } catch (NullPointerException | IllegalStateException ignored) {
            } finally {
                try {
                    Message message = new Message();
                    message.what = PLAY_RADIO_FROM_LIST;
                    message.arg1 = index;
                    message.arg2 = 0;
                    myHandler.sendMessage(message);
                } catch (NullPointerException ignored) {
                }
            }
        }
    }


    private void playMp3fromLocation(boolean play) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                reUploadUI();
            }
        });
        try {
            mediaPlayer.reset();
            mediaPlayer = null;
        } catch (NullPointerException | IllegalStateException ignored) {
            System.gc();
        } finally {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(fileSaveDir.toString());
                mediaPlayer.prepare();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(getActivity(), "感谢您的收听,您可以选择其他电台", Toast.LENGTH_SHORT).show();
                        mediaPlayer.release();
                    }
                });
                if (play) handler.post(new Runnable() {
                    @Override
                    public void run() {
                        play();
                    }
                });
            } catch (IOException | NullPointerException ignored) {
            }
        }
    }


    private void playMP3fromNet(int which) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                reUploadUI();
            }
        });
        try {
            mediaPlayer.reset();
            mediaPlayer = null;
        } catch (NullPointerException | IllegalStateException ignored) {
            System.gc();
        } finally {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(radioList_Net.get(which).getMp3Address());
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onPrepared(MediaPlayer mp) throws NullPointerException {
                        play();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(getActivity(), "感谢您的收听,您可以选择其他电台", Toast.LENGTH_SHORT).show();
                        mediaPlayer.release();
                    }
                });
                if (AppUpdatexUtils.isWifiConnected(getActivity())) {
                    downloadToSDcard(radio);//须添加wifi检测
                }

            } catch (IOException | NullPointerException ignored) {
            }
        }
    }

    public void reUploadUI() {
        if (getActivity() instanceof Home_VideoActivity) {
            if (((Home_VideoActivity) getActivity()).list_fragment.get(1) instanceof Home_Video_2Fragment) {
                ((Home_Video_2Fragment) ((Home_VideoActivity) getActivity()).list_fragment.get(1)).onRadioChange(radio);
            }
        }
        homeRadioName.setText(radio.getTitle());
        String[] times = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date(System.currentTimeMillis())).split("-");
        int[] time_ = new int[times.length];
        for (int i = 0; i < times.length; i++) {
            time_[i] = Integer.valueOf(times[i]);

        }
        OnlineCount.setText("在线人数:" + getCount(time_));
        VolleySingleton.getVolleySingleton(getActivity()).getImageLoader().get(radio.getPicAddress(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                homeVideoBG.setImageBitmap(imageContainer.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
    }

    public static int getCount(int[] time_) {
        int count;
        double p;
        if (time_[3] < 6) {
            p = Math.random() / 5;
        } else if (time_[3] < 9) {
            p = Math.random() / 2;
        } else if (time_[3] < 13) {
            p = Math.random();
        } else if (time_[3] < 18) {
            p = Math.random();
        } else if (time_[3] < 23) {
            p = Math.random() / 2;
        } else {
            p = Math.random() / 5;
        }
        count = (int) (((time_[0] - 2016) * (int) (Math.random() * (10000))
                + (time_[1]) * (int) (Math.random() * (1000))
                + (time_[2]) * (int) (Math.random() * (100))) * p);
        if (time_[3] > 7 && time_[3] < 23) {
            count = count < 500 ? getCount(time_) : count;
        }
        return count;
    }


    public boolean locationHasResouce(final Radio radio) {
        HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) new URL(radio.getMp3Address()).openConnection();
            conn.setConnectTimeout(1000);
            conn.connect(); // 连接
        } catch (IOException e) {
            return false;
        }
        MediaLen = conn.getContentLength();
        fileSaveDir = new File(SD + String.valueOf(MediaLen) + ".mp3");
        if (fileSaveDir.isDirectory())
            fileSaveDir.delete();
        if (fileSaveDir.isFile()) {
            if (fileSaveDir.length() != conn.getContentLength()) {
                fileSaveDir.delete();
            } else {
                return true;
            }
        }
        if (!fileSaveDir.getParentFile().exists()) // 判断目录是否存在，如果不存在，创建目录
            if (!fileSaveDir.getParentFile().mkdirs()) {
                Toast.makeText(getActivity(), "创建文件夹失败", Toast.LENGTH_SHORT).show();
            }
        return false;
    }


    public void downloadToSDcard(final Radio radio) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                HttpURLConnection conn;
                try {
                    conn = (HttpURLConnection) new URL(radio.getMp3Address()).openConnection();
                    conn.setConnectTimeout(5 * 1000);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty(
                            "Accept",
                            "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
                    conn.setRequestProperty(
                            "Accept-Language",
                            "zh-CN");
                    conn.setRequestProperty(
                            "Referer",
                            radio.getMp3Address());
                    conn.setRequestProperty(
                            "Charset",
                            "UTF-8");
                    conn.setRequestProperty(
                            "User-Agent",
                            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
                    conn.setRequestProperty(
                            "Connection",
                            "Keep-Alive");
                    conn.connect(); // 连接
                    MediaLen = conn.getContentLength();
                    fileSaveDir = new File(SD + String.valueOf(MediaLen) + ".mp3");
                    InputStream inputStream;
                    inputStream = conn.getInputStream();
                    FileOutputStream outputStream;
                    outputStream = new FileOutputStream(fileSaveDir);
                    byte[] Byte = new byte[4 * 1024];
                    int len;
                    while ((len = inputStream.read(Byte)) != -1) {
                        outputStream.write(Byte, 0, len);
                        outputStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    public interface radioListener {
        void onRadioChange(Radio radio);
    }
}

