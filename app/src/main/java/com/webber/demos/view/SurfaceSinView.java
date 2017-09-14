package com.webber.demos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by mxh on 2017/8/17.
 * Describe：正弦曲线
 */

public class SurfaceSinView extends SurfaceView implements SurfaceHolder.Callback {

    private Path mPath;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint mPaint;

    private int x = 0;
    private int y = 0;
    private boolean isDraw = true;

    public SurfaceSinView(Context context) {
        this(context, null);
    }

    public SurfaceSinView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceSinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("picher_log", "Surface创建");
        mPath.moveTo(0, 400);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isDraw) {
                    draw();
                    x += 2;
                    y = (int) (100 * Math.sin(x * Math.PI / 90) + 400);
                    mPath.lineTo(x, y);
                }
            }
        }).start();
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            // SurfaceView背景
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("picher_log", "Surface改变");

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("picher_log", "Surface销毁");
        isDraw = false;
    }
}
