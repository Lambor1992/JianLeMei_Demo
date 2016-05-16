package com.jlm.app.jianlemei_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.user.UserTools;
import com.jlm.app.jianlemei_demo.utils.HttpAdress;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegsiterActivity extends AppCompatActivity implements View.OnFocusChangeListener {


    @Bind(R.id.register_ok)
    Button Register_Ok;
    @Bind(R.id.register_username)
    EditText Username;
    @Bind(R.id.register_password)
    EditText Password;
    @Bind(R.id.register_re_password)
    EditText RePassword;
    @Bind(R.id.register_phone)
    EditText Phone;
    @Bind(R.id.register_id)
    EditText IdCardNo;
    @Bind(R.id.register_realname)
    EditText RealName;
    @Bind(R.id.register_invitecode)
    EditText InviteCode;
    @Bind(R.id.register_invitename)
    EditText InviteName;
    private VolleySingleton VolleySingleton;
    public static final String HTTP_REGISTER_ACTION = HttpAdress.HTTP_registerAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);
        ButterKnife.bind(this);
        Username.setOnFocusChangeListener(this);
        Password.setOnFocusChangeListener(this);
        RePassword.setOnFocusChangeListener(this);
        Phone.setOnFocusChangeListener(this);
        IdCardNo.setOnFocusChangeListener(this);
        RealName.setOnFocusChangeListener(this);
        InviteCode.setOnFocusChangeListener(this);
        InviteName.setOnFocusChangeListener(this);
        VolleySingleton = com.jlm.app.jianlemei_demo.utils.VolleySingleton.getVolleySingleton(this);

        Register_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhone(Phone.getText().toString()) &&
                        isIDCardNO(IdCardNo.getText().toString()) &&
                        isPePassword(Password.getText().toString(), RePassword.getText().toString())
                        ) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, HTTP_REGISTER_ACTION, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (!jsonObject.optBoolean("flag")) {
                                    Toast.makeText(RegsiterActivity.this, "用户名已存在,注册失败", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (!jsonObject.optBoolean("pan")) {
                                    Toast.makeText(RegsiterActivity.this, "邀请码错误,注册失败", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Toast.makeText(RegsiterActivity.this, jsonObject.optString("msg1") + "\n" + jsonObject.optString("msg2"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegsiterActivity.this, LoginActivity.class).putExtra("tel", Phone.getText().toString()).putExtra("password", Password.getText().toString()));
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<String, String>(1);
                            map.put("json", getLocationJson().toString());
                            return map;
                        }
                    };
                    VolleySingleton.addToRequestQueue(stringRequest);
                }
            }


        });

    }


    public JSONObject getLocationJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(UserTools.USERNAME, Username.getText().toString());
            jsonObject.put(UserTools.PASSWORD, Password.getText().toString());

            jsonObject.put(UserTools.CONFIRMPASSWORD, RePassword.getText().toString());
            jsonObject.put(UserTools.REALNAME, RealName.getText().toString());

            jsonObject.put(UserTools.INVITECODE, InviteCode.getText().toString());
            jsonObject.put(UserTools.INVITEPEOPLE, InviteName.getText().toString());

            jsonObject.put(UserTools.TEL, Phone.getText().toString());
            jsonObject.put(UserTools.IDENTIFYCODE, IdCardNo.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static boolean isPhone(String str) {
        return str.matches("^(13[0-9]|15[01]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$");
    }

    public boolean isPassword(String password) {
        return password.length() > 4;
    }

    public boolean isPePassword(String passwprd, String repassword) {
        return passwprd.equals(repassword);
    }

    public boolean isIDCardNO(String idcardNO) {
        return idcardNO.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            switch (v.getId()) {
//                case R.id.register_username:
//                    if (isPhone(Username.getText().toString()))
//                        Toast.makeText(RegsiterActivity.this, "用户名不能为手机号码", Toast.LENGTH_SHORT).show();
//                    break;
                case R.id.register_password:
                    if (!isPassword(Password.getText().toString()))
                        Toast.makeText(RegsiterActivity.this, "密码太短,请重新输入", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.register_re_password:
                    if (!isPePassword(Password.getText().toString(), RePassword.getText().toString()))
                        Toast.makeText(RegsiterActivity.this, "2次密码不相同，请重新输入", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.register_phone:
                    if (!isPhone(Phone.getText().toString()))
                        Toast.makeText(RegsiterActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.register_id:
                    if (!isIDCardNO(IdCardNo.getText().toString()))
                        Toast.makeText(RegsiterActivity.this, "请输入18位身份证号码", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.register_realname:
                    break;
                case R.id.register_invitecode:
                    break;
                case R.id.register_invitename:
                    break;

            }
        }
    }
}
