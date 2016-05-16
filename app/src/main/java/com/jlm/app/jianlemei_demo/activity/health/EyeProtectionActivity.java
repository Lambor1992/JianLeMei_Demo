package com.jlm.app.jianlemei_demo.activity.health;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.adapter.FragmentPagerAdapter;
import com.jlm.app.jianlemei_demo.adapter.TabPageIndicatorAdapter;
import com.jlm.app.jianlemei_demo.fragment.health.healthcase.EyeProtectFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EyeProtectionActivity extends Activity implements MediaPlayer.OnCompletionListener {
    @Bind(R.id.home_video_tab)
    TabPageIndicator mTab;
    @Bind(R.id.home_video_viewpager)
    ViewPager mViewpager;
    private MediaPlayer mediaPlayer;
    MediaPlayer bigin = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_video_layout);
        ButterKnife.bind(this);
        findViewById(R.id.home_video_fram).setVisibility(View.VISIBLE);

        try {
            bigin = new MediaPlayer();
            AssetFileDescriptor FD_1 = getAssets().openFd("eyeprotection_begin.mp3");
            bigin.setDataSource(FD_1.getFileDescriptor(), FD_1.getStartOffset(), FD_1.getLength());
            bigin.prepare();
            bigin.setOnCompletionListener(this);
            bigin.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bigin.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                findViewById(R.id.home_video_fram).setVisibility(View.GONE);
                findViewById(R.id.home_video_title).setVisibility(View.GONE);
                ArrayList<Fragment> list_fragment = new ArrayList<>();
                list_fragment.add(new EyeProtectFragment("揉天应穴", "    以左右大拇指罗纹面接左右眉头下面的上眶角处。其他四指散开弯曲如弓状，支在前额上，按揉面不要大。", R.drawable.health_case_eye_1_1));
                list_fragment.add(new EyeProtectFragment("挤按睛明穴", "    以左手或右手大拇指按鼻根部，先向下按、然后向上挤。", R.drawable.health_case_eye_1_2));
                list_fragment.add(new EyeProtectFragment("按揉四白穴", "    先以左右食指与中指并拢，放在靠近鼻翼两侧，大拇指支撑在下腭骨凹陷处，然后放下中指，在面颊中央按揉。" + "/n" + "注意穴位不需移动，按揉面不要太大。", R.drawable.health_case_eye_1_3));
                list_fragment.add(new EyeProtectFragment("按太阳穴 轮刮眼眶", "    拳起四指，以左右大拇指罗纹面按住太阳穴，以左右食指第二节内侧面轮刮眼眶上下一圈，上侧从眉头开始，到眉梢为止，下面从内眼角起至外眼角止，先上后下，轮刮上下一圈。", R.drawable.health_case_eye_1_4));
                FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getFragmentManager(), list_fragment, new String[]{"1", "2", "3", "4"});
                mViewpager.setAdapter(adapter);
                initPlayer("eyeprotection_1.mp3");
                mTab.setViewPager(mViewpager);
            }
        });

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        initPlayer("eyeprotection_1.mp3");
                        break;
                    case 1:
                        initPlayer("eyeprotection_2.mp3");
                        break;
                    case 2:
                        initPlayer("eyeprotection_3.mp3");
                        break;
                    case 3:
                        initPlayer("eyeprotection_4.mp3");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    public void initPlayer(String name) {
        try {
            mediaPlayer.reset();
        } catch (NullPointerException e) {
        } finally {
            try {
                mediaPlayer = new MediaPlayer();
                AssetFileDescriptor FD_1 = getAssets().openFd(name);
                mediaPlayer.setDataSource(FD_1.getFileDescriptor(), FD_1.getStartOffset(), FD_1.getLength());
                mediaPlayer.prepare();
                mediaPlayer.setOnCompletionListener(this);
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        if (bigin != null) {
            bigin.reset();
            bigin.release();
        }
        super.onDestroy();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mViewpager.getCurrentItem() == 3) {
            setResult(0, getIntent().putExtra("back", true));
            finish();
        } else {
            mViewpager.setCurrentItem(mViewpager.getCurrentItem() + 1);
        }
    }
}
