package com.jlm.app.jianlemei_demo.activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import org.json.JSONArray;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Mine_NotificationActivity extends AppCompatActivity {

    @Bind(R.id.mine_notification_lv)
    ListView NotificationLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_notifiction);
        ButterKnife.bind(this);
        intiNotification();
    }

    private void intiNotification() {
        VolleySingleton.getVolleySingleton(this).getRequestQueue().add(new JsonArrayRequest(HttpAdress.BASE_noticeAction_sendNotice, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray array) {
                Log.e("Test", "" + array);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }
}
