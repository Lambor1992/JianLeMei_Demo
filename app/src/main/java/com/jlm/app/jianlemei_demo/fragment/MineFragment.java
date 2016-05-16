package com.jlm.app.jianlemei_demo.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.LoginActivity;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_TaskActivity;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_NotificationActivity;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_feedbackActivity;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_inviteActivity;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_settingActivity;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;
import com.jlm.app.jianlemei_demo.widget.PullScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MineFragment extends Fragment implements PullScrollView.OnTurnListener {
    private static MineFragment fragment;
    @Bind(R.id.background_img)
    ImageView backgroundImg;
    @Bind(R.id.user_post2)
    TextView userPost2;
    @Bind(R.id.user_avatar)
    ImageView userAvatar;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.scroll_view_head)
    RelativeLayout scrollViewHead;
    @Bind(R.id.scroll_view)
    PullScrollView mScrollView;


    public MineFragment() {
    }

    public static MineFragment newInstance() {
        if (fragment == null) return new MineFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        mScrollView.setHeader(userAvatar);
        mScrollView.setOnTurnListener(this);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE);
        userName.setText(sharedPreferences.getString(Mine_userinfoActivity.UserInfo_realname, ""));
        String add1 = sharedPreferences.getString(Mine_userinfoActivity.UserInfo_bg, "");
        String add2 = sharedPreferences.getString(Mine_userinfoActivity.UserInfo_photo, "");
        if (!add1.equals(""))
            VolleySingleton.getVolleySingleton(getActivity()).getImageLoader().get(add1
                    , new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    backgroundImg.setImageBitmap(imageContainer.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    backgroundImg.setImageResource(R.drawable.scrollview_header);
                }
            });

        if (!add2.equals(""))
            VolleySingleton.getVolleySingleton(getActivity()).getImageLoader().get(
                    add2, new ImageLoader.ImageListener() {
                        @Override
                        public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                            userAvatar.setImageBitmap(imageContainer.getBitmap());
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            userAvatar.setImageResource(R.drawable.avatar_default);
                        }
                    }, 50, 50);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onTurn() {
        Log.e("jiaodian", "))))))");

    }

    @OnClick({R.id.background_img, R.id.user_avatar, R.id.fg_mine_userinfo, R.id.fg_mine_task, R.id.fg_mine_notifition, R.id.fg_mine_feedback, R.id.fg_mine_invite, R.id.fg_mine_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fg_mine_userinfo:

                startActivity(new Intent(getActivity(), Mine_userinfoActivity.class));
                break;
            case R.id.fg_mine_task:
                startActivity(new Intent().setClass(getActivity(), Mine_TaskActivity.class));
                break;
            case R.id.fg_mine_notifition:
                startActivity(new Intent().setClass(getActivity(), Mine_NotificationActivity.class));
                break;
            case R.id.fg_mine_feedback:
                startActivity(new Intent().setClass(getActivity(), Mine_feedbackActivity.class));
                break;
            case R.id.fg_mine_invite:
                startActivity(new Intent().setClass(getActivity(), Mine_inviteActivity.class));
                break;
            case R.id.fg_mine_setting:
                startActivityForResult(new Intent(getActivity(), Mine_settingActivity.class), 0);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (data.getBooleanExtra("outLogin", false)) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        }
    }
}
