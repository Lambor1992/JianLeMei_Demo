package com.jlm.app.jianlemei_demo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;

public class Register_powerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_power);
        TextView tv = (TextView)findViewById(R.id.text);
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
        this.findViewById(R.id.register_power_agree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_powerActivity.this, RegsiterActivity.class));
                Toast.makeText(Register_powerActivity.this, "本协议即刻生效，请在使用过程中严格遵守！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
