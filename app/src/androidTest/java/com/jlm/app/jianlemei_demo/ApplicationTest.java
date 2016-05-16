package com.jlm.app.jianlemei_demo;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;


public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {
        super(Application.class);
        jlmTest();


    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    public void jlmTest(){
        new VolleySingleton(getContext()).addToRequestQueue(new StringRequest(HttpAdress.BASE_goodsAction + "1", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("Test", "s"+s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }
}