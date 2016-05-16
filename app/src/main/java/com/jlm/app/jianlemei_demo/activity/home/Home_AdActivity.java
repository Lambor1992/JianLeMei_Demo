package com.jlm.app.jianlemei_demo.activity.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;
import com.jlm.app.jianlemei_demo.widget.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Home_AdActivity extends AppCompatActivity {

    @Bind(R.id.home_ad_title)
    TitleBar AdTitle;
    @Bind(R.id.home_ad_img)
    ImageView AdImg;
    @Bind(R.id.home_ad_content)
    TextView AdContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ad);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String picture = intent.getStringExtra("picture");
        AdTitle.setTitleName(title);
        AdContent.setText(content);
        VolleySingleton.getVolleySingleton(this).addToRequestQueue(new ImageRequest(picture, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                AdImg.setImageBitmap(bitmap);
                AdImg.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        AdImg.setLayoutParams(new LinearLayout.LayoutParams(AdImg.getWidth(), (int) (AdImg.getWidth() * 0.6)));
                    }
                });
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }
}
