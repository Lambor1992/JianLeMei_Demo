package com.jlm.app.jianlemei_demo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mUserView;
    private EditText mPasswordView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserView = (AutoCompleteTextView) findViewById(R.id.login_user_name);
        mPasswordView = (EditText) findViewById(R.id.password);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("tel") != null && bundle.getString("password") != null) {
                mUserView.setText(bundle.getString("tel"));
                mPasswordView.setText(bundle.getString("password"));
            }
        }
        SharedPreferences sharedPreferences = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("is", false)) {
            startActivityForResult(new Intent(LoginActivity.this, MainActivity.class), 0);
            finish();
        }
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        if (mEmailSignInButton != null) {
            mEmailSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String username = mUserView.getText().toString();
                    final String password = mPasswordView.getText().toString();
                    if (!RegsiterActivity.isPhone(username)) {
                        Log.e("Test", username);
                        Toast.makeText(LoginActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!isPasswordValid(password)) {
                        Toast.makeText(LoginActivity.this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                        return;
                    } else new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                String address = HttpAdress.HTTP_loginAction_login
                                        + "?username="
                                        + URLEncoder.encode(username, "UTF-8")
                                        + "&&password="
                                        + password;
                                VolleySingleton.getVolleySingleton(LoginActivity.this)
                                        .getRequestQueue()
                                        .add(new StringRequest(address,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String s) {
                                                        try {
                                                            JSONObject jsonObject = new JSONObject(s);
                                                            boolean flag = jsonObject.getBoolean("flag");
                                                            if (flag) {
                                                                getSharedPreferences("isLogin", Context.MODE_PRIVATE).edit().putBoolean("is", flag).apply();
                                                                getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).edit().putString(Mine_userinfoActivity.UserInfo_username, jsonObject.optString("username")).apply();
                                                                getSharedPreferences(Mine_userinfoActivity.UserInfo, Context.MODE_PRIVATE).edit().putString(Mine_userinfoActivity.UserInfo_password, jsonObject.optString("username")).apply();
                                                            } else {
                                                                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                                                getSharedPreferences("isLogin", Context.MODE_PRIVATE).edit().putBoolean("is", flag).apply();
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError volleyError) {
                                                        Toast.makeText(LoginActivity.this, "请检查网络设置", Toast.LENGTH_SHORT).show();
                                                    }
                                                }));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            boolean isLogin = getSharedPreferences("isLogin", Context.MODE_PRIVATE).getBoolean("is", false);
                            if (isLogin) {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        startActivityForResult(new Intent(LoginActivity.this, MainActivity.class), 0);
                                        finish();
                                    }
                                }, 1000);
                            }
                        }
                    }.execute();
                }
            });
        }


        findViewById(R.id.login_register).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Register_powerActivity.class));
            }
        });

        findViewById(R.id.login_forget_password).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "忘记密码啦，打电话问问我们的客服热线吧", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != data) {
            finish();
        }
    }
}

