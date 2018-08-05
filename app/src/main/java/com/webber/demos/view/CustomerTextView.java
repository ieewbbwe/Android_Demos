package com.webber.demos.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by picher on 2018/8/2.
 * Describe：
 */

public class CustomerTextView extends AppCompatTextView {

    private ArrayList<String> mContentList = new ArrayList<>();

    private int viewWidth;
    private int viewHeight;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private Paint contentPaint;
    private int contentColor = Color.BLACK;
    private int contentTextSize = 50;
    private int maxContentHeight;
    private int maxContentWidth;
    private int mCurrentIndex = 0;
    private String mCurrentString;
    private String mNextString;
    private float mCurrentX = 0;
    private float mCurrentY = 0;
    private String singleText = "单行文本信息！";
    private int currentStringWidth;
    private int currentStringHeight;
    private float mXOffset;
    private boolean isScrollNext = false;
    private boolean isScrollNow = false;
    private int screenIndex;

    public CustomerTextView(Context context) {
        this(context, null);
    }

    public CustomerTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setContentList(List<String> strings) {
        this.mContentList.addAll(strings);
    }

    private void init() {
        contentPaint = new Paint();
        contentPaint.setAntiAlias(true);
        contentPaint.setDither(true);
        contentPaint.setTextSize(contentTextSize);
        contentPaint.setColor(contentColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();

        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        if (null != mContentList && mContentList.size() > 0) {
            for (int i = 0; i < mContentList.size(); i++) {
                String contentString = mContentList.get(i);
                Rect contentBound = new Rect();
                contentPaint.getTextBounds(contentString, 0, contentString.length(), contentBound);
                int tempWidth = contentBound.width();
                int tempHeight = contentBound.height();
                maxContentHeight = Math.max(maxContentHeight, tempHeight);
                maxContentWidth = Math.max(maxContentWidth, tempWidth);
            }
        } else {
            Rect contentBound = new Rect();
            contentPaint.getTextBounds(singleText, 0, singleText.length(), contentBound);
            maxContentWidth = contentBound.width();
            maxContentHeight = contentBound.height();
        }

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else if (widthMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, maxContentHeight);
        } else if (heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(maxContentWidth, heightSize);
        } else {
            setMeasuredDimension(maxContentWidth, maxContentHeight);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    // 画两条文本
    // 若当前文本长度超过一个屏幕则滚动完成
    // 切换下一条
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mContentList.size() > 1) {
            if (mCurrentIndex >= mContentList.size()) {
                mCurrentIndex = 0;
            }
            // 计算文本
            mCurrentString = mContentList.get(mCurrentIndex);
            mNextString = mContentList.get(mCurrentIndex + 1 >= mContentList.size() ? 0 : mCurrentIndex + 1);
            // 计算绘制区域
            currentStringWidth = calcStringSize(mCurrentString).width();
            currentStringHeight = calcStringSize(mCurrentString).height();
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            mCurrentY = currentStringHeight + layoutParams.topMargin;
            //mCurrentX = layoutParams.leftMargin;

            screenIndex = currentStringWidth / viewWidth + 1;
            //mXOffset = currentStringWidth - viewWidth;
            if(screenIndex > 1){
                // 超过一屏则滚动当前直到末尾 然后切换下一条新闻
                 if(!isScrollNow){
                     startScrollNow();
                 }
            }else{
                // 切换下一条新闻
                if(!isScrollNext){
                    startScrollNext();
                }
            }

            canvas.drawText(mCurrentString, mCurrentX, mCurrentY, contentPaint);
            canvas.drawText(mNextString, mCurrentX + screenIndex * viewWidth, mCurrentY, contentPaint);

        }
    }

    /**
     * 滚动完当前
     */
    private void startScrollNow() {
        isScrollNow = true;
        horizontalScroll(0,(screenIndex - 1) * viewWidth, new LinearInterpolator(),new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 滚动完成后 停顿2秒 切换下一条
                delay(2000, new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        horizontalScroll((screenIndex - 1) * viewWidth,screenIndex * viewWidth, new AccelerateDecelerateInterpolator(), new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                delay(5000, new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        isScrollNow = false;
                                        mCurrentIndex ++;
                                        startScrollNext();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

    }

    private void delay(int ms, Consumer<Long> consumer) {
        Observable.timer(ms, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
        //Timer timer = new Timer();
        //timer.schedule(task, ms);
    }

    private void horizontalScroll(int start, int end, TimeInterpolator interpolator, AnimatorListenerAdapter animatorListenerAdapter) {
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.setDuration(5000);
        animator.setInterpolator(interpolator);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mCurrentX = -value;
                postInvalidate();
            }
        });
        animator.addListener(animatorListenerAdapter);
    }

    /**
     * 切换到下一个
     */
    public void startScrollNext() {
        isScrollNext = true;
        ValueAnimator animator = ValueAnimator.ofFloat(0, viewWidth);
        animator.setDuration(5000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());// 先快后慢
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mCurrentX = -value;
                postInvalidate();
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 停顿2s 后切换下一条
              delay(2000, new Consumer<Long>() {
                  @Override
                  public void accept(Long aLong) throws Exception {
                      isScrollNext = false;
                      mCurrentIndex ++;
                      startScrollNext();
                  }
              });
            }
        });
    }

    private Rect calcStringSize(String str) {
        Rect rect = new Rect();
        contentPaint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }
}
