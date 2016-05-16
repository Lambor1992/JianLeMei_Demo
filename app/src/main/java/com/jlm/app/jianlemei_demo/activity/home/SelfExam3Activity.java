package com.jlm.app.jianlemei_demo.activity.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelfExam3Activity extends Activity {

    @Bind(R.id.self_exam2_layout)
    LinearLayout Layout;
    @Bind(R.id.self_exam2_submit)
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_exam2);
        ButterKnife.bind(this);
        Submit.setVisibility(View.GONE);
        ArrayList<Integer> arrayList = getIntent().getIntegerArrayListExtra("id");
        final String address = HttpAdress.HTTP_findSickaction + "?ids=" + arrayList.toString().replace("[", "").replace("]", "").replace(" ", "");
        Log.e("Test", address);
        VolleySingleton.getVolleySingleton(this).getRequestQueue().add(new JsonArrayRequest(address, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        Button Btn = new Button(SelfExam3Activity.this);
                        Btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        String sss = jsonArray.getJSONObject(i).optString("sickName", "");
                        try {
                            sss =new String(sss.getBytes("ISO8859-1"),"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Btn.setText(sss);
                        final int finalI = i;
//                        Btn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                try {
//                                    startActivity(new Intent(SelfExam3Activity.this, SelfExam4Activity.class).putExtra("id", jsonArray.getJSONObject(finalI).getInt("sickId")));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
                        Layout.addView(Btn);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleySingleton.getVolleySingleton(SelfExam3Activity.this).getRequestQueue().add(new JsonArrayRequest(address, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray jsonArray) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                Button Btn = new Button(SelfExam3Activity.this);
                                Btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                String sss = jsonArray.getJSONObject(i).optString("sickName", "");
                                try {
                                    sss =new String(sss.getBytes("ISO8859-1"),"UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                Btn.setText(sss);
                                final int finalI = i;
//                        Btn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                try {
//                                    startActivity(new Intent(SelfExam3Activity.this, SelfExam4Activity.class).putExtra("id", jsonArray.getJSONObject(finalI).getInt("sickId")));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
                                Layout.addView(Btn);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
            }
        }));

    }

    @OnClick(R.id.self_exam2_submit)
    public void onClick() {
    }
}
