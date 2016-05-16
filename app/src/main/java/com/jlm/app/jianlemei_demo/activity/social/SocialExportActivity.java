package com.jlm.app.jianlemei_demo.activity.social;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SocialExportActivity extends Activity {


    @Bind(R.id.social_export_icon)
    ImageView ExportIcon;
    @Bind(R.id.social_export_name)
    TextView ExportName;
    @Bind(R.id.social_export_position)
    TextView ExportPosition;
    @Bind(R.id.social_export_hospital)
    TextView ExportHospital;
    @Bind(R.id.social_export_expert)
    TextView ExportExpert;
    @Bind(R.id.social_export_info)
    TextView ExportInfo;
    @Bind(R.id.social_export_consult)
    Button Consult;
    @Bind(R.id.social_export_message)
    TextView MessageTv;

    public static final int SOCIAL_EXPERT_CONSULT = 0;
    public int userId;
    public int doctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_export);
        ButterKnife.bind(this);
        userId = getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getInt(Mine_userinfoActivity.UserInfo_id, 0);
        Consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(SocialExportActivity.this, ConsultActivity.class)
                        .putExtra("userId", userId)
                        .putExtra("doctorId", doctorId), SOCIAL_EXPERT_CONSULT);
            }
        });

        VolleySingleton.getVolleySingleton(this).getRequestQueue().add(new StringRequest(Request.Method.GET, getIntent().getStringExtra("address"), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    ExportName.setText(jsonObject.optString("name"));
                    ExportExpert.setText(jsonObject.optString("expert"));
                    ExportHospital.setText(jsonObject.optString("hospital"));
                    ExportPosition.setText(jsonObject.optString("position"));
                    ExportInfo.setText(jsonObject.optString("doctorInfo"));
                    doctorId = jsonObject.optInt("id");
                    VolleySingleton.getVolleySingleton(SocialExportActivity.this).getImageLoader().get(jsonObject.optString("icon"), new ImageLoader.ImageListener() {
                        @Override
                        public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                            ExportIcon.setImageBitmap(imageContainer.getBitmap());
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            ExportIcon.setImageResource(R.drawable.social_expert_icon);
                        }
                    });
                    VolleySingleton.getVolleySingleton(SocialExportActivity.this)
                            .getRequestQueue()
                            .add(new JsonArrayRequest(
                                    HttpAdress.BASE_commentAction_queryAllCommentByDoctorId
                                            + "?doctorId="
                                            + doctorId
                                            + "&&userId="
                                            + userId, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray jsonArray) {
                                    int len = jsonArray.length();
                                    for (int i = 0; i < len; i++) {
                                        try {
                                            JSONObject object = jsonArray.getJSONObject(i);
                                            StringBuilder sb = new StringBuilder("\n");
                                            sb.append(object.opt("author"));
                                            sb.append("  ");
                                            sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(object.optString("creatTime"))));
                                            sb.append(":\n   ");
                                            sb.append(object.opt("content"));
                                            MessageTv.setText(MessageTv.getText().toString() + sb);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    MessageTv.setText("网络出错,暂时无法显示");
                                }
                            }));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            switch (resultCode) {
                case 0:
                    String message = data.getStringExtra("Content");
                    MessageTv.setText(MessageTv.getText().toString()
                            + "\n"
                            + getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString(Mine_userinfoActivity.UserInfo_username, "")
                            + "  刚刚:\n   " + message);
                    break;
            }
        }
    }
}

