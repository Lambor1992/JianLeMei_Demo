package com.jlm.app.jianlemei_demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.health.HealthCookFoodActivity;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.User.Cookbook;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HealthCookFoodAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Cookbook> list = new ArrayList<>();
    private Drawable iReView;
    private Drawable iView;
    private VolleySingleton volleySingleton;
    private static final String COOKADD = HttpAdress.BASE_cookbook + "?qType=";
    private Context context;

    //判断是否有标签，没有标签 这查询全部数据，否则将等待调用set方法设置查询内容
    public HealthCookFoodAdapter(Context context, boolean hasTag) {
        this.context = context;
        iReView = ContextCompat.getDrawable(context, R.drawable.health_case_review);
        iView = ContextCompat.getDrawable(context, R.drawable.health_case_view);
        volleySingleton = new VolleySingleton(context);
        inflater = LayoutInflater.from(context);
        if (!hasTag) {
            getAll();
        }
    }

    public HealthCookFoodAdapter(Context context, String state) {
        this.context = context;
        iReView = ContextCompat.getDrawable(context, R.drawable.health_case_review);
        iView = ContextCompat.getDrawable(context, R.drawable.health_case_view);
        volleySingleton = new VolleySingleton(context);
        inflater = LayoutInflater.from(context);
        if (state != null) {
            State(state);
        } else getAll();
    }

    /*
    所有食谱
     */
    private void getAll() {
        volleySingleton.addToRequestQueue(getStringRequest(COOKADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (!s.startsWith("[")) return;
                list = (ArrayList<Cookbook>) JSON.parseArray(s, Cookbook.class);
                notifyDataSetChanged();
            }
        }));
    }

    /*
    特殊食谱
     */
    public void State(String state) {
//        volleySingleton.getRequestQueue().add(new JsonArrayRequest(HttpAdress.BASE_cookbookbyType + state, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray jsonArray) {
//                Log.e("Test", "___________" + jsonArray);
//                list = (ArrayList<Cookbook>) JSON.parseArray(jsonArray.toString(), Cookbook.class);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        }));
        volleySingleton.addToRequestQueue(getStringRequest(HttpAdress.BASE_cookbookbyType + state, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                list = (ArrayList<Cookbook>) JSON.parseArray(s, Cookbook.class);
                notifyDataSetChanged();
            }
        }));
    }

    public StringRequest getStringRequest(String url, Response.Listener<String> listener) {
        return new StringRequest(Request.Method.GET, url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Cookbook getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_health_case_cookbook_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setView(getItem(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, HealthCookFoodActivity.class).putExtra("address", getItem(position).getAddress()));
            }
        });
        return convertView;
    }


    class ViewHolder {
        @Bind(R.id.item_health_case_cookbook_icon)
        ImageView Img;
        @Bind(R.id.item_health_case_cookbook_title)
        TextView Title;
        @Bind(R.id.item_health_case_cookbook_data)
        TextView Data;
        @Bind(R.id.item_health_case_cookbook_view)
        TextView View;
        @Bind(R.id.item_health_case_cookbook_review)
        TextView Review;

        ViewHolder(android.view.View view) {
            ButterKnife.bind(this, view);
            Img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Img.getWidth(), (int) (Img.getWidth() * 0.8));
                    params.setMargins(0, 3, 0, 3);
                    Img.setLayoutParams(params);
                }
            });
            Review.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int i = (int) Review.getTextSize() + 1;
                    iReView.setBounds(0, 0, i, i);
                    iView.setBounds(0, 0, i, (int) (i * 0.6));
                    Review.setCompoundDrawables(iReView, null, null, null);
                    View.setCompoundDrawables(iView, null, null, null);
                }
            });
            Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, Title.getTextSize() * 1.1f);
        }

        public void setView(Cookbook cookbook) {
            this.Review.setText(String.valueOf(cookbook.getReview()));
            this.Data.setText(cookbook.getUploadData());
            this.Title.setText(cookbook.getTitle());
            this.View.setText(String.valueOf(cookbook.getTotalView()));
            volleySingleton.getImageLoader().get(cookbook.getLittlePic(), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    Img.setImageBitmap(imageContainer.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
        }
    }
}
