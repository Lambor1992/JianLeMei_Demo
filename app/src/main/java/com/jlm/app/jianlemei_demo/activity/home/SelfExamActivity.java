package com.jlm.app.jianlemei_demo.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.db.health.SelfExam;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SelfExamActivity extends Activity {
    @Bind(R.id.self_exam_choose_group)
    RadioGroup ChooseGroup;
    int checkid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_exam_choose);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String body = intent.getStringExtra("body");
        int age = intent.getIntExtra("age", 0);
        int sex = intent.getIntExtra("sex", 0);
        int id = 0;
        for (int i = 0; i < SelfExam.bodys.length; i++) {
            if (body.equals(SelfExam.bodys[i]))
                id = i;
        }
        final String address = HttpAdress.HTTP_bodyAction_findSymptom
                + "?id="
                + id
                + "&&age="
                + age
                + "&&sex="
                + sex;

        new AsyncTask<Void, String, String>() {
            @Override
            protected String doInBackground(Void... params) {
                BufferedReader reader;
                String result = null;
                StringBuilder sbf = new StringBuilder();
                try {
                    URL url = new URL(address);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String strRead ;
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

            @Override
            protected void onPostExecute(String s) {
                final ArrayList<Integer> listi = new ArrayList<>();
                ArrayList<String> lists = new ArrayList<>();
                int len = 0;
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        listi.add(jsonObject.optInt("symptom_id"));
                        lists.add(jsonObject.optString("symptom_name"));
                    }
                } catch (JSONException ignored) {
                } catch (NullPointerException e) {
                    Toast.makeText(SelfExamActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
                } finally {
                    for (int i = 0; i < len; i++) {
                        RadioButton btn = new RadioButton(SelfExamActivity.this);
                        btn.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT, 1));
                        final int finalI = i;
                        btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (buttonView.isPressed()) {
                                    checkid = listi.get(finalI);
                                }
                            }
                        });
                        btn.setId(i);
                        btn.setText(lists.get(i));
                        btn.setChecked(false);
                        ChooseGroup.addView(btn);
                    }
                }
            }
        }.execute();
    }

    @OnClick(R.id.self_exam_choose_submit)
    public void onClick() {
        startActivity(new Intent(this, SelfExam2Activity.class).putExtra("checkid", checkid));
    }
}
