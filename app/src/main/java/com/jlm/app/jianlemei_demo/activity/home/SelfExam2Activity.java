package com.jlm.app.jianlemei_demo.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;
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

public class SelfExam2Activity extends Activity {

    @Bind(R.id.self_exam2_layout)
    LinearLayout Layout;
    ArrayList<Integer> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_exam2);
        ButterKnife.bind(this);
        int checkid = getIntent().getIntExtra("checkid", 0);
        final String address = HttpAdress.HTTP_findAccompany_action + "?id=" + checkid;
        new AsyncTask<Void, String, String>() {
            @Override
            protected String doInBackground(Void... params) {
                BufferedReader reader;
                String result = null;
                StringBuilder builder = new StringBuilder();
                try {
                    URL url = new URL(address);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String strRead;
                    while ((strRead = reader.readLine()) != null) {
                        builder.append(strRead);
                    }
                    reader.close();
                    result = builder.toString();
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
                        listi.add(jsonObject.optInt("accompanyId"));
                        lists.add(jsonObject.optString("accompanyName"));
                    }
                } catch (JSONException ignored) {
                } catch (NullPointerException e) {
                    Toast.makeText(SelfExam2Activity.this, "网络出错", Toast.LENGTH_SHORT).show();
                } finally {
                    for (int i = 0; i < len; i++) {
                        final CheckBox checkBox = new CheckBox(SelfExam2Activity.this);
                        checkBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        checkBox.setText(lists.get(i));
                        checkBox.setId(i);
                        final int finalI = i;
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    arrayList.add(listi.get(finalI));
                                } else {
                                    arrayList.remove(arrayList.indexOf(listi.get(finalI)));
                                }
                            }
                        });
                        Layout.addView(checkBox);
                    }
                }
            }
        }.execute();
    }

    @OnClick(R.id.self_exam2_submit)
    public void onClick() {
        if (arrayList == null || arrayList.size() == 0) {
            Toast.makeText(this, "选择一项或多项并发症", Toast.LENGTH_LONG).show();
        } else {
            startActivity(new Intent(this, SelfExam3Activity.class).putIntegerArrayListExtra("id", arrayList));
        }
    }
}
