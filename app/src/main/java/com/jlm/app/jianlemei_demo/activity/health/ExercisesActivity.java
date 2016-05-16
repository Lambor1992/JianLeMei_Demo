package com.jlm.app.jianlemei_demo.activity.health;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.adapter.FragmentPagerAdapter;
import com.jlm.app.jianlemei_demo.adapter.TabPageIndicatorAdapter;
import com.jlm.app.jianlemei_demo.fragment.health.healthcase.ExerciseFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExercisesActivity extends Activity implements MediaPlayer.OnCompletionListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.exercise_tab)
    TabPageIndicator Tab;
    @Bind(R.id.exercise_viewpage)
    ViewPager mViewpage;
    private MediaPlayer mediaPlayer;
    private static final String EXE1[] = {"步骤1、单膝直立仰面躺下，立起单腿膝盖，此时应保持身体笔直。双臂呈八字形展开，放置于身体两侧。",
            "步骤2、放倒膝盖缓缓向外侧放到直立的膝盖，尽量贴向地面。脚底紧贴住伸直的另一只腿。上半身保持笔直，注意腰部不要晃动。",
            "步骤3、再次立膝将放倒的膝盖恢复直立，再伸直膝盖，使腿呈笔直状。",
            "如此这般，再换腿练习，左右交叉进行，各五次。 "};
    private static final String EXE2[] = {"步骤1、身体平躺下活动腰部，将有助于加强骨盆肌肉群以及大腿肌肉活动量，对改善便秘也很有帮助哟!",
            "步骤2、托起腰部靠肩部和脚掌将身体支撑住，同时慢慢托起腰部。要下意识拉伸大腿内侧，保持这个姿势七秒钟。",
            "步骤3、并拢膝盖在托起腰部的同时，将双膝并拢，坚持七秒。感觉像是把骨盆收紧了一样。然后再慢慢放送腰部。",
            "回到步骤1的姿势。重复练习2～3次"};
    private static final String EXE3[] = {"步骤1、身体平躺通过对上半身的活动，尤其是前臂以及侧腹肌肉的运动，胸大肌也将得到锻炼。因此，还可以达到丰胸和消除肩酸的效果哦!",
            "步骤2、舒张双臂，靠近耳旁双臂展开，移动至头的方向，尽量贴近耳旁。下意识舒展侧腹。",
            "步骤3、一边画弧形，一边放下手臂双臂展开，移动至头的方向，尽量贴近耳旁。下意识舒展侧腹。"};
    private static final String EXE4[] = {"步骤1、身体平躺睡眠是激活新陈代谢和恢复激素平衡的重要时间。因此，为了得到深沉睡眠，我们还要进行最后的整理运动，来舒展和放松我们的身心。",
            "步骤2、摆出投降的姿势双手举起至正上方，然后笔直放置于两耳旁。与此同时，用腹部深呼吸。",
            "步骤3、保持十秒钟双手达到耳旁后，保持腹式呼吸十秒钟哦。胳膊肘儿一定注意不能弯曲。然后将力气瞬间释放。"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        ButterKnife.bind(this);

        ArrayList<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new ExerciseFragment("第一节", R.drawable.weight_2_1, EXE1));
        listFragment.add(new ExerciseFragment("第二节", R.drawable.weight_2_2, EXE2));
        listFragment.add(new ExerciseFragment("第三节", R.drawable.weight_2_3, EXE3));
        listFragment.add(new ExerciseFragment("第四节", R.drawable.weight_2_4, EXE4));
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getFragmentManager(), listFragment, new String[]{"1", "2", "3", "4"});
        mViewpage.setAdapter(adapter);
        Tab.setViewPager(mViewpage);
        mViewpage.addOnPageChangeListener(this);
        initPlayer("protect8_8.mp3");
    }

    public void initPlayer(String Mp3Name) {
        try {
            mediaPlayer.reset();
        } catch (NullPointerException e) {
        } finally {
            try {
                mediaPlayer = new MediaPlayer();
                AssetFileDescriptor FD_1 = getAssets().openFd(Mp3Name);
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
    public void onCompletion(MediaPlayer mp) {
        if (mViewpage.getCurrentItem() == 3) {
            setResult(0, getIntent().putExtra("back", true));
            finish();
        } else {
            mViewpage.setCurrentItem(mViewpage.getCurrentItem() + 1);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        initPlayer("protect8_8.mp3");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
