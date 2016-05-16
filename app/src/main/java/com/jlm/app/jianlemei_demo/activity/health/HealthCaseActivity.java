package com.jlm.app.jianlemei_demo.activity.health;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.SorryActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_ConsultationActivity;
import com.jlm.app.jianlemei_demo.adapter.HealthCaseAdapter;
import com.jlm.app.jianlemei_demo.utils.HealthCase;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HealthCaseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.health_case_view_list)
    ListView healthCaseViewList;
    @Bind(R.id.health_case_improve)
    ImageView mImprove;
    private String[] make;
    private HealthCaseAdapter adapter;
    private boolean[] checked;
    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case);
        ButterKnife.bind(this);
        make = getIntent().getStringArrayExtra(HealthCase.MAKE);
        ImageView imageView = (ImageView) findViewById(R.id.health_case_img);
        if (null == make || make.length == 0) {
            mImprove.setImageResource(R.drawable.health_case_improve_4);
            state = HealthCase.BLOOD2;
            if (imageView != null) {
                imageView.setImageResource(R.drawable.fa5);
            }
            Toast.makeText(this, "暂未接入数据", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (make[0].equals(HealthCase.HEALTH_CASE_C.EYE[0])) {
                state = HealthCase.EYE;
                mImprove.setImageResource(R.drawable.health_case_improve_1);
                if (imageView != null) {
                    imageView.setImageResource(R.drawable.fa1);
                }
            } else if (make[0].equals(HealthCase.HEALTH_CASE_C.EAR[0])) {
                state = HealthCase.EAR;
                mImprove.setImageResource(R.drawable.health_case_improve_2);
                if (imageView != null) {
                    imageView.setImageResource(R.drawable.fa2);
                }
            } else if (make[0].equals(HealthCase.HEALTH_CASE_C.BLOOD[0])) {
                state = HealthCase.BLOOD;
                mImprove.setImageResource(R.drawable.health_case_improve_3);
                if (imageView != null) {
                    imageView.setImageResource(R.drawable.fa3);
                }

            } else if (make[0].equals(HealthCase.HEALTH_CASE_C.WEIGHT[0])) {
                state = HealthCase.WEIGHT;
                mImprove.setImageResource(R.drawable.health_case_improve_5);
                if (imageView != null) {
                    imageView.setImageResource(R.drawable.fa5);
                }

            } else if (make[0].equals(HealthCase.HEALTH_CASE_C.PSYCHOLOGY[0])) {
                state = HealthCase.PSYCHOLOGY;
                mImprove.setImageResource(R.drawable.health_case_improve_6);
                if (imageView != null) {
                    imageView.setImageResource(R.drawable.fa6);
                }

            }
        }
        checked = new boolean[make.length];
        SharedPreferences sharedPreferences = getSharedPreferences("healthcase", Context.MODE_PRIVATE);
        int len = checked.length;
        for (int i = 0; i < len; i++) {
            checked[i] = sharedPreferences.getBoolean("" + make[i], false);
        }
        adapter = new HealthCaseAdapter(make, 0, this);
        healthCaseViewList.setAdapter(adapter);
        adapter.changeData(checked);
        healthCaseViewList.setOnItemClickListener(this);
    }

    @OnClick({R.id.health_case_improve, R.id.health_case_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.health_case_improve:
                startActivity(new Intent(this, HealthFoodActivity.class).putExtra("state", state));
                break;
            case R.id.health_case_data:
                startActivity(new Intent(this, Home_ConsultationActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivityForResult(getIntent(make[position]), position);
    }

    private Intent getIntent(String tag) {
        Intent intent = new Intent();
        if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[0])) {
            intent.setClass(this, EyeProtectionActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[1])) {
            intent.setClass(this, EyeOCAcivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[2])) {
            intent.setClass(this, EyeCMActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[3])) {
            intent.setClass(this, EyeOMAcivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[4])) {
            intent.setClass(this, EyeOMAcivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[5])) {
            intent.setClass(this, EyeOMAcivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[6])) {
            intent.setClass(this, EyeOMAcivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[7])) {
            intent.setClass(this, EyeOCAcivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EYE[8])) {
            intent.setClass(this, EyeTMActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EAR[0])) {
            intent.setClass(this, EarActivity.class).putExtra("content", "双掌心面对耳廓，先顺时针揉动20次后，再逆时针揉动二十次，早晚各做三次，揉动时不宜过猛以双耳充血发红为宜。").putExtra("res", R.drawable.content_icon_normal_ear_1);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EAR[1])) {
            intent.setClass(this, EarActivity.class).putExtra("content", "耳屏亦称为小耳朵。以拇指、食不断挤压，放松耳屏，左右耳屏同时进行，每次捏20~30下，捏时以双耳屏发热为宜。").putExtra("res", R.drawable.content_icon_normal_ear_2);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EAR[2])) {
            intent.setClass(this, EarActivity.class).putExtra("content", "双手掌心面对耳廓，向内耳方向轻轻按下，然后轻轻松手，反复进行，刚开始每次3~5分钟，以后可增加到5~10分钟，早晚各两次。").putExtra("res", R.drawable.content_icon_normal_ear_3);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EAR[3])) {
            intent.setClass(this, EarActivity.class).putExtra("content", "食指轻轻插入外耳孔，来回转动各20次，用力要均匀，速度不宜过快，以防损伤耳内皮肤。不要双耳同时进行，应先左后右交替进行。").putExtra("res", R.drawable.content_icon_normal_ear_4);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.EAR[4])) {
            intent.setClass(this, EarActivity.class).putExtra("content", "用右手从头上拉左耳廓上部二十次，再用左手拉右耳廓上部二十次。").putExtra("res", R.drawable.content_icon_normal_ear_5);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.BLOOD[0])) {
            intent.setClass(this, BloodPress1Activity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.BLOOD[1])) {
            intent.setClass(this, HealthCaseFootActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.BLOOD[2])) {
            intent.setClass(this, BloodPress2Activity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.BLOOD[3])) {
            intent.setClass(this, BloodPressTeaActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.WEIGHT[0])) {
            intent.setClass(this, SorryActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.WEIGHT[1])) {
            intent.setClass(this, ExercisesActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.WEIGHT[2])) {
            intent.setClass(this, HealthCaseFootActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.PSYCHOLOGY[0])) {
            intent.setClass(this, SorryActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.PSYCHOLOGY[1])) {
            intent.setClass(this, SorryActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.PSYCHOLOGY[2])) {
            intent.setClass(this, SorryActivity.class);
        } else if (tag.equals(HealthCase.HEALTH_CASE_C.PSYCHOLOGY[3])) {
            intent.setClass(this, HealthCaseFootActivity.class);
        }
        return intent.putExtra("health", tag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            data.getStringExtra("health");
        } catch (NullPointerException e) {
            return;
        }
        int count = 0;
        String key = "";
        Boolean tag = data.getBooleanExtra("back", false);
        checked[requestCode] = tag;
        adapter.changeData(checked);
        SharedPreferences.Editor editor = getSharedPreferences("healthcase", Context.MODE_PRIVATE).edit();
        int len = checked.length;
        for (int i = 0; i < len; i++) {
            editor.putBoolean("" + make[i], checked[i]);
            if (checked[i]) {
                editor.putBoolean("" + make[i], true);
                count++;
            }
        }
        if (make[0].equals(HealthCase.HEALTH_CASE_C.EYE[0])) {
            key = "eye";
        } else if (make[0].equals(HealthCase.HEALTH_CASE_C.EAR[0])) {
            key = "ear";
        } else if (make[0].equals(HealthCase.HEALTH_CASE_C.BLOOD[0])) {
            key = "blood";
        } else if (make[0].equals(HealthCase.HEALTH_CASE_C.WEIGHT[0])) {
            key = "weight";
        } else if (make[0].equals(HealthCase.HEALTH_CASE_C.PSYCHOLOGY[0])) {
            key = "psychology";
        }
        editor.putInt(key, count).apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
