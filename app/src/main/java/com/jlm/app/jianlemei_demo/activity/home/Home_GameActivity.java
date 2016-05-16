package com.jlm.app.jianlemei_demo.activity.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.game.wuziqi.Game_WuziqiAcitivity;

public class Home_GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_game);
    }

    public void wuziqi(View view) {
        startActivity(new Intent(this, Game_WuziqiAcitivity.class));
    }
}
