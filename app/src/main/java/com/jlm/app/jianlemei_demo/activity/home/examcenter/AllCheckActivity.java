package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jlm.app.jianlemei_demo.R;
import com.jlm.app.jianlemei_demo.activity.mine.Mine_userinfoActivity;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;


public class AllCheckActivity extends Activity {

    // 曲线
    private Timer timer = new Timer();
    private TimerTask task;
    private static int gx;
    private static int j;
    private static boolean flags = false;
    TextView txt_check_title;
    private static int heartRate;//心率
    private static int highRate;//高压
    private static float bloodN;//粘度
    private static int bloodY;//yang
    private static int lowRate;//低压
    private String user_id;

    private static double flag = 1;
    private Handler handler;
    private String title = "健乐美图仪";
    private XYSeries series;
    private XYMultipleSeriesDataset mDataset;
    private GraphicalView chart;
    private XYMultipleSeriesRenderer renderer;
    private Context context;
    private int addX = -1;
    private int progress = 0;
    double addY;
    int[] xv = new int[200];
    int[] yv = new int[200];
    int[] hua = new int[]{60, 65, 70, 75, 80, 85, 80, 75, 70, 65, 60, 58, 56,
            55, 56, 58, 60, 65, 70, 75, 75};

    // private static final String TAG = "HeartRateMonitor";
    private static final AtomicBoolean processing = new AtomicBoolean(false);
    private static SurfaceView preview = null;
    private static SurfaceHolder previewHolder = null;
    private static Camera camera = null;
    private static TextView text1 = null;
    private static TextView text2 = null;
    private static PowerManager.WakeLock wakeLock = null;
    private static int averageIndex = 0;
    private static final int averageArraySize = 4;
    private static final int[] averageArray = new int[averageArraySize];

    public static enum TYPE {
        GREEN, RED
    }

    ;

    private static TYPE currentType = TYPE.GREEN;

    public static TYPE getCurrent() {
        return currentType;
    }

    private static int beatsIndex = 0;
    private static final int beatsArraySize = 3;
    private static final int[] beatsArray = new int[beatsArraySize];
    private static double beats = 0;
    private static long startTime = 0;

    private RoundProgressBar mRoundProgressBar1, mRoundProgressBar2,
            mRoundProgressBar3, mRoundProgressBar4, mRoundProgressBar5;
    private static TextView txt_ProgressBar1, txt_ProgressBar2,
            txt_ProgressBar3, txt_ProgressBar4, txt_ProgressBar5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_check);

        user_id = getIntent().getStringExtra("userId");
        Log.e("All_check", user_id);
        initView();
        txt_check_title.setText("挡住摄像头和闪光灯，开始检测");
        // 曲线
        context = getApplicationContext();

        // 这里获得main界面上的布局，下面会把图表画在这个布局里面
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);

        // 这个类用来放置曲线上的所有点，是一个点的集合，根据这些点画出曲线
        series = new XYSeries(title);

        // 创建一个数据集的实例，这个数据集将被用来创建图表
        mDataset = new XYMultipleSeriesDataset();

        // 将点集添加到这个数据集中
        mDataset.addSeries(series);

        // 以下都是曲线的样式和属性等等的设置，renderer相当于一个用来给图表做渲染的句柄
        int color = Color.GREEN;
        PointStyle style = PointStyle.CIRCLE;
        renderer = buildRenderer(color, style, true);

        // 设置好图表的样式
        setChartSettings(renderer, "X", "Y", 0, 300, 55, 95, Color.WHITE,
                Color.WHITE);

        // 生成图表
        chart = ChartFactory.getLineChartView(context, mDataset, renderer);

        // 将图表添加到布局中去
        layout.addView(chart, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));

        // 这里的Handler实例将配合下面的Timer实例，完成定时更新图表的功能
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 刷新图表
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        updateChart();
                        break;
                    default:
                        break;
                }
            }
        };

        task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };

        timer.schedule(task, 1, 40); // 曲线

        preview = (SurfaceView) findViewById(R.id.preview);
        previewHolder = preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm
                .newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");

    }

    private void initView() {
        txt_check_title = (TextView) findViewById(R.id.txt_check_title);
        mRoundProgressBar1 = (RoundProgressBar) findViewById(R.id.progressBar1);
        mRoundProgressBar2 = (RoundProgressBar) findViewById(R.id.progressBar2);
        mRoundProgressBar3 = (RoundProgressBar) findViewById(R.id.progressBar3);
        mRoundProgressBar4 = (RoundProgressBar) findViewById(R.id.progressBar4);
        mRoundProgressBar5 = (RoundProgressBar) findViewById(R.id.progressBar5);

        txt_ProgressBar1 = (TextView) findViewById(R.id.txt_progressBar1);
        txt_ProgressBar2 = (TextView) findViewById(R.id.txt_progressBar2);
        txt_ProgressBar3 = (TextView) findViewById(R.id.txt_progressBar3);
        txt_ProgressBar4 = (TextView) findViewById(R.id.txt_progressBar4);
        txt_ProgressBar5 = (TextView) findViewById(R.id.txt_progressBar5);
    }

    // 曲线
    @Override
    public void onDestroy() {
        // 当结束程序时关掉Timer
        timer.cancel();
        super.onDestroy();
    }


    protected XYMultipleSeriesRenderer buildRenderer(int color,
                                                     PointStyle style, boolean fill) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

        // 设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.RED);
        // r.setPointStyle(null);
        // r.setFillPoints(fill);
        r.setLineWidth(1);
        renderer.addSeriesRenderer(r);
        return renderer;
    }

    protected void setChartSettings(XYMultipleSeriesRenderer renderer,
                                    String xTitle, String yTitle, double xMin, double xMax,
                                    double yMin, double yMax, int axesColor, int labelsColor) {
        // 有关对图表的渲染可参看api文档
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
        renderer.setShowGrid(true);
        renderer.setGridColor(Color.GREEN);
        renderer.setXLabels(40);
        renderer.setYLabels(20);
        renderer.setXTitle("时间");
        renderer.setYTitle("mmHg");
        renderer.setYLabelsAlign(Align.RIGHT);
        renderer.setPointSize((float) 3);
        renderer.setShowLegend(false);
    }

    public void updateProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (flags && progress <= 200) {
                    progress += 1;

                    System.out.println(progress);

                    mRoundProgressBar1.setProgress(progress);
                    mRoundProgressBar2.setProgress(progress);
                    mRoundProgressBar3.setProgress(progress);
                    mRoundProgressBar4.setProgress(progress);
                    mRoundProgressBar5.setProgress(progress);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        if (progress >= 200) {
            txt_check_title.setText("即将完成，请稍等...");
            Intent intent = new Intent();
            intent.setClass(AllCheckActivity.this, CheckFastResultActivity.class);

            intent.putExtra("heartRate", heartRate);
            intent.putExtra("highRate", highRate);
            intent.putExtra("bloodN", bloodN);
            intent.putExtra("bloodY", bloodY);
            intent.putExtra("lowRate", lowRate);




            startActivity(intent);
            finish();
        }
    }

    private void updateChart() {
        if (flag == 1) {
            addY = 10;
        } else {
            flag = 1;
            if (gx < 200) {
                if (hua[20] > 1) {
                    flags = false;
                    Toast.makeText(AllCheckActivity.this, "请用您的手指同时盖住摄像头和闪光灯！",
                            Toast.LENGTH_SHORT).show();
                    hua[20] = 0;
                }
                hua[20]++;
                return;
            } else
                hua[20] = 10;
            j = 0;
            flags = true;
            updateProgress();
            txt_check_title.setText("正在检测，请稍等...");
        }
        if (j < 20) {
            addY = hua[j];
            j++;
        }
        // 移除数据集中旧的点集
        mDataset.removeSeries(series);
        // 判断当前点集中到底有多少点，因为屏幕总共只能容纳100个，所以当点数超过100时，长度永远是100
        int length = series.getItemCount();
        int bz = 0;
        // addX = length;
        if (length > 200) {
            length = 200;
            bz = 1;
        }
        addX = length;
        // 将旧的点集中x和y的数值取出来放入backup中，并且将x的值加1，造成曲线向右平移的效果
        for (int i = 0; i < length; i++) {
            xv[i] = (int) series.getX(i) - bz;
            yv[i] = (int) series.getY(i);
        }
        // 点集先清空，为了做成新的点集而准备
        series.clear();
        mDataset.addSeries(series);
        // 将新产生的点首先加入到点集中，然后在循环体中将坐标变换后的一系列点都重新加入到点集中
        // 这里可以试验一下把顺序颠倒过来是什么效果，即先运行循环体，再添加新产生的点
        series.add(addX, addY);
        for (int k = 0; k < length; k++) {
            series.add(xv[k], yv[k]);
        }
        // 在数据集中添加新的点集
        // mDataset.addSeries(series);
        // 视图更新，没有这一步，曲线不会呈现动态
        // 如果在非UI主线程中，需要调用postInvalidate()，具体参考api
        chart.invalidate();
    } // 曲线

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        camera = Camera.open();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    @SuppressWarnings("deprecation")
    private static PreviewCallback previewCallback = new PreviewCallback() {

        public void onPreviewFrame(byte[] data, Camera cam) {
            if (data == null)
                throw new NullPointerException();
            Camera.Size size = cam.getParameters().getPreviewSize();
            if (size == null)
                throw new NullPointerException();
            if (!processing.compareAndSet(false, true))
                return;
            int width = size.width;
            int height = size.height;
            // 图像处理
            int imgAvg = ImageProcess.decodeYUV420SPtoRedAvg(data.clone(),
                    height, width);
            gx = imgAvg;
            text1.setText("平均像素值是:" + String.valueOf(imgAvg));
            // 像素平均值imgAvg,日志
            // Log.i(TAG, "imgAvg=" + imgAvg);
            if (imgAvg == 0 || imgAvg == 255) {
                processing.set(false);
                return;
            }

            int averageArrayAvg = 0;
            int averageArrayCnt = 0;
            for (int i = 0; i < averageArray.length; i++) {
                if (averageArray[i] > 0) {
                    averageArrayAvg += averageArray[i];
                    averageArrayCnt++;
                }
            }

            int rollingAverage = (averageArrayCnt > 0) ? (averageArrayAvg / averageArrayCnt)
                    : 0;
            TYPE newType = currentType;
            if (imgAvg < rollingAverage) {
                newType = TYPE.RED;
                if (newType != currentType) {
                    beats++;
                    flag = 0;
                    text2.setText("脉冲数是:" + String.valueOf(beats));
                    // Log.e(TAG, "BEAT!! beats=" + beats);
                }
            } else if (imgAvg > rollingAverage) {
                newType = TYPE.GREEN;
            }

            if (averageIndex == averageArraySize)
                averageIndex = 0;
            averageArray[averageIndex] = imgAvg;
            averageIndex++;

            // Transitioned from one state to another to the same
            if (newType != currentType) {
                currentType = newType;
                // image.postInvalidate();
            }
            // 获取系统结束时间（ms）
            long endTime = System.currentTimeMillis();
            double totalTimeInSecs = (endTime - startTime) / 1000d;
            if (totalTimeInSecs >= 2) {
                double bps = (beats / totalTimeInSecs);
                int dpm = (int) (bps * 60d);
                if (dpm < 30 || dpm > 180 || imgAvg < 200) {
                    // 获取系统开始时间（ms）
                    startTime = System.currentTimeMillis();
                    // beats心跳总数
                    beats = 0;
                    processing.set(false);
                    return;
                }
                // Log.e(TAG, "totalTimeInSecs=" + totalTimeInSecs + " beats="+
                // beats);
                if (beatsIndex == beatsArraySize)
                    beatsIndex = 0;
                beatsArray[beatsIndex] = dpm;
                beatsIndex++;
                int beatsArrayAvg = 0;
                int beatsArrayCnt = 0;
                for (int i = 0; i < beatsArray.length; i++) {
                    if (beatsArray[i] > 0) {
                        beatsArrayAvg += beatsArray[i];
                        beatsArrayCnt++;
                    }
                }
                int beatsAvg = (beatsArrayAvg / beatsArrayCnt);

                txt_ProgressBar1.setTextColor(Color.BLACK);
                txt_ProgressBar2.setTextColor(Color.BLACK);
                txt_ProgressBar3.setTextColor(Color.BLACK);
                txt_ProgressBar4.setTextColor(Color.BLACK);
                txt_ProgressBar5.setTextColor(Color.BLACK);

                txt_ProgressBar1.setText(String.valueOf(beatsAvg));
                heartRate = beatsAvg;
                int xueyang = 0;
                if ((beatsAvg + 25) > 100) {
                    xueyang = 99;
                } else if ((beatsAvg + 25) < 95) {
                    xueyang = 98;
                } else if ((beatsAvg + 25) < 85) {
                    xueyang = 97;
                } else {
                    xueyang = 96;
                }
                double math = Math.random() * 10;
                int random = (int) math;
                Log.e("math", math + "");
                bloodY = xueyang;
                txt_ProgressBar4.setText(String.valueOf(xueyang));
                highRate = HeartHighRate.High(beatsAvg, random);
                txt_ProgressBar2.setText(String.valueOf(HeartHighRate.High(
                        beatsAvg, random)));
                lowRate = HeartLowRate.Low(beatsAvg, random);
                txt_ProgressBar5.setText(String.valueOf(HeartLowRate.Low(
                        beatsAvg, random)));
                Double xn = (double) (beatsAvg / 200d + math / 100);
                DecimalFormat df = new DecimalFormat("0.00");
                String nd = df.format(xn);
                bloodN = (Float.valueOf(nd));
                txt_ProgressBar3.setText(String.valueOf(nd) + "");

                // 获取系统时间（ms）
                startTime = System.currentTimeMillis();
                beats = 0;
            }
            processing.set(false);
        }
    };

    private static SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {

        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(previewHolder);
                camera.setPreviewCallback(previewCallback);
            } catch (Throwable t) {
                // Log.e("PreviewDemo-surfaceCallback","Exception in setPreviewDisplay()",
                // t);
            }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            Camera.Size size = getSmallestPreviewSize(width, height, parameters);
            if (size != null) {
                parameters.setPreviewSize(size.width, size.height);
            }
            camera.setParameters(parameters);
            camera.startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // Ignore
        }
    };

    private static Camera.Size getSmallestPreviewSize(int width, int height,
                                                      Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;
                    if (newArea < resultArea)
                        result = size;
                }
            }
        }
        return result;
    }
}