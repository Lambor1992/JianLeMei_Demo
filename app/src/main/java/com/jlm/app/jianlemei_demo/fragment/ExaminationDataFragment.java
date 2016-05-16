package com.jlm.app.jianlemei_demo.fragment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.data.BMIActivity;
import com.jlm.app.jianlemei_demo.activity.data.BloodPressShowActivity;
import com.jlm.app.jianlemei_demo.activity.data.BloodViscosiyActivity;
import com.jlm.app.jianlemei_demo.activity.data.HearingActivity;
import com.jlm.app.jianlemei_demo.activity.data.HeartRateActivity;
import com.jlm.app.jianlemei_demo.activity.data.OxygenActivity;
import com.jlm.app.jianlemei_demo.activity.data.TemperatureActivity;
import com.jlm.app.jianlemei_demo.activity.data.VisionActivity;
import com.jlm.app.jianlemei_demo.activity.data.VitalCapacityActivity;
import com.jlm.app.jianlemei_demo.activity.data.WeightActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_ExamCenterActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.VitalcapacityActivity;
import com.jlm.app.jianlemei_demo.data.ExmaData;
import com.jlm.app.jianlemei_demo.db.bean.BMIBean;
import com.jlm.app.jianlemei_demo.utils.calendar.CalendarAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExaminationDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExaminationDataFragment extends Fragment {
    private static ExaminationDataFragment fragment;
    @Bind(R.id.fg_exam_data_calendar_prevMonth)
    ImageView ExamDataCalendarPrevMonth;
    @Bind(R.id.fg_exam_data_calendar_currentMonth)
    TextView ExamDataCalendarCurrentMonth;
    @Bind(R.id.fg_exam_data_calendar_nextMonth)
    ImageView ExamDataCalendarNextMonth;
    @Bind(R.id.fg_exam_data_calendar_flipper)
    ViewFlipper ExamDataCalendarFlipper;
    @Bind(R.id.fg_exam_data_calendar)
    LinearLayout ExamDataCalendar;
    @Bind(R.id.fg_exam_data_calendar_showtime_btn)
    Button ExamDataCalendarShowtimeBtn;
    @Bind(R.id.fg_exam_data_listview)
    ListView ExamDataListview;

    private GestureDetector gestureDetector = null;
    private CalendarAdapter calV = null;
    private static int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";
    private int gvFlag = 0;
    private GridView gridView = null;
    private ViewFlipper flipper = null;

    public ExaminationDataFragment() {
    }

    public static ExaminationDataFragment newInstance() {
        if (fragment == null) return new ExaminationDataFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examination_data, container, false);
        ButterKnife.bind(this, view);
        flipper = (ViewFlipper) view.findViewById(R.id.fg_exam_data_calendar_flipper);
        gridView = (GridView) view.findViewById(R.id.fg_exam_data_calendar_content);
        init();
        gestureDetector = new GestureDetector(getActivity(), new MyGestureListener());
        flipper.removeAllViews();
        ExamDataCalendarShowtimeBtn.setText("" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " (点击查看日历)");
        calV = new CalendarAdapter(getActivity(), getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        addGridView();
        gridView.setAdapter(calV);
        flipper.addView(gridView, 0);
        addTextToTopTextView(ExamDataCalendarCurrentMonth);
        ExamDataListview.setAdapter(new MyExamDataAdapter(getActivity()));
        ExamDataListview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        ExamDataListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void init() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date); // 当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fg_exam_data_calendar_prevMonth, R.id.fg_exam_data_calendar_nextMonth, R.id.fg_exam_data_calendar_showtime_btn})
    public void onClick(View view) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        switch (view.getId()) {
            case R.id.fg_exam_data_calendar_prevMonth:
                enterPrevMonth(gvFlag);
                break;
            case R.id.fg_exam_data_calendar_nextMonth:
                enterNextMonth(gvFlag);
                break;
            case R.id.fg_exam_data_calendar_showtime_btn:
                if (ExamDataCalendar.getVisibility() == View.GONE) {
                    ExamDataCalendar.setVisibility(View.VISIBLE);
                    ExamDataCalendarShowtimeBtn.setText("点击隐藏日历");
                } else {
                    ExamDataCalendar.setVisibility(View.GONE);
                    ExamDataCalendarShowtimeBtn.setText("" + format.format(new Date()) + " (点击查看日历)");
                }
                break;
        }
    }

    public class MyExamDataAdapter extends BaseAdapter {
        LayoutInflater inflater;
        String[] list = ExmaData.EXAMINATION_DATA;
        boolean[] isWrite = ExmaData.isWriteEXAMINATION_DATA;
        boolean[] isConnect = ExmaData.isConnectEXAMINATION_DATA;
        boolean[] isExam = ExmaData.isExamEXAMINATION_DATA;

        MyExamDataAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public String getItem(int position) {
            return list[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_exam_data_listview_layout, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.setView(list[position], isWrite[position], isConnect[position], isExam[position], position);
            convertView.setOnClickListener(new View.OnClickListener() {
                //                                                        0        1          2         3      4
//                public static final String[] EXAMINATION_DATA = {"血压", "体质指数", "体温", "心率", "血氧",/
//                          5        6         7        8        9
//                        "情绪", "视力值", "色盲", "色弱", "听力",
//                            10           11          12
//                        "肺活量", "抗压指数", "抑郁症",
//                           13      14        15         16          17         18
//                        "自闭症", "身高", "体重", "血液粘度", "脉搏度", "步数"};
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(getActivity(), BloodPressShowActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(getActivity(), BMIActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(getActivity(), TemperatureActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(getActivity(), HeartRateActivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(getActivity(), OxygenActivity.class));
                            break;
                        case 5:
                            startActivity(new Intent(getActivity(), VisionActivity.class));
                            break;
                        case 6:
                            startActivity(new Intent(getActivity(), VisionActivity.class));
                            break;
                        case 7:
                            startActivity(new Intent(getActivity(), HearingActivity.class));
                            break;
                        case 8:
                            startActivity(new Intent(getActivity(), VitalCapacityActivity.class));
                            break;

                        case 9:
                            startActivity(new Intent(getActivity(), BloodViscosiyActivity.class));
                            break;
                        default:
                            Toast.makeText(getActivity(), "暂未开通此服务", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.item_fg_exam_data_list_name)
            TextView itemFgExamDataListName;
            @Bind(R.id.item_fg_examdta_1)
            ImageButton itemFgExamdta1;
            @Bind(R.id.item_fg_examdta_2)
            ImageButton itemFgExamdta2;
            @Bind(R.id.item_fg_examdta_3)
            ImageButton itemFgExamdta3;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }

            public void setView(final String name, boolean isWrite, boolean isConnect, boolean isExam, final int position) {
                this.itemFgExamDataListName.setText(name);
                this.itemFgExamdta1.setVisibility(isConnect ? View.VISIBLE : View.GONE);
                this.itemFgExamdta2.setVisibility(isExam ? View.VISIBLE : View.GONE);
                this.itemFgExamdta3.setVisibility(isWrite ? View.VISIBLE : View.GONE);

                this.itemFgExamdta2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), ExmaData.ExamList.get(position)));
                    }
                });
                this.itemFgExamdta3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            new ExmaData().getDialogDialog(getActivity(), position).create().show();
                        } catch (NullPointerException e) {
                            new AlertDialog.Builder(getActivity()).setIcon(R.drawable.icon).setMessage("敬请期待")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).create().show();
                        }
                    }
                });
            }
        }
    }


    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int gvFlag = 0; // 每次添加gridview到viewflipper中时给的标记
            if (e1.getX() - e2.getX() > 120) {
                enterNextMonth(gvFlag);
                return true;
            } else if (e1.getX() - e2.getX() < -120) {
                enterPrevMonth(gvFlag);
                return true;
            }
            return false;
        }
    }

    private void enterNextMonth(int gvFlag) {
        addGridView(); // 添加一个gridView
        jumpMonth++; // 下一个月
        calV = new CalendarAdapter(getActivity(), this.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        gridView.setAdapter(calV);
        addTextToTopTextView(ExamDataCalendarCurrentMonth); // 移动到下一月后，将当月显示在头标题中
        gvFlag++;
        flipper.addView(gridView, gvFlag);
        flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        flipper.showNext();
        flipper.removeViewAt(0);
    }

    private void enterPrevMonth(int gvFlag) {
        addGridView(); // 添加一个gridView
        jumpMonth--; // 上一个月
        calV = new CalendarAdapter(getActivity(), this.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        gridView.setAdapter(calV);
        gvFlag++;
        addTextToTopTextView(ExamDataCalendarCurrentMonth); // 移动到上一月后，将当月显示在头标题中
        flipper.addView(gridView, gvFlag);
        flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_out));
        flipper.showPrevious();
        flipper.removeViewAt(0);
    }

    public void addTextToTopTextView(TextView view) {
        StringBuffer textDate = new StringBuffer();
        textDate.append(calV.getShowYear()).append("年").append(calV.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
    }

    private void addGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int Width = display.getWidth();
        int Height = display.getHeight();
        gridView = new GridView(getActivity());
        gridView.setNumColumns(7);
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setVerticalSpacing(1);
        gridView.setHorizontalSpacing(1);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub
                int startPosition = calV.getStartPositon();
                int endPosition = calV.getEndPosition();
                if (startPosition <= position + 7 && position <= endPosition - 7) {
                    String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0]; // 这一天的阳历
                    String scheduleYear = calV.getShowYear();
                    String scheduleMonth = calV.getShowMonth();
                    Toast.makeText(getActivity(), scheduleYear + "-" + scheduleMonth + "-" + scheduleDay, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
