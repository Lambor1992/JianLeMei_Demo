package com.jlm.app.jianlemei_demo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.widget.numberprogressbar.NumberProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.jlm.app.jianlemei_demo.utils.AppUpdatexUtils.java
 * @author: zwg
 * @date: 2016-05-03 10:42
 */
public class AppUpdatexUtils {
    private static Context mContext;
    private static File fileSaveDir;
    private static final String upDatePath = Environment.getExternalStorageDirectory() + "/" + "jlm/update/";

    {
        UpDateMemoryManager.initUpdateMemory(mContext);
    }

    public static void intiAppUpdatexUtils(final Context context) {
        mContext = context;
        VolleySingleton.getVolleySingleton(context)
                .getRequestQueue()
                .add(new StringRequest(HttpAdress.BASE_updateApp,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                try {
                                    final JSONObject jsonObject = new JSONObject(s);
                                    //检查服务器版本和当前版本,如果服务器版本号大于当前版本号，则检查wifi环境,wifi能用则继续检查sdcard是否能使用，若能则开始下载
                                    if (jsonObject.getInt("versionCode") != getVerCode(mContext)) {
                                        if (isWifiConnected(context)) {
                                            if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                                                new AsyncTask<Void, Integer, Boolean>() {
                                                    @Override
                                                    protected Boolean doInBackground(Void... params) {
                                                        try {
                                                            return downloadToSDcard(jsonObject.getString("updateurl"), jsonObject.getString("appName"), jsonObject.getString("versionName"));
                                                        } catch (IOException | JSONException e) {
                                                            return false;
                                                        }
                                                    }

                                                    @Override
                                                    protected void onProgressUpdate(Integer... values) {
                                                    }

                                                    @Override
                                                    protected void onPostExecute(Boolean aBoolean) {
                                                        if (aBoolean) {
                                                            new AlertDialog.Builder(mContext)
                                                                    .setIcon(R.drawable.icon)
                                                                    .setMessage("发现更新包,已经在wifi开着的时候偷偷下下来了，要不要更新呢?")
                                                                    .setTitle("提示")
                                                                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            dialog.dismiss();
                                                                        }
                                                                    })
                                                                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            context.startActivity(new Intent().setAction(Intent.ACTION_VIEW).setDataAndType(Uri.fromFile(fileSaveDir), "application/vnd.android.package-archive"));
                                                                        }
                                                                    })
                                                                    .create()
                                                                    .show();
                                                        }
                                                    }
                                                }.execute();
                                            }
                                        }
                                    } else {
                                        UpDateMemoryManager.initUpdateMemory(mContext);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }));

    }

    public static void intiAppUpdate2Utils(final Context context) {
        mContext = context;
        VolleySingleton.getVolleySingleton(context)
                .getRequestQueue()
                .add(new StringRequest(HttpAdress.BASE_updateApp,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                try {
                                    final JSONObject jsonObject = new JSONObject(s);
                                    //检查服务器版本和当前版本,如果服务器版本号大于当前版本号，则检查wifi环境,wifi能用则继续检查sdcard是否能使用，若能则开始下载
                                    if (jsonObject.getInt("versionCode") != getVerCode(mContext)) {
                                        new AlertDialog.Builder(context).setIcon(R.drawable.icon)
                                                .setTitle("发现新版本")
                                                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .setNegativeButton("下载", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (isWifiConnected(context)) {
                                                            dialog.dismiss();
                                                            new AppUpdatexUtils.downLoadToSDCard(jsonObject, context).execute();
                                                        } else {
                                                            new AlertDialog.Builder(context).setTitle("提示")
                                                                    .setIcon(R.drawable.icon)
                                                                    .setMessage("当前网络不是wifi,是否继续下载")
                                                                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            dialog.dismiss();
                                                                        }
                                                                    })
                                                                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            dialog.dismiss();
                                                                            new AppUpdatexUtils.downLoadToSDCard(jsonObject, context).execute();
                                                                        }
                                                                    })
                                                                    .create()
                                                                    .show();
                                                        }
                                                    }
                                                })
                                                .create()
                                                .show();
                                    } else {
                                        UpDateMemoryManager.initUpdateMemory(mContext);
                                        new AlertDialog.Builder(context).setMessage("当前已经是最新版本")
                                                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .create().show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(context, "网络数据出错", Toast.LENGTH_SHORT).show();
                            }
                        }));

    }

    /**
     * &#x83b7;&#x53d6;&#x7248;&#x672c;&#x53f7;
     *
     * @param context
     * @return
     */
    private static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verCode;
    }

    /**
     * &#x83b7;&#x53d6;&#x7248;&#x672c;&#x540d;&#x79f0;
     *
     * @param context
     * @return
     */
    private static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verName;
    }

    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean downloadToSDcard(String downloadUrl, String appName, String versionName) throws IOException {
        URL url = new URL(downloadUrl);
        HttpURLConnection conn;
        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.connect(); // 连接

        fileSaveDir = new File(upDatePath + appName + versionName + ".apk");
        if (fileSaveDir.isDirectory()) {
            fileSaveDir.delete();
        }
        if (fileSaveDir.isFile()) {
            return true;
        }
        if (!fileSaveDir.getParentFile().exists()) { // 判断目录是否存在，如果不存在，创建目录
            fileSaveDir.getParentFile().mkdirs();
        }
        InputStream inputStream;
        inputStream = conn.getInputStream();
        FileOutputStream outputStream;
        outputStream = new FileOutputStream(fileSaveDir);
        byte[] Byte = new byte[4 * 1024];
        int len;
        try {
            while ((len = inputStream.read(Byte)) != -1) {
                outputStream.write(Byte, 0, len);
                outputStream.flush();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static class downLoadToSDCard extends AsyncTask<Void, Integer, Boolean> {
        JSONObject jsonObject;
        Context context;
        AlertDialog dialog;
        NumberProgressBar numberProgressBar;

        public downLoadToSDCard(JSONObject jsonObject, Context context) {
            this.jsonObject = jsonObject;
            this.context = context;
            numberProgressBar = new NumberProgressBar(context);
            numberProgressBar.setPadding(5, 10, 5, 5);
            dialog = new AlertDialog.Builder(this.context)
                    .setIcon(R.drawable.icon)
                    .setTitle("下载中")
                    .setNegativeButton("后台下载", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setView(numberProgressBar).create();
            dialog.show();
        }

        protected Boolean doInBackground(Void... params) {
            URL url = null;
            String appName = null;
            String versionName = null;
            try {
                url = new URL(jsonObject.getString("updateurl"));
                appName = jsonObject.getString("appName");
                versionName = jsonObject.getString("versionName");
            } catch (MalformedURLException | JSONException e) {
                Toast.makeText(context, "网络数据出错", Toast.LENGTH_SHORT).show();
            }
            HttpURLConnection conn;
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setRequestMethod("GET");
                conn.connect(); // 连接
                int total = conn.getContentLength();
                fileSaveDir = new File(upDatePath + appName + versionName + ".apk");
                if (fileSaveDir.isDirectory()) {
                    fileSaveDir.delete();
                }
                if (fileSaveDir.isFile()) {
                    if (fileSaveDir.length() == total) {
                        return true;
                    } else {
                        fileSaveDir.delete();
                    }
                }
                if (!fileSaveDir.getParentFile().exists()) { // 判断目录是否存在，如果不存在，创建目录
                    fileSaveDir.getParentFile().mkdirs();
                }
                InputStream inputStream;
                inputStream = conn.getInputStream();
                FileOutputStream outputStream;
                fileSaveDir = new File(upDatePath + appName + versionName + ".apk" + ".load");
                outputStream = new FileOutputStream(fileSaveDir);
                byte[] Byte = new byte[4 * 1024];
                int len;
                int count = 0;
                try {
                    while ((len = inputStream.read(Byte)) != -1) {
                        outputStream.write(Byte, 0, len);
                        count += len;
                        publishProgress((int) ((count * 100) / total));
                        outputStream.flush();
                    }
                } catch (IOException e) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileSaveDir.renameTo(new File(upDatePath + appName + versionName + ".apk"));
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            numberProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                dialog.dismiss();
                new AlertDialog.Builder(mContext)
                        .setIcon(R.drawable.icon)
                        .setMessage("更新包已经下载好了，是否更新?")
                        .setTitle("提示")
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                context.startActivity(new Intent().setAction(Intent.ACTION_VIEW).setDataAndType(Uri.fromFile(fileSaveDir), "application/vnd.android.package-archive"));
                            }
                        })
                        .create()
                        .show();
            } else {
                Toast.makeText(context, "网络似乎有问题，请稍后再试", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class UpDateMemoryManager {
        public static void initUpdateMemory(final Context context) {
            VolleySingleton.getVolleySingleton(context).getRequestQueue().add(new StringRequest(HttpAdress.BASE_updateApp, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        if (jsonObject.getInt("versionCode") == getVerCode(context)) {
                            File file = new File(upDatePath);
                            if (file.exists()) {
                                if (file.isDirectory()) {
                                    for (File f : file.listFiles()) {
                                        f.delete();
                                    }
                                }
                            }
                        }
                    } catch (JSONException e) {
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }));
        }
    }
}
