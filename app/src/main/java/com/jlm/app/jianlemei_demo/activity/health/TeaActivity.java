package com.jlm.app.jianlemei_demo.activity.health;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.User.Tea;
import com.jlm.app.jianlemei_demo.widget.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeaActivity extends Activity {

    @Bind(R.id.health_case_tea_titlebar)
    TitleBar Titlebar;
    @Bind(R.id.health_case_tea_img)
    ImageView TeaImg;
    @Bind(R.id.health_case_tea_content)
    TextView TeaContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_case_tea);
        ButterKnife.bind(this);
        Tea tea = getIntent().getParcelableExtra("tea");
        Titlebar.setTitleName(tea.getTeaName());
        TeaImg.setImageResource(tea.getIcon());
        TeaContent.setText("    "+tea.getTeaContent());

    }
}
