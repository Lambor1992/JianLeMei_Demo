package com.jlm.app.jianlemei_demo.activity.data;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.adapter.TemperatureAdapter;
import com.jlm.app.jianlemei_demo.db.bean.TemperatureBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;
import com.jlm.app.jianlemei_demo.widget.TitleBar;

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

public class TemperatureActivity extends Activity {
    @Bind(R.id.exam_data_single_title)
    TitleBar Title;
    @Bind(R.id.exam_data_single_chart)
    ColumnChartView SingleChart;
    @Bind(R.id.exam_data_single_name_tv)
    TextView NameTv;
    @Bind(R.id.exam_data_single_listview)
    ListView mListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_single_layout);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        Title.setTitleName("体温监测");
        NameTv.setText("体温");
        TemperatureAdapter adapter = new TemperatureAdapter(this);
        mListview.setAdapter(adapter);
        ExamSQLHelper examSQLHelper = new ExamSQLHelper(this);
        try {
            ArrayList<TemperatureBean> xList = (ArrayList<TemperatureBean>) examSQLHelper.getDao(TemperatureBean.class).queryForAll();
            adapter.setData(xList);
            initChartView(xList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            examSQLHelper.close();
        }
    }

    private void initChartView(ArrayList<TemperatureBean> list) {
        int numColumns = list.size();
        List<Column> columns = new ArrayList<>(numColumns);
        List<AxisValue> axisValues = new ArrayList<>(numColumns);
        Axis axisX = new Axis();
        for (int i = 0; i < numColumns; i++) {
            List<SubcolumnValue> values = new ArrayList<>();
            values.add(new SubcolumnValue(list.get(i).getTemperature(), Color.parseColor("#f237e3")));
            Column column = new Column(values);
            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
            axisValues.add(new AxisValue((float) i).setLabel(dataFormat(list.get(i).getRecordTime())));
        }
        ColumnChartData data = new ColumnChartData(columns);
        axisX.setValues(axisValues);
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(new Axis().setHasLines(true));
        SingleChart.setColumnChartData(data);
    }

    public String dataFormat(String data) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.valueOf(data)));
    }
}
