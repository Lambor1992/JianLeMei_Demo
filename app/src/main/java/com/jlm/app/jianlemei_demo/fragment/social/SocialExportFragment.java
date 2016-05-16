package com.jlm.app.jianlemei_demo.fragment.social;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.social.SocialExportActivity;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

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

public class SocialExportFragment extends Fragment {
    @Bind(R.id.fg_social_export_listview)
    ListView SocialExportListview;
    private MyAdapter adapter;

    public SocialExportFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social_export, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new MyAdapter(getActivity());
        SocialExportListview.setMinimumHeight(80);
        SocialExportListview.setAdapter(adapter);
        adapter.setData();
        SocialExportListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class MyAdapter extends BaseAdapter {
        String adress = HttpAdress.BASE_export;
        LayoutInflater inflater;
        ArrayList<DOCTOR> list = new ArrayList<>(7);
        VolleySingleton volley = null;

        public MyAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
            volley = VolleySingleton.getVolleySingleton(getActivity());
        }

        public void setData() {
            new AsyncTask<Void, String, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    BufferedReader reader = null;
                    String result = null;
                    StringBuffer sbf = new StringBuffer();
                    try {
                        URL url = new URL(adress);
                        HttpURLConnection connection = (HttpURLConnection) url
                                .openConnection();
                        connection.setRequestMethod("GET");
                        connection.connect();
                        InputStream is = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        String strRead = null;
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
                    try {
                        JSONArray jsonArray = new JSONArray(s);
                        int len = jsonArray.length();
                        for (int i = 0; i < len; i++) {
                            list.add(new DOCTOR(jsonArray.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        Toast.makeText(getActivity(), "网络出错", Toast.LENGTH_SHORT).show();
                    }
                    notifyDataSetChanged();
                    Log.e("Test", "__________________");
                }

            }.execute();
        }

        @Override
        public int getCount() {
            if (list == null) return 0;
            else return list.size();
        }

        @Override
        public DOCTOR getItem(int position) {
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
                convertView = inflater.inflate(R.layout.item_social_export_layout, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.setData(getItem(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), SocialExportActivity.class).putExtra("address", getItem(position).address));
                }
            });
            return convertView;
        }

        public class ViewHolder {
            @Bind(R.id.item_social_export_icon)
            ImageView itemSocialExportIcon;
            @Bind(R.id.item_social_export_name)
            TextView itemSocialExportName;
            @Bind(R.id.item_social_export_position)
            TextView itemSocialExportPosition;
            @Bind(R.id.item_social_export_hospital)
            TextView itemSocialExportHospital;
            @Bind(R.id.item_social_export_expert)
            TextView itemSocialExportExpert;
            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
            public void setData(DOCTOR doctor) {
                ImageRequest imageRequest = new ImageRequest(doctor.getIcon(), new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        itemSocialExportIcon.setImageBitmap(bitmap);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });
                volley.addToRequestQueue(imageRequest);
                this.itemSocialExportExpert.setText("科室:" + doctor.getExpert());
                this.itemSocialExportHospital.setText("单位:" + doctor.getHospital());
                this.itemSocialExportName.setText(doctor.getName());
                this.itemSocialExportPosition.setText("职位:" + doctor.getPosition());
            }
        }

        public class DOCTOR {
            String icon;
            String name;
            String position;
            String hospital;
            String expert;
            String address;
            String id;

            DOCTOR(JSONObject json) {
                try {
                    this.id = json.getString("id");
                    this.icon = json.getString("icon");
                    this.name = json.getString("name");
                    this.position = json.getString("position");
                    this.hospital = json.getString("hospital");
                    this.expert = json.getString("expert");
                    this.address = json.getString("address");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public String getIcon() {
                return icon;
            }

            public String getName() {
                return name;
            }

            public String getPosition() {
                return position;
            }

            public String getHospital() {
                return hospital;
            }

            public String getExpert() {
                return expert;
            }

            public String getAdress() {
                return adress;
            }
        }
    }
}
