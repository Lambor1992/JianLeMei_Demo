package com.jlm.app.jianlemei_demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.home.ConsultationActivity;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/29.
 */
public class HealthConsultAdapter extends BaseAdapter {
    private Context context;
    public ArrayList<Consult> list = new ArrayList<>();
    private VolleySingleton volleySingleton;

    public HealthConsultAdapter(Context context) {
        this.context = context;
        volleySingleton = VolleySingleton.getVolleySingleton(context);
        setData();
    }

    public void setData() {
        volleySingleton.addToRequestQueue(new StringRequest(HttpAdress.BASE_consult, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray array = new JSONArray(s);
                    int len = array.length();
                    for (int i = 0; i < len; i++) {
                        list.add(new Consult(array.getJSONObject(i)));
                    }
                    notifyDataSetChanged();
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
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Consult getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_textview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setView(getItem(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ConsultationActivity.class).putExtra("address", getItem(position).address));
            }
        });
        return convertView;
    }

    public class ViewHolder {
        @Bind(R.id.textview_tv)
        TextView textviewTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setView(final Consult consult) {
            this.textviewTv.setText(consult.title);

        }
    }
}

class Consult {
    int id;
    String title;
    String address;

    public Consult(JSONObject jsonObject) {
        this.id = jsonObject.optInt("id");
        this.title = jsonObject.optString("title");
        this.address = jsonObject.optString("address");
    }
}
