package com.webber.demos.media;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.webber.demos.R;

import java.lang.reflect.Method;

public class MediaDemoActivity extends AppCompatActivity {

    //public static String video_url = "http://video.appledaily.com.hk/mcp/encode/2018/03/01/3558058/20180301_int_73_m.mp4";
    public static String video_url = "http://video.appledaily.com.hk/mcp/encode/2018/03/05/3560800/20180305_int_81_m.mp4";
    private RelativeLayout mRootRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_demo);
        hideBottomUIMenu();
        mRootRl = findViewById(R.id.m_root_rl);
        printScreenInfo();
    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void printScreenInfo() {
        Display display = getWindowManager().getDefaultDisplay();
        final Point point = new Point();
        display.getSize(point);

        Log.d("picher","ScreenW:"+point.x + "ScreenH:" + point.y +"real:"+getRealHeight(this)+"VH:"+(getRealHeight(this)-getHeight(this)));
        Log.d("picher","con:"+getVirtualBarHeigh());

        mRootRl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRootRl.setLayoutParams(new FrameLayout.LayoutParams(getRealHeight(MediaDemoActivity.this) /*+ getVirtualBarHeigh()*/, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        });
    }

    /**获取虚拟功能键高度 */
    public int getVirtualBarHeigh() {
        int vh = 0;
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.widthPixels - windowManager.getDefaultDisplay().getWidth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }

    public static int getRealHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealMetrics(dm);
        } else {
            display.getMetrics(dm);
        }
        int realHeight = dm.widthPixels;
        return realHeight;
    }

    public static int getHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        int height = dm.widthPixels;
        return height;
    }
}
