package com.jlm.app.jianlemei_demo.activity.social;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class ConsultActivity extends Activity {
    public int doctorId;
    public int userId;
    private EditText ContentEdt;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        ContentEdt = (EditText) findViewById(R.id.consult_content);
        intent = getIntent();
        doctorId = intent.getIntExtra("doctorId", -1);
        userId = intent.getIntExtra("userId", -1);
    }

    public void expertConsult(View view) {
        VolleySingleton.getVolleySingleton(this)
                .getRequestQueue()
                .add(new StringRequest(
                        HttpAdress.BASE_commentAction_saveComment + "?json=" + getJson().toString(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Toast.makeText(ConsultActivity.this, "我们会将您的疑问转交给专家，静候专家回复", Toast.LENGTH_SHORT).show();
                                setResult(0, intent.putExtra("Content", ContentEdt.getText().toString()));
                                finish();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(ConsultActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                            }
                        }));
    }

    private JSONObject getJson() {
        JSONObject json = new JSONObject();
        try {
            try {
                json.put("content", URLEncoder.encode(ContentEdt.getText().toString(), "UTF-8"));
                json.put("userId", userId);
                json.put("doctorId", doctorId);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
