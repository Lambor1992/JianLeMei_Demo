package com.jlm.app.jianlemei_demo.activity.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.WarmingSharePrefrenceHelper;
import com.jlm.app.jianlemei_demo.widget.AddContactsWindow;
import com.jlm.app.jianlemei_demo.widget.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Home_WarmingActivity extends AppCompatActivity implements AddContactsWindow.RefreshView {

    @Bind(R.id.home_warming_title)
    TitleBar titleBar;
    @Bind(R.id.home_warming_emergency1_name)
    TextView Emergency1Name;
    @Bind(R.id.home_warming_emergency1_phone)
    TextView Emergency1Phone;
    @Bind(R.id.homg_warming_emergency1_layout)
    LinearLayout Emergency1Layout;
    @Bind(R.id.homg_warming_emergency1_add)
    Button Emergency1Add;
    @Bind(R.id.homg_warming_emergency2_add)
    Button Emergency2Add;
    @Bind(R.id.homg_warming_emergency2_name)
    TextView Emergency2Name;
    @Bind(R.id.homg_warming_emergency2_phone)
    TextView Emergency2Phone;
    @Bind(R.id.homg_warming_emergency2_layout)
    LinearLayout Emergency2Layout;
    @Bind(R.id.homg_warming_emergency3_add)
    Button Emergency3Add;
    @Bind(R.id.homg_warming_emergency3_name)
    TextView Emergency3Name;
    @Bind(R.id.homg_warming_emergency3_phone)
    TextView Emergency3Phone;
    @Bind(R.id.homg_warming_emergency3_layout)
    LinearLayout Emergency3Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_warming);
        ButterKnife.bind(this);
        initView();
        ((CheckBox) this.findViewById(R.id.home_warming_swith_btn)).setChecked(new WarmingSharePrefrenceHelper(this).getShare().getBoolean(WarmingSharePrefrenceHelper.isOn, false));
    }

    private void initView() {

        final String emergency1 = new WarmingSharePrefrenceHelper(this).getShare().getString(WarmingSharePrefrenceHelper.Emergency1, "");
        final String emergency2 = new WarmingSharePrefrenceHelper(this).getShare().getString(WarmingSharePrefrenceHelper.Emergency2, "");
        final String emergency3 = new WarmingSharePrefrenceHelper(this).getShare().getString(WarmingSharePrefrenceHelper.Emergency3, "");
        if (!"".equals(emergency1)) {
            Emergency1Add.setVisibility(View.GONE);
            Emergency1Layout.setVisibility(View.VISIBLE);
            Emergency1Name.setText("紧急联系人:" + emergency1.split("&&")[0]);
            Emergency1Phone.setText("电话号码:" + emergency1.split("&&")[1]);
            Emergency1Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout linearLayout = new LinearLayout(Home_WarmingActivity.this);
                    linearLayout.setPadding(10, 10, 10, 10);
                    Button call = new Button(Home_WarmingActivity.this);
                    call.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    call.setText("打电话");
                    call.setBackgroundResource(R.drawable.shap_normal);
                    call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + emergency1.split("&&")[1])));
                        }
                    });
                    linearLayout.addView(call);
                    new AlertDialog.Builder(Home_WarmingActivity.this)
                            .setIcon(R.drawable.icon)
                            .setTitle("能为您做什么?")
                            .setView(linearLayout)
                            .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new WarmingSharePrefrenceHelper(Home_WarmingActivity.this).deleteEmergency1();
                                    dialog.dismiss();
                                    refreshView();
                                }
                            })
                            .setNeutralButton("修改", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new AddContactsWindow(Home_WarmingActivity.this, WarmingSharePrefrenceHelper.Emergency1).showPopupWindow(titleBar);
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
            });
        } else {
            Emergency1Layout.setVisibility(View.GONE);
            Emergency1Add.setVisibility(View.VISIBLE);
        }

        if (!"".equals(emergency2)) {
            Emergency2Add.setVisibility(View.GONE);
            Emergency2Layout.setVisibility(View.VISIBLE);
            Emergency2Name.setText("紧急联系人(短信):" + emergency2.split("&&")[0]);
            Emergency2Phone.setText("电话号码:" + emergency2.split("&&")[1]);
            Emergency2Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout linearLayout = new LinearLayout(Home_WarmingActivity.this);
                    linearLayout.setPadding(10, 10, 10, 10);
                    Button call = new Button(Home_WarmingActivity.this);
                    call.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    call.setText("打电话");
                    call.setBackgroundResource(R.drawable.shap_normal);
                    call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + emergency2.split("&&")[1])));
                        }
                    });
                    linearLayout.addView(call);
                    new AlertDialog.Builder(Home_WarmingActivity.this)
                            .setIcon(R.drawable.icon)
                            .setTitle("能为您做什么?")
                            .setView(linearLayout)
                            .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new WarmingSharePrefrenceHelper(Home_WarmingActivity.this).deleteEmergency2();
                                    dialog.dismiss();
                                    refreshView();
                                }
                            })
                            .setNeutralButton("修改", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new AddContactsWindow(Home_WarmingActivity.this, WarmingSharePrefrenceHelper.Emergency2).showPopupWindow(titleBar);
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
            });
        } else {
            Emergency2Layout.setVisibility(View.GONE);
            Emergency2Add.setVisibility(View.VISIBLE);

        }

        if (!"".equals(emergency3)) {
            Emergency3Add.setVisibility(View.GONE);
            Emergency3Layout.setVisibility(View.VISIBLE);

            Emergency3Name.setText("紧急联系人(短信):" + emergency3.split("&&")[0]);
            Emergency3Phone.setText("电话号码:" + emergency3.split("&&")[1]);
            Emergency3Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout linearLayout = new LinearLayout(Home_WarmingActivity.this);
                    linearLayout.setPadding(10, 10, 10, 10);
                    Button call = new Button(Home_WarmingActivity.this);
                    call.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    call.setText("打电话");
                    call.setBackgroundResource(R.drawable.shap_normal);
                    call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + emergency3.split("&&")[1])));
                        }
                    });
                    linearLayout.addView(call);
                    new AlertDialog.Builder(Home_WarmingActivity.this)
                            .setIcon(R.drawable.icon)
                            .setTitle("能为您做什么?")
                            .setView(linearLayout)
                            .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new WarmingSharePrefrenceHelper(Home_WarmingActivity.this).deleteEmergency3();
                                    dialog.dismiss();
                                    refreshView();
                                }
                            })
                            .setNeutralButton("修改", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new AddContactsWindow(Home_WarmingActivity.this, WarmingSharePrefrenceHelper.Emergency3).showPopupWindow(titleBar);
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
            });
        } else {
            Emergency3Layout.setVisibility(View.GONE);
            Emergency3Add.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.homg_warming_emergency1_add, R.id.homg_warming_emergency2_add, R.id.homg_warming_emergency3_add, R.id.home_warming_swith_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_warming_swith_btn:
                new WarmingSharePrefrenceHelper(this).changeWarmingState();
                break;
            case R.id.homg_warming_emergency1_add:
                new AddContactsWindow(this, WarmingSharePrefrenceHelper.Emergency1).showPopupWindow(titleBar);
                break;
            case R.id.homg_warming_emergency2_add:
                new AddContactsWindow(this, WarmingSharePrefrenceHelper.Emergency2).showPopupWindow(titleBar);
                break;
            case R.id.homg_warming_emergency3_add:
                new AddContactsWindow(this, WarmingSharePrefrenceHelper.Emergency3).showPopupWindow(titleBar);
                break;
        }
    }

    @Override
    public void refreshView() {
        initView();
    }
}
