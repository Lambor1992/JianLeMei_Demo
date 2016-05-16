package com.jlm.app.jianlemei_demo.activity.home.mall;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;
import com.jlm.app.jianlemei_demo.widget.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MallDetailActivity extends Activity {

    @Bind(R.id.mall_detail_title)
    TitleBar mallDetailTitle;
    @Bind(R.id.mall_detail_webview)
    WebView mallDetailWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_detail);
        ButterKnife.bind(this);
        WebSettings webSettings = mallDetailWebview.getSettings();
        int screenDensity = getResources().getDisplayMetrics().densityDpi ;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM ;
        switch (screenDensity){
            case DisplayMetrics.DENSITY_LOW :
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break ;
        }
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
      /*  webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true);
       webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);*/

        String goodsAddress = getIntent().getStringExtra("goods");
        VolleySingleton.getVolleySingleton(this).getRequestQueue().add(new StringRequest(goodsAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    mallDetailTitle.setTitleName(jsonObject.optString("name", ""));
                    mallDetailWebview.loadUrl(jsonObject.optString("goodhtml"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }
}
