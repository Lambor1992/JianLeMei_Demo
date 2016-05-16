package com.jlm.app.jianlemei_demo.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Mine_inviteActivity extends AppCompatActivity {

    @Bind(R.id.mine_invite_people)
    TextView InvitePeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_invite);
        ButterKnife.bind(this);
        InvitePeople.setText(getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).getString(Mine_userinfoActivity.UserInfo_invitepeople, ""));
    }
}
