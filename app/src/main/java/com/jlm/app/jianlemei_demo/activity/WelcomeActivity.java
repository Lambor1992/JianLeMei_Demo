package com.jlm.app.jianlemei_demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.fragment.guide.SampleSlide;
import com.jlm.app.jianlemei_demo.widget.appintro.AppIntro;

public class WelcomeActivity extends AppIntro {

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro4));
        setDepthAnimation();
        final Intent intent = new Intent(this, GuideActivity.class);
        if (getSharedPreferences("isGuide", Context.MODE_PRIVATE).getBoolean("is", false)) {
            startActivity(intent);
            finish();
        } else {
            getSharedPreferences("isGuide", Context.MODE_PRIVATE).edit().putBoolean("is", true).apply();
        }
    }

    private void loadMainActivity() {
        Intent intent = new Intent().setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {

    }
}
