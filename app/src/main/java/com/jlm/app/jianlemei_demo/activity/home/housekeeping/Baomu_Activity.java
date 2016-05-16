package com.jlm.app.jianlemei_demo.activity.home.housekeeping;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jlm.app.jianlemei_demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Baomu_Activity extends Activity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiazhenginfo_title);
        listview = (ListView) findViewById(R.id.jiazheng_list);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.jiazheng_listitem,
                new String[]{"num1", "num2", "year1", "year2"},
                new int[]{R.id.text_num1, R.id.text_num2, R.id.text_year1, R.id.text_year2});
        listview.setAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("num1", "员工编号");
        map.put("num2", "02");
        map.put("year1", "工龄");
        map.put("year2", "1年");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("num1", "员工编号");
        map.put("num2", "01");
        map.put("year1", "工龄");
        map.put("year2", "1年");
        list.add(map);


        return list;
    }
}
