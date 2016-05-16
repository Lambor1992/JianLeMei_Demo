package com.jlm.app.jianlemei_demo.activity.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlm.app.jianlemei_demo.R;

public class SignatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info_signature);
        this.findViewById(R.id.mine_info_signature_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(20, getIntent());
                getSharedPreferences(Mine_userinfoActivity.UserInfo, MODE_PRIVATE).edit().putString(Mine_userinfoActivity.UserInfo_signature, ((EditText) findViewById(R.id.mine_info_signature_edt)).getText().toString()).apply();
                finish();
            }
        });
    }
}
