package com.jlm.app.jianlemei_demo.activity.mine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.utils.ImageUtils;
import com.jlm.app.jianlemei_demo.utils.ToolUtil;
import com.jlm.app.jianlemei_demo.utils.VolleySingleton;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jlm.app.jianlemei_demo.utils.ImageUtils.getAbsoluteImagePath;

public class Mine_userinfoActivity extends AppCompatActivity {


    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    @Bind(R.id.mine_info_signature)
    TextView Signature;
    @Bind(R.id.mine_info_head_click)
    ImageView HeadClick;
    @Bind(R.id.mine_info_username_tv)
    TextView UserTelTv;
    @Bind(R.id.mine_info_username_click)
    RelativeLayout UsernameClick;
    @Bind(R.id.mine_info_signature_tv)
    TextView SignatureTv;
    @Bind(R.id.mine_info_realname_tv)
    TextView RealnameTv;
    @Bind(R.id.mine_info_realname_click)
    RelativeLayout ealnameClick;
    @Bind(R.id.mine_info_sex_layout)
    RelativeLayout SexLayout;
    @Bind(R.id.mine_info_sex)
    TextView SexTv;
    @Bind(R.id.mine_info_brithday_click)
    TextView BirthdayTv;
    @Bind(R.id.mine_info_address_tv)
    TextView AddressTv;
    private String[] items = new String[]{"选择本地图片", "拍照"};
    private static final String IMAGE_FILE_NAME = "fg_mine_icon.jpg";
    private static final File ICON_PIC_FILE = new File(Environment.getDownloadCacheDirectory(),
            IMAGE_FILE_NAME);
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_OK_CODE = 2;
    private static final Uri ICON_PIC = Uri.fromFile(ICON_PIC_FILE);
    private Handler mHanlder = new Handler();
    private int mYear;
    private int mMonth;
    private int mDay;
    public static final String UserInfo = "UserInfo";
    public static final String UserInfo_username = "UserInfo_username";
    public static final String UserInfo_signature = "UserInfo_signature";
    public static final String UserInfo_realname = "UserInfo_realname";
    public static final String UserInfo_sex = "UserInfo_sex";
    public static final String UserInfo_birthday = "UserInfo_birthday";
    public static final String UserInfo_password = "UserInfo_password";
    public static final String UserInfo_id = "UserInfo_id";
    public static final String UserInfo_tel = "UserInfo_tel";
    public static final String UserInfo_identifycode = "UserInfo_identifycode";
    public static final String UserInfo_invitecode = "UserInfo_invitecode";
    public static final String UserInfo_invitepeople = "UserInfo_invitepeople";
    public static final String UserInfo_age = "UserInfo_age";
    public static final String UserInfo_province = "UserInfo_province";
    public static final String UserInfo_city = "UserInfo_city";
    public static final String UserInfo_county = "UserInfo_county";
    public static final String UserInfo_addr = "UserInfo_addr";
    public static final String UserInfo_photo = "UserInfo_photo";
    public static final String UserInfo_bg = "UserInfo_bg";
    public static final String UserInfo_remarks = "UserInfo_remarks";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_userinfo);
        ButterKnife.bind(this);
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        initView();
        mLocationClient.start();
    }

    public void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserInfo, Context.MODE_PRIVATE);
        UserTelTv.setText(sharedPreferences.getString(UserInfo_tel, ""));

        String signature = sharedPreferences.getString(UserInfo_signature, "您可以设置您的个性签名");
        SignatureTv.setText(signature.equals("") ? "您可以设置您的个性签名" : signature);

        RealnameTv.setText(sharedPreferences.getString(UserInfo_realname, ""));
        SexTv.setText(sharedPreferences.getString(UserInfo_sex, "男"));
        BirthdayTv.setText(sharedPreferences.getString(UserInfo_birthday, ""));
        if (BirthdayTv.getText().toString().equals("")) {
            String idcard = sharedPreferences.getString(UserInfo_identifycode, "");
            if (!idcard.equals("")) {
                try {
                    String s = idcard.substring(6, 14);
                    BirthdayTv.setText(s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 8));
                } catch (IndexOutOfBoundsException ignored) {

                }
            }
        }
        if (!sharedPreferences.getString(UserInfo_photo, "").equals("")) {
            VolleySingleton.getVolleySingleton(this).getImageLoader().get(sharedPreferences.getString(UserInfo_photo, ""), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    if (imageContainer.getBitmap() != null) {
                        HeadClick.setImageBitmap(ImageUtils.getRoundedCornerBitmap(imageContainer.getBitmap(), ToolUtil.dip2px(Mine_userinfoActivity.this, 40)));
                    }
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    HeadClick.postInvalidate();
                }
            });
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @OnClick({R.id.mine_info_head_click, R.id.mine_info_username_tv, R.id.mine_info_username_click, R.id.mine_info_signature, R.id.mine_info_signature_tv, R.id.mine_info_realname_tv, R.id.mine_info_realname_click, R.id.mine_info_sex, R.id.mine_info_brithday_click, R.id.mine_info_address_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_info_head_click:
                new AlertDialog.Builder(this)
                        .setTitle("设置头像")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Intent intent2 = new Intent();
                                        intent2.setType("image/*"); // 设置文件类型
                                        intent2.setAction(Intent.ACTION_OPEN_DOCUMENT);
                                        startActivityForResult(intent2, IMAGE_REQUEST_CODE);
                                        break;
                                    case 1:
                                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        if (hasSdcard())
                                            intent1.putExtra(MediaStore.EXTRA_OUTPUT, ICON_PIC);
                                        startActivityForResult(intent1, CAMERA_REQUEST_CODE);
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                break;
            case R.id.mine_info_username_tv:
                break;
            case R.id.mine_info_username_click:
                break;
            case R.id.mine_info_signature:
                startActivityForResult(new Intent(this, SignatureActivity.class), 10);
                break;
            case R.id.mine_info_signature_tv:
                startActivityForResult(new Intent(this, SignatureActivity.class), 10);
                break;
            case R.id.mine_info_realname_tv:
                break;
            case R.id.mine_info_realname_click:
                break;
            case R.id.mine_info_sex:
                new AlertDialog.Builder(this).setIcon(R.drawable.icon).setTitle("选择性别")
                        .setItems(new String[]{"男", "女"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        SexTv.setText("男");
                                        getSharedPreferences(UserInfo, Context.MODE_PRIVATE).edit().putString(UserInfo_sex, "男").apply();
                                        break;
                                    case 1:
                                        SexTv.setText("女");
                                        getSharedPreferences(UserInfo, Context.MODE_PRIVATE).edit().putString(UserInfo_sex, "女").apply();
                                        break;
                                }
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                break;
            case R.id.mine_info_brithday_click:
                startActivityForResult(new Intent(this, DatechooseActivity.class), 100);
                break;
            case R.id.mine_info_address_tv:
                break;
        }
    }


    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            mHanlder.post(new Runnable() {
                @Override
                public void run() {
                    AddressTv.setText(location.getAddrStr());
                }
            });
        }
    }

    public void saveFilish() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 10:
                if (resultCode != 20) return;
                String signature = getSharedPreferences(UserInfo, Context.MODE_PRIVATE).getString(UserInfo_signature, "您可以设置您的个性签名");
                SignatureTv.setText(signature.equals("您可以设置您的个性签名") ? "" : signature);
                break;
            case 100:
                if (resultCode != 200) return;
                BirthdayTv.setText(getSharedPreferences(UserInfo, Context.MODE_PRIVATE).getString(UserInfo_birthday, "2016-4-15"));
                break;
            case IMAGE_REQUEST_CODE:
                if (null == data.getData()) return;
                Intent intent1 = new Intent();
                intent1.setAction("com.android.camera.action.CROP");
                intent1.setDataAndType(Uri.fromFile(new File(getAbsoluteImagePath(this, data.getData()))), "image/*");// mUri是已经选择的图片Uri
                intent1.putExtra("crop", "true");
                intent1.putExtra("aspectX", 1);// 裁剪框比例
                intent1.putExtra("aspectY", 1);
                intent1.putExtra("outputX", 150);// 输出图片大小
                intent1.putExtra("outputY", 150);
                intent1.putExtra("return-data", true);
                this.startActivityForResult(intent1, RESULT_OK_CODE);
                break;
            case CAMERA_REQUEST_CODE:
                Intent intent2 = new Intent();
                intent2.setAction("com.android.camera.action.CROP");
                intent2.setDataAndType(ICON_PIC, "image/*");// mUri是已经选择的图片Uri
                intent2.putExtra("crop", "true");
                intent2.putExtra("aspectX", 1);// 裁剪框比例
                intent2.putExtra("aspectY", 1);
                intent2.putExtra("outputX", 150);// 输出图片大小
                intent2.putExtra("outputY", 150);
                intent2.putExtra("return-data", true);
                this.startActivityForResult(intent2, RESULT_OK_CODE);
                break;
            case RESULT_OK_CODE:
                Bitmap bitmap = ImageUtils.getRoundedCornerBitmap(data.getExtras().<Bitmap>getParcelable("data"), HeadClick.getWidth() / 2);
                HeadClick.setImageBitmap(bitmap);
                break;
        }
    }

    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}

