package com.jlm.app.jianlemei_demo.activity.home.mall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.jlm.app.jianlemei_demo.R;
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

public class MallActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private VolleySingleton volleySingleton;
    private MallAdapter adapter;
    private String BASE_goodsAction = HttpAdress.BASE_goodsAction;
    private String LastType = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        volleySingleton = VolleySingleton.getVolleySingleton(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String qType = getIntent().getStringExtra("qType");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "打开我的购物车", Snackbar.LENGTH_LONG)
                            .setAction("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            }).show();
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        ListView mListView = (ListView) this.findViewById(R.id.content_recyclerview);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.content_mall_radiogroup);
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.content_mall_radiobtn1:
                            initData2("1");
                            break;
                        case R.id.content_mall_radiobtn2:
                            initData2("2");
                            break;
                        case R.id.content_mall_radiobtn3:
                            initData2("3");
                            break;
                        case R.id.content_mall_radiobtn4:
                            initData2("4");
                            break;
                    }
                }
            });
        }
        adapter = new MallAdapter(this);
        if (mListView != null) {
            mListView.setAdapter(adapter);
        }
        initData(qType);
    }

    private void initData2(String type) {
        if (type == null) type = "1";
        if (LastType.equals(type)) return;
        final String finalType = type;
        LastType = type;
        new AsyncTask<Void, String, String>() {
            @Override
            protected String doInBackground(Void... params) {
                BufferedReader reader;
                String result = null;
                StringBuilder sbf = new StringBuilder();
                try {
                    URL url = new URL(BASE_goodsAction + finalType);
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
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    adapter.clear();
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        final Goods goods = new Goods();
                        goods.setId(jsonObject.optInt("id", 1000000000));
                        goods.setName(jsonObject.optString("name", ""));
                        goods.setPicture(jsonObject.optString("picture", ""));
                        goods.setGid(jsonObject.optString("gid", ""));
                        goods.setType(jsonObject.optInt("type", 0));
                        goods.setqType(jsonObject.optInt("qType", 0));
                        goods.setDescribe(jsonObject.optString("describe", ""));
                        goods.setDetailAddress(jsonObject.optString("detailAddress", ""));
                        goods.setPrice((float) jsonObject.optDouble("price"));
                        goods.setDicountReason(jsonObject.optString("dicountReason", ""));
                        goods.setDiscountprice((float) jsonObject.getDouble("discountprice"));
                        goods.setIsDiscount(jsonObject.optBoolean("isDiscount", false));
                        goods.setIsCanuseEntegral(jsonObject.optBoolean("isCanuseEntegral", false));
                        goods.setIsHot(jsonObject.optBoolean("isHot", false));
                        goods.setIsNew(jsonObject.optBoolean("isNew", false));
                        adapter.addData(goods);
                    }
                } catch (JSONException ignored) {
                } catch (NullPointerException e) {
                    Toast.makeText(MallActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
                } finally {
                    adapter.notifyDataSetChanged();
                }
            }
        }.execute();
    }


    private void initData(String type) {
        if (type == null) type = "1";
        if (LastType.equals(type)) return;
        final String finalType = type;
        LastType = type;
        new AsyncTask<Void, String, String>() {
            @Override
            protected String doInBackground(Void... params) {
                BufferedReader reader ;
                String result = null;
                StringBuilder sbf = new StringBuilder();
                try {
                    URL url = new URL(BASE_goodsAction + finalType);
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
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    adapter.clear();
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        final Goods goods = new Goods();
                        goods.setId(jsonObject.optInt("id", 1000000000));
                        goods.setName(jsonObject.optString("name", ""));
                        goods.setPicture(jsonObject.optString("picture", ""));
                        goods.setGid(jsonObject.optString("gid", ""));
                        goods.setType(jsonObject.optInt("type", 0));
                        goods.setqType(jsonObject.optInt("qType", 0));
                        goods.setDescribe(jsonObject.optString("describe", ""));
                        goods.setDetailAddress(jsonObject.optString("detailAddress", ""));
                        goods.setPrice((float) jsonObject.optDouble("price"));
                        goods.setDicountReason(jsonObject.optString("dicountReason", ""));
                        goods.setDiscountprice((float) jsonObject.getDouble("discountprice"));
                        goods.setIsDiscount(jsonObject.optBoolean("isDiscount", false));
                        goods.setIsCanuseEntegral(jsonObject.optBoolean("isCanuseEntegral", false));
                        goods.setIsHot(jsonObject.optBoolean("isHot", false));
                        goods.setIsNew(jsonObject.optBoolean("isNew", false));
                        adapter.addDataANDRefresh(goods);
                    }
                } catch (JSONException ignored) {
                } catch (NullPointerException e) {
                    Toast.makeText(MallActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    public class MallAdapter extends BaseAdapter {
        ArrayList<Goods> goodsArrayList = new ArrayList<>();
        public Context context;
        public LayoutInflater inflater;

        public MallAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public int getCount() {
            return goodsArrayList != null ? goodsArrayList.size() : 0;
        }

        @Override
        public Goods getItem(int position) {
            return goodsArrayList != null ? goodsArrayList.get(position) : null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public void clear() {
            goodsArrayList.clear();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Goods1ViewHolder holder1;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_mall_goods1, parent, false);
                holder1 = new Goods1ViewHolder(convertView);
                convertView.setTag(holder1);
            } else {
                holder1 = (Goods1ViewHolder) convertView.getTag();
            }
            holder1.setView(getItem(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MallActivity.this, MallDetailActivity.class).putExtra("goods", getItem(position).getDetailAddress()));
                }
            });
            return convertView;
        }

        public void addData(Goods goods) {
            goodsArrayList.add(goods);
        }


        public void addDataANDRefresh(Goods goods) {
            goodsArrayList.add(goods);
            notifyDataSetChanged();
        }
    }


    public class Goods3ViewHolder {
        @Bind(R.id.item_mall_goods_img3)
        ImageView MallgoodImg3;
        @Bind(R.id.item_mall_goods_name3)
        TextView MallgoodName3;
        @Bind(R.id.item_mall_goods_price3)
        TextView MallgoodPrice3;
        @Bind(R.id.item_mall_goods_discountprice3)
        TextView MallgoodDiscountPrice3;


        Goods3ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setView(Goods goods) {
            MallgoodName3.setText(goods.getName());
            MallgoodPrice3.setText("特惠价:" + goods.getPrice() + "");
            MallgoodPrice3.setTextColor(Color.parseColor("#13b8f4"));
            MallgoodDiscountPrice3.setText("劲爆价:" + goods.getDiscountprice());
            MallgoodDiscountPrice3.setTextColor(Color.parseColor("#df2504"));

            volleySingleton.getImageLoader().get(goods.getPicture(), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    MallgoodImg3.setImageBitmap(imageContainer.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
        }
    }


    public class Goods2ViewHolder {
        @Bind(R.id.item_mall_goods_img2)
        ImageView MallGoodsImg2;
        @Bind(R.id.item_mall_goods_name2)
        TextView MallGoodsName2;
        @Bind(R.id.item_mall_goods_descibe2)
        TextView MallGoodsDescibe2;
        @Bind(R.id.item_mall_goods_price2)
        TextView MallGoodsPrice2;
        @Bind(R.id.item_mall_goods_discountprice2)
        TextView MallGoodsDiscountPrice2;

        Goods2ViewHolder(View view) {

            ButterKnife.bind(this, view);
        }

        public void setView(Goods goods) {
            MallGoodsName2.setText(goods.getName());
            MallGoodsDescibe2.setText(goods.getDescribe());
            MallGoodsPrice2.setText(":" + goods.getPrice());
            MallGoodsDiscountPrice2.setText("劲爆价:" + goods.getDiscountprice());
            volleySingleton.getImageLoader().get(goods.getPicture(), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    MallGoodsImg2.setImageBitmap(imageContainer.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
        }
    }

    public class Goods1ViewHolder {
        @Bind(R.id.item_mall_goods_img1)
        ImageView MallGoodsImg1;
        @Bind(R.id.item_mall_goods_name1)
        TextView MallGoodsName1;
        @Bind(R.id.item_mall_goods_descibe1)
        TextView MallGoodsDescribe1;
        @Bind(R.id.item_mall_goods_price1)
        TextView MallGoodsPrice1;
        @Bind(R.id.item_mall_goods_discountprice1)
        TextView MallGoodsDiscountPrice1;

        Goods1ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setView(Goods goods) {
            volleySingleton.getImageLoader().get(goods.getPicture(), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    MallGoodsImg1.setImageBitmap(imageContainer.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }
            });
            MallGoodsName1.setText(goods.getName());
            MallGoodsDescribe1.setText(goods.getDescribe());
            if (!goods.getIsDiscount()) {
                MallGoodsPrice1.setText("价格:" + goods.getPrice());
                MallGoodsDiscountPrice1.setVisibility(View.GONE);
            } else {
                SpannableString string = new SpannableString("原价:" + goods.getPrice());
                string.setSpan(new StrikethroughSpan(), 3, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                MallGoodsPrice1.setText(string);
                MallGoodsDiscountPrice1.setText("特惠价:" + goods.getDiscountprice());
                MallGoodsDiscountPrice1.setVisibility(View.VISIBLE);
            }
        }
    }
}


