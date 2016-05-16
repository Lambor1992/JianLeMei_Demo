package com.jlm.app.jianlemei_demo.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.AutismActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.BloodPressActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.BloodViscosiyActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.DepressionExamActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.EarExamActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.EmotionActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.EyeExamActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.HeartRateActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.MoreActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.OxygenActivity;
import com.jlm.app.jianlemei_demo.activity.home.examcenter.VitalcapacityActivity;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.db.bean.BMIBean;
import com.jlm.app.jianlemei_demo.db.bean.BloodPressBean;
import com.jlm.app.jianlemei_demo.db.bean.HearingBean;
import com.jlm.app.jianlemei_demo.db.bean.HeartRateBean;
import com.jlm.app.jianlemei_demo.db.bean.HeightBean;
import com.jlm.app.jianlemei_demo.db.bean.OxygenBean;
import com.jlm.app.jianlemei_demo.db.bean.TemperatureBean;
import com.jlm.app.jianlemei_demo.db.bean.VisionBean;
import com.jlm.app.jianlemei_demo.db.bean.VitalCapacityBean;
import com.jlm.app.jianlemei_demo.db.bean.WeightBean;
import com.jlm.app.jianlemei_demo.db.health.ExamSQLHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ExmaData {
    //                                                0        1          2         3      4
    public static final String[] EXAMINATION_DATA = {"血压", "体质指数", "体温", "心率", "血氧",
            //5        6         7        8        9
            "视力值", "色盲", "听力", "肺活量", "血液粘度"
    };

    public static final boolean[] isWriteEXAMINATION_DATA = getIsWriteEXAMINATION_DATA();
    public static final boolean[] isConnectEXAMINATION_DATA = getIsConnectEXAMINATION_DATA();
    public static final boolean[] isExamEXAMINATION_DATA = getIsExamEXAMINATION_DATA();
    public String username;
    public static final ArrayList<Class<? extends Activity>> ExamList = getExamList();

    public AlertDialog.Builder getDialogDialog(final Context context, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context).setTitle(EXAMINATION_DATA[position])
                .setIcon(R.drawable.icon)
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        username = context.getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString("UserInfo_username", "user1");
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(50, 20, 50, 20);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        final TextView Tv1 = new TextView(context);
        Tv1.setLayoutParams(params);
        final TextView Tv2 = new TextView(context);
        Tv2.setLayoutParams(params);
        final TextView Tv3 = new TextView(context);
        Tv3.setLayoutParams(params);
        final TextView TvSex = new TextView(context);
        TvSex.setLayoutParams(params);
        final EditText ETv1 = new EditText(context);
        final EditText ETv2 = new EditText(context);
        final EditText ETv3 = new EditText(context);

        switch (position) {
            case 0:
                Tv1.setText("收缩压(低压):");
                Tv2.setText("舒张压(高压):");
                linearLayout.addView(Tv1);
                linearLayout.addView(ETv1);
                linearLayout.addView(Tv2);
                linearLayout.addView(ETv2);
                return dialog
                        .setView(linearLayout)
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    new ExamSQLHelper(context).getDao(BloodPressBean.class).
                                            create(new BloodPressBean(Integer.valueOf(ETv1.getText().toString()), Integer.valueOf(ETv2.getText().toString()), username));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(context, "请输入正确的血压值", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            case 1:
                Tv1.setText("填写您的年龄:");
                Tv2.setText("填写您的身高(米·m):");
                Tv3.setText("填写您的体重(千克·kg):");
                linearLayout.addView(TvSex);
                linearLayout.addView(Tv1);
                linearLayout.addView(ETv1);
                linearLayout.addView(Tv2);
                linearLayout.addView(ETv2);
                linearLayout.addView(Tv3);
                linearLayout.addView(ETv3);
                TvSex.setVisibility(View.GONE);
                final AlertDialog.Builder sexDialog = new AlertDialog.Builder(context).setIcon(R.drawable.icon).setTitle(EXAMINATION_DATA[position])
                        .setView(linearLayout).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    int age = Integer.valueOf(ETv1.getText().toString());
                                    float high = Float.valueOf(ETv2.getText().toString());
                                    float weight = Float.valueOf(ETv3.getText().toString());
                                    if (high > 100) {
                                        high = high / 100;
                                    }
                                    float bim = weight / high / high;
                                    if (bim < 19) {
                                        Toast.makeText(context, "体重偏低", Toast.LENGTH_SHORT).show();
                                    } else if (bim < 25) {
                                        Toast.makeText(context, "体重处于健康范围", Toast.LENGTH_SHORT).show();
                                    } else if (bim < 30) {
                                        Toast.makeText(context, "有点超重，请加强锻炼", Toast.LENGTH_SHORT).show();
                                    } else if (bim < 39) {
                                        Toast.makeText(context, "非常超重，请加强锻炼", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "极度超重，请加强锻炼", Toast.LENGTH_SHORT).show();
                                    }

                                    new ExamSQLHelper(context).getDao(BMIBean.class).
                                            create(new BMIBean(TvSex.getText().toString().equals("男"),
                                                    age,
                                                    high,
                                                    weight));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(context, "请输入正确的身体信息", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                return dialog.setTitle(EXAMINATION_DATA[position] + "-选择性别").setItems(new String[]{" 男", " 女"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                TvSex.setText("男");
                                sexDialog.setTitle(EXAMINATION_DATA[position] + "(男)").create().show();
                                dialog.dismiss();
                                break;
                            case 1:
                                TvSex.setText("女");
                                sexDialog.setTitle(EXAMINATION_DATA[position] + "(女)").create().show();
                                dialog.dismiss();
                                break;
                        }
                    }
                });
            case 2:
                Tv1.setText("输入您的今日体温");
                linearLayout.addView(Tv1);
                linearLayout.addView(ETv1);
                return dialog.setView(linearLayout)
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    new ExamSQLHelper(context).getDao(TemperatureBean.class).create(new TemperatureBean(Float.valueOf(ETv1.getText().toString()), username));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(context, "请输入正确的体温值", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            case 3:
                Tv1.setText("输入您的今日心率");
                linearLayout.addView(Tv1);
                linearLayout.addView(ETv1);
                return dialog.setView(linearLayout)
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    new ExamSQLHelper(context).getDao(HeartRateBean.class).create(new HeartRateBean(Integer.valueOf(ETv1.getText().toString()), username));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(context, "请输入正确的心率值", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            case 4:
                Tv1.setText("输入您的今日血氧");
                linearLayout.addView(Tv1);
                linearLayout.addView(ETv1);
                return dialog.setView(linearLayout)
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    new ExamSQLHelper(context).getDao(OxygenBean.class).create(new OxygenBean(Integer.valueOf(ETv1.getText().toString()), username));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(context, "请输入正确的血氧值", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            case 5:
                Tv1.setText("输入您的今日视力值");
                linearLayout.addView(Tv1);
                linearLayout.addView(ETv1);
                return dialog.setView(linearLayout)
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    float vision = Float.valueOf(ETv1.getText().toString());
                                    if (vision > 5 || vision < 0) {
                                        Toast.makeText(context, "请确定您输入的是视力值", Toast.LENGTH_SHORT).show();
                                    }
                                    new ExamSQLHelper(context).getDao(VisionBean.class).create(new VisionBean(vision, username));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(context, "请输入正确的视力值", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            case 7:
                Tv1.setText("输入您的今日听力(低频下限)");
                Tv2.setText("输入您的今日听力(高频下限)");
                linearLayout.addView(Tv1);
                linearLayout.addView(ETv1);
                linearLayout.addView(Tv2);
                linearLayout.addView(ETv2);
                return dialog.setView(linearLayout)
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    float low = Float.valueOf(ETv1.getText().toString());
                                    float high = Float.valueOf(ETv2.getText().toString());
                                    if (high < low) {
                                        Toast.makeText(context, "请输入正确的听力值", Toast.LENGTH_SHORT).show();
                                    }
                                    new ExamSQLHelper(context).getDao(HearingBean.class).create(new HearingBean(high, low, username));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(context, "请输入正确的听力值", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            case 8:
                Tv1.setText("输入您的今日肺活量");
                linearLayout.addView(Tv1);
                linearLayout.addView(ETv1);
                return dialog.setView(linearLayout)
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    int vitalCapacity = Integer.valueOf(ETv1.getText().toString());
                                    if (vitalCapacity < 0) {
                                        Toast.makeText(context, "请输入正确的肺活量", Toast.LENGTH_SHORT).show();
                                    }
                                    new ExamSQLHelper(context).getDao(VitalCapacityBean.class).create(new VitalCapacityBean(vitalCapacity, username));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(context, "请输入正确的肺活量", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            default:
                return null;
        }
    }


    private static final ArrayList<Class<? extends Activity>> getExamList() {
        ArrayList<Class<? extends Activity>> list = new ArrayList<>(EXAMINATION_DATA.length);
        list.add(BloodPressActivity.class);
        list.add(MoreActivity.class);
        list.add(HeartRateActivity.class);
        list.add(OxygenActivity.class);
        list.add(EyeExamActivity.class);
        list.add(EyeExamActivity.class);
        list.add(EyeExamActivity.class);
        list.add(EarExamActivity.class);
        list.add(VitalcapacityActivity.class);
        list.add(BloodViscosiyActivity.class);

        return list;
    }


    private static final boolean[] getIsConnectEXAMINATION_DATA() {
        int len = EXAMINATION_DATA.length;
        boolean[] b = new boolean[len];
        return b;
    }

    private static final boolean[] getIsExamEXAMINATION_DATA() {
        int len = EXAMINATION_DATA.length;
        boolean[] b = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (EXAMINATION_DATA[i].equals("体重") ||
                    EXAMINATION_DATA[i].equals("身高") ||
                    EXAMINATION_DATA[i].equals("体温") ||
                    EXAMINATION_DATA[i].equals("体质指数")) {

            } else {
                b[i] = true;
            }
        }
        return b;
    }

    private static final boolean[] getIsWriteEXAMINATION_DATA() {
        int len = EXAMINATION_DATA.length;
        boolean[] b = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (EXAMINATION_DATA[i].equals("情绪") ||
                    EXAMINATION_DATA[i].equals("色盲") ||
                    EXAMINATION_DATA[i].equals("色弱") ||
                    EXAMINATION_DATA[i].equals("抑郁症") ||
                    EXAMINATION_DATA[i].equals("脉搏度") ||
                    EXAMINATION_DATA[i].equals("步数") ||
                    EXAMINATION_DATA[i].equals("抗压指数") ||
                    EXAMINATION_DATA[i].equals("自闭症") ||
                    EXAMINATION_DATA[i].equals("血液粘度") ||
                    EXAMINATION_DATA[i].equals("脉搏度")) {
            } else {
                b[i] = true;
            }
        }
        return b;
    }
}
