package com.jlm.app.jianlemei_demo.fragment.video;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.db.bean.Radio;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Home_Video_2Fragment extends Fragment implements Home_Video_1Fragment.radioListener {
    private static Home_Video_2Fragment fragment;
    @Bind(R.id.fg_home_vedio_diantai_tv)
    TextView DiantaiTv;
    @Bind(R.id.fg_home_vedio_zhuanjia_tv)
    TextView ZhuanjiaTv;
    @Bind(R.id.fg_home_radio_webview)
    WebView webView;
    @Bind(R.id.fg_home_radio_message)
    TextView MessageTv;
    @Bind(R.id.fg_home_radio_submit)
    Button SubmitBtn;
    @Bind(R.id.fg_home_radio_edt)
    EditText editText;
    int radioId;
    int userId;

    public Home_Video_2Fragment() {
    }

    public static Home_Video_2Fragment newInstancet() {
        if (fragment == null) fragment = new Home_Video_2Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home_video_2, container, false);
        ButterKnife.bind(this, view);
        userId = getActivity().getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getInt(Mine_userinfoActivity.UserInfo_id, -1);
        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                if (!"".equals(s)) {
                    VolleySingleton
                            .getVolleySingleton(getActivity())
                            .getRequestQueue()
                            .add(new StringRequest(
                                    HttpAdress.BASE_commentAction_saveCommentForRadio
                                            + "?json=" + getJson(s).toString(),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {
                                            initMessage();
                                            editText.setText("");
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError volleyError) {

                                        }
                                    }));
                }
            }
        });
        return view;
    }

    private JSONObject getJson(String s) {
        JSONObject json = new JSONObject();
        try {
            try {
                json.put("content", URLEncoder.encode(s, "UTF-8"));
                json.put("userId", userId);
                json.put("radioId", radioId);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initMessage() {
        VolleySingleton
                .getVolleySingleton(getActivity())
                .getRequestQueue()
                .add(new JsonArrayRequest(
                        HttpAdress.BASE_commentAction_queryAllCommentByRadioId
                                + "?radioId=" + radioId
                                + "&&userId=" + userId,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray jsonArray) {
                                try {
                                    showMessage(jsonArray);
                                } catch (JSONException e) {
                                    Toast.makeText(getActivity(), "数据异常,请联系工作人员", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }));
    }

    public void showMessage(JSONArray array) throws JSONException {
        int len = array.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            sb.append(jsonObject.opt("author"));
            sb.append("  ");
            sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(jsonObject.optString("creatTime"))));
            sb.append(":\n  ");
            sb.append(jsonObject.opt("content"));
            sb.append(":\n");
        }
        MessageTv.setText(sb.toString());
    }

    @Override
    public void onRadioChange(Radio radio) {
        radioId = radio.getId();
        webView.loadUrl(radio.getRadioAddress());
        DiantaiTv.setText("    " + radio.getRadioInfo());
        initMessage();
    }
}
