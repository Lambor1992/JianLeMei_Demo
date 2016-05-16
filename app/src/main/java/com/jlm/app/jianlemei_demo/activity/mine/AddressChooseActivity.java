package com.jlm.app.jianlemei_demo.activity.mine;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.address.City;
import com.jlm.app.jianlemei_demo.utils.address.Dis;
import com.jlm.app.jianlemei_demo.utils.address.Pro;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class AddressChooseActivity extends Activity {
    private ListView mListView;
    private MyAddressAdapter addressAdapter;
    private ArrayList<City> CityList = null;
    private ArrayList<Dis> DisList = null;
    private String city = null;
    private boolean listflag = true;
    private String[] mHotCity = new String[]{"成都", "北京", "上海", "杭州", "西安", "绵阳", "广州"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_choose);
        initView();
        addressAdapter = new MyAddressAdapter(this);
        mListView.setAdapter(addressAdapter);
        ArrayList<Pro> proList = getProList();
        addressAdapter.setData(proList);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<Pro> getProList() {
        ArrayList<Pro> list = null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            try (JSONReader reader = new JSONReader(new BufferedReader(new InputStreamReader(getAssets().open("pro.txt"))))) {
                String str = reader.readString();
                list = (ArrayList<Pro>) JSON.parseArray(str, Pro.class);
            } catch (IOException e) {
                Log.e("Address", "Error");
            }
            return list;
        } else {
            JSONReader reader;
            try {
                reader = new JSONReader(new BufferedReader(new InputStreamReader(getAssets().open("pro.txt"))));
                String str = reader.readString();
                list = (ArrayList<Pro>) JSON.parseArray(str, Pro.class);
                reader.close();
            } catch (IOException e) {
                Log.e("Address", "Error");
            }
            return list;
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<City> getCityList(String ProID) {
        ArrayList<City> list = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try (JSONReader reader = new JSONReader(new BufferedReader(new InputStreamReader(getAssets().open("city.txt"))))) {
                String str = reader.readString();
                list = (ArrayList<City>) JSON.parseArray(str, City.class);
            } catch (IOException e) {
                Log.e("Address", "Error");
            }
        } else {
            JSONReader reader;
            try {
                reader = new JSONReader(new BufferedReader(new InputStreamReader(getAssets().open("city.txt"))));
                Log.e("Address", "" + reader);
                String str = reader.readString();
                Log.e("Address", "" + str);
                list = (ArrayList<City>) JSON.parseArray(str, City.class);
                reader.close();
            } catch (IOException e) {
                Log.e("Address", "Error");
            }
        }
        Log.e("Address", "" + list);
        Iterator<City> iterator = list != null ? list.iterator() : null;
        assert iterator != null;
        Log.e("Address", "" + iterator);
        while (iterator.hasNext()) {
            if (!Objects.equals(iterator.next().getProID(), ProID)) iterator.remove();
        }
        return list;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<Dis> getDisList(String CityID) {
        ArrayList<Dis> list = null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            try (JSONReader reader = new JSONReader(new BufferedReader(new InputStreamReader(getAssets().open("dis.txt"))))) {
                String str = reader.readString();
                list = (ArrayList<Dis>) JSON.parseArray(str, Dis.class);
            } catch (IOException e) {
                Log.e("Address", "Error");
            }
        } else {
            JSONReader reader;
            try {
                reader = new JSONReader(new BufferedReader(new InputStreamReader(getAssets().open("dis.txt"))));
                String str = reader.readString();
                list = (ArrayList<Dis>) JSON.parseArray(str, Dis.class);
                reader.close();
            } catch (IOException e) {
                Log.e("Address", "Error");
            }
        }
        Iterator<Dis> iterator = list != null ? list.iterator() : null;
        assert iterator != null;
        while (iterator.hasNext()) {
            if (!Objects.equals(iterator.next().getCityID(), CityID)) iterator.remove();
        }
        return list;
    }


    private void initView() {
        this.mListView = (ListView) findViewById(R.id.address_choose_listview);
        GridView mHotCityGrid = (GridView) findViewById(R.id.adrress_choose_gridview);
        mHotCityGrid.setAdapter(new ArrayAdapter<>(this, R.layout.popup_address_item, mHotCity));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listflag) {
                    CityList = getCityList(getProList().get(position).getProID());
                    addressAdapter.setData(CityList);
                    listflag = false;
                } else {
                    city = CityList.get(position).getName();
                    DisList = getDisList(CityList.get(position).getCityID());
                    setResult(110, new Intent().putParcelableArrayListExtra("dis", DisList).putExtra("city", city));
                    finish();
                }
            }
        });
    }


    private class MyAddressAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<String> address = null;

        public MyAddressAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        public void setData(ArrayList list) {
            if (null == list) return;
            if (address == null) address = new ArrayList<>();
            else address.clear();
            if (list.get(0) instanceof Pro) {
                @SuppressWarnings("unchecked") ArrayList<Pro> tempList = (ArrayList<Pro>) list;
                for (Pro adr : tempList) {
                    address.add(adr.getName());
                }
            } else if (list.get(0) instanceof City) {
                @SuppressWarnings("unchecked") ArrayList<City> tempList = (ArrayList<City>) list;
                for (City adr : tempList) {
                    address.add(adr.getName());
                }
            } else if (list.get(0) instanceof Dis) {
                @SuppressWarnings("unchecked") ArrayList<Dis> tempList = (ArrayList<Dis>) list;
                for (Dis adr : tempList) {
                    address.add(adr.getDisName());
                }
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (address == null) return 0;
            return address.size();
        }

        @Override
        public String getItem(int position) {
            return address.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.popup_address_item_layout, null);
                holder.mTextView = (TextView) convertView.findViewById(R.id.popup_address_item_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mTextView.setText(getItem(position));
            return convertView;
        }

        class ViewHolder {
            TextView mTextView;
        }
    }
}
