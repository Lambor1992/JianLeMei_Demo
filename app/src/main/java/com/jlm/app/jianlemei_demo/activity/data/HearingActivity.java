package com.jlm.app.jianlemei_demo.activity.data;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.adapter.BloodPressAdapter;
import com.jlm.app.jianlemei_demo.activity.adapter.HearingAdapter;
import com.jlm.app.jianlemei_demo.db.bean.BloodPressBean;
import com.jlm.app.jianlemei_demo.db.bean.HearingBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

public class HearingActivity extends Activity {

    @Bind(R.id.exam_data_double_title)
    ColumnChartView chart;
    @Bind(R.id.exam_data_double_listview)
    ListView listView;
    @Bind(R.id.exam_data_double_info1)
    TextView info1;
    @Bind(R.id.exam_data_double_info2)
    TextView info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_double_layout);
        ButterKnife.bind(this);
        info1.setText("低频");
        info2.setText("高频");
        HearingAdapter adapter = new HearingAdapter(this);
        listView.setAdapter(adapter);
        ExamSQLHelper examSQLHelper = new ExamSQLHelper(this);
        try {
            ArrayList<HearingBean> xList = (ArrayList<HearingBean>) examSQLHelper.getDao(HearingBean.class).queryForAll();
            adapter.setData(xList);
            initChartView(xList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            examSQLHelper.close();
        }
    }

    public void initChartView(ArrayList<HearingBean> list) {
        int numColumns = list.size();
        List<Column> columns = new ArrayList<>(numColumns);
        List<AxisValue> axisValues = new ArrayList<>(numColumns);
        Axis axisX = new Axis();
        for (int i = 0; i < numColumns; i++) {
            List<SubcolumnValue> values = new ArrayList<>();
            values.add(new SubcolumnValue(list.get(i).getHearmingL(), Color.parseColor("#f237e3")));
            values.add(new SubcolumnValue(list.get(i).getHearmingH(), Color.parseColor("#12B7F5")));
            Column column = new Column(values);
            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(true);
            columns.add(column);
            axisValues.add(new AxisValue(Float.valueOf(i)).setLabel(dataFormat(list.get(i).getRecordTime())));
        }
        ColumnChartData data = new ColumnChartData(columns);
        axisX.setValues(axisValues);
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(new Axis().setHasLines(true));
        chart.setColumnChartData(data);
    }

    public String dataFormat(String data) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.valueOf(data)));
    }
}