package com.webber.demos.media;

import android.animation.ValueAnimator;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.IOException;

/**
 * Created by picher on 2018/3/1.
 * Describe：
 */

public class CustomerVideoView extends SurfaceView
        implements MediaControl {

    private MediaPlayer mMediaPlayer;
    private SurfaceHolder surfaceHolder;
    private int mVideoWidth;
    private int mVideoHeight;

    public CustomerVideoView(Context context) {
        this(context,null);
    }

    public CustomerVideoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomerVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mMediaPlayer = new MediaPlayer();
        //設置循環播放
        mMediaPlayer.setLooping(true);

        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("picher","media-->> onError");
                return false;
            }
        });
        mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                Log.d("picher","media-->> onVideoSizeChanged" + "w:"+width+"h:"+height);
                mVideoWidth = mp.getVideoWidth();
                mVideoHeight = mp.getVideoHeight();
                if (mVideoWidth != 0 && mVideoHeight != 0) {
                    getHolder().setFixedSize(mVideoWidth, mVideoHeight);
                    requestLayout();
                }
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("picher","media-->> onCompletion");
                mMediaPlayer.start();
            }
        });

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d("picher","media-->> onPrepared");
                mp.start();
            }
        });
        mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                //Log.d("picher","media->> setOnBufferingUpdateListener percent: " + percent);
            }
        });

        surfaceHolder = getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("picher","surfaceCreated");
                try {
                    mMediaPlayer.reset();
                    mMediaPlayer.setDisplay(holder);
                    mMediaPlayer.setDataSource(MediaDemoActivity.video_url);
                    mMediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d("picher","surfaceChanged");

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("picher","surfaceDestroyed");
                mMediaPlayer.stop();
                mMediaPlayer.release();
                surfaceHolder.removeCallback(this);
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("picher","onMeasure->>w:"+widthMeasureSpec+"h:"+heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);//width / 16/9;//MeasureSpec.getSize(heightMeasureSpec);
        int width = height*16/9;//MeasureSpec.getSize(widthMeasureSpec);
        Log.d("picher","after onMeasure->>w:"+width+"h:"+height);
        setMeasuredDimension(width,height);
    }

    @Override
    public void play() {
        mMediaPlayer.start();

    }

    @Override
    public void pause() {
        mMediaPlayer.pause();
    }

    @Override
    public void stop() {

    }

    private void listPressAnimation(final View view, int startX, int endX, final boolean isShowList) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(startX, endX);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((Float) animation.getAnimatedValue()).intValue(), RelativeLayout.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mMediaPlayer.isPlaying()){
            pause();
        }else{
            play();
        }
        return super.onTouchEvent(event);
    }
}
