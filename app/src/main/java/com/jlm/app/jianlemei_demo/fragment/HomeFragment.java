package com.jlm.app.jianlemei_demo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.home.Home_AdActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_ExamCenterActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_GameActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_ConsultationActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_HouseKeepingActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_SOSActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_SelfExamActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_VideoActivity;
import com.jlm.app.jianlemei_demo.activity.home.Home_WarmingActivity;
import com.jlm.app.jianlemei_demo.activity.home.mall.MallActivity;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;
import com.jlm.app.jianlemei_demo.widget.AutoScrollViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {
    private static HomeFragment fragment;
    @Bind(R.id.fg_home_viewpager)
    AutoScrollViewPager Viewpager;
    @Bind(R.id.fg_home_radiobutton1)
    RadioButton fgHomeRadiobutton1;
    @Bind(R.id.fg_home_radiobutton2)
    RadioButton fgHomeRadiobutton2;
    @Bind(R.id.fg_home_radiobutton3)
    RadioButton fgHomeRadiobutton3;
    @Bind(R.id.fg_home_radiobutton4)
    RadioButton fgHomeRadiobutton4;
    @Bind(R.id.fg_home_radiobutton5)
    RadioButton fgHomeRadiobutton5;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        if (fragment == null) return new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        Viewpager.startAutoScroll();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getActivity());
        Viewpager.setAdapter(adapter);

        Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        fgHomeRadiobutton1.setChecked(true);
                        break;
                    case 1:
                        fgHomeRadiobutton2.setChecked(true);
                        break;
                    case 2:
                        fgHomeRadiobutton3.setChecked(true);
                        break;
                    case 3:
                        fgHomeRadiobutton4.setChecked(true);
                        break;
                    case 4:
                        fgHomeRadiobutton5.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Viewpager.stopAutoScroll();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fg_home_viewpager, R.id.fg_home_radiobutton1, R.id.fg_home_radiobutton5, R.id.fg_home_radiobutton2, R.id.fg_home_radiobutton3, R.id.fg_home_radiobutton4, R.id.fg_home_radiogroup, R.id.fg_home_framelayout, R.id.fg_home_vedio_btn, R.id.fg_home_news_btn, R.id.fg_home_housekeeping_btn, R.id.fg_home_callme_btn, R.id.fg_home_mall_btn, R.id.fg_home_examcenter_btn, R.id.fg_home_reminds_btn, R.id.fg_home_game_btn, R.id.fg_home_selfexam_btn, R.id.fg_home_sos_btn, R.id.fg_home_warming_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fg_home_viewpager:
                startActivity(new Intent(getActivity(), Home_AdActivity.class).putExtra("ADSURL", ""));
                break;
            case R.id.fg_home_radiobutton1:
                Viewpager.setCurrentItem(0);
                break;
            case R.id.fg_home_radiobutton2:
                Viewpager.setCurrentItem(1);
                break;
            case R.id.fg_home_radiobutton3:
                Viewpager.setCurrentItem(2);
                break;
            case R.id.fg_home_radiobutton4:
                Viewpager.setCurrentItem(3);
                break;
            case R.id.fg_home_radiobutton5:
                Viewpager.setCurrentItem(4);
                break;
            case R.id.fg_home_radiogroup:
                break;
            case R.id.fg_home_framelayout:
                break;
            case R.id.fg_home_vedio_btn:
                startActivity(new Intent(getActivity(), Home_VideoActivity.class));
                break;
            case R.id.fg_home_news_btn:
                startActivity(new Intent(getActivity(), Home_ConsultationActivity.class));
                break;
            case R.id.fg_home_housekeeping_btn:
                startActivity(new Intent(getActivity(), Home_HouseKeepingActivity.class));
                break;
            case R.id.fg_home_callme_btn:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + HttpAdress.Tel));
                startActivity(intent);
                break;
            case R.id.fg_home_mall_btn:
                startActivity(new Intent(getActivity(), MallActivity.class));
                break;
            case R.id.fg_home_examcenter_btn:
                startActivity(new Intent(getActivity(), Home_ExamCenterActivity.class));
                break;
            case R.id.fg_home_reminds_btn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    startActivity(new Intent(AlarmClock.ACTION_SHOW_ALARMS));
                }
                break;
            case R.id.fg_home_game_btn:
                startActivity(new Intent(getActivity(), Home_GameActivity.class));
                break;
            case R.id.fg_home_selfexam_btn:
                startActivity(new Intent(getActivity(), Home_SelfExamActivity.class));
                break;
            case R.id.fg_home_sos_btn:
                startActivity(new Intent(getActivity(), Home_SOSActivity.class));
                break;
            case R.id.fg_home_warming_btn:
                startActivity(new Intent(getActivity(), Home_WarmingActivity.class));
                break;
        }
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        String address = HttpAdress.BASE_ads;
        LayoutInflater inflater;
        ArrayList<ads> list = new ArrayList<>(5);
        VolleySingleton volleySingleton = null;

        public MyViewPagerAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
            this.volleySingleton = VolleySingleton.getVolleySingleton(context);
            VolleySingleton.getVolleySingleton(getActivity()).getRequestQueue().add(new JsonArrayRequest(address, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            list.add(new ads(jsonArray.getJSONObject(i)));
                            notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }));
        }

        @Override
        public int getCount() {
            if (list == null) return 0;
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) throws NullPointerException {
            final View v = inflater.inflate(R.layout.item_fg_home_viewpager, null);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Home_AdActivity.class);
                    intent.putExtra("title", list.get(position).getTitle());
                    intent.putExtra("content", list.get(position).getContent());
                    intent.putExtra("picture", list.get(position).getPicture());
                    startActivity(intent);
                }
            });
            volleySingleton.getImageLoader().get(list.get(position).getPicture(), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) throws NullPointerException {
                    ((ImageView) v.findViewById(R.id.item_fg_home_pager_imgv)).setImageBitmap(imageContainer.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            ((TextView) v.findViewById(R.id.item_fg_home_pager_titletxt)).setVisibility(View.GONE);
            container.addView(v);
            return v;
        }

        class ads {
            String title;
            String content;
            String picture;

            ads(JSONObject json) {

                this.title = json.optString("title", "");
                this.content = json.optString("content", "");
                this.picture = json.optString("picture", "");

            }

            public String getTitle() {
                return title;
            }

            public String getContent() {
                return content;
            }

            public String getPicture() {
                return picture;
            }

            @Override
            public String toString() {
                return title + content + picture;
            }
        }
    }
}


