package com.webber.demos.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.webber.demos.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweenAnimActivity extends AppCompatActivity {

    @Bind(R.id.m_start_bt)
    Button mStartBt;
    @Bind(R.id.m_stop_bt)
    Button mStopBt;
    @Bind(R.id.m_choose_sp)
    Spinner mChooseSp;
    @Bind(R.id.m_anim_iv)
    ImageView mAnimIv;
    private String animType;
    private TranslateAnimation translateAnim;
    private RotateAnimation rotateAnim;
    private ScaleAnimation scaleAnim;
    private AlphaAnimation alphaAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mStartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animType = (String) mChooseSp.getSelectedItem();
                startAnim(animType);
            }
        });
        mStopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animType = (String) mChooseSp.getSelectedItem();
                stopAnim(animType);
            }
        });

        mAnimIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TweenAnimActivity.this, "点击了控件！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void stopAnim(String animType) {
        Log.d("picher_log", animType);
        switch (animType) {
            case "位移动画":
                if (translateAnim != null) {
                    translateAnim.cancel();
                }
                break;
            case "缩放动画":
                if (scaleAnim != null) {
                    scaleAnim.cancel();
                }
                break;
            case "旋转动画":
                if (rotateAnim != null) {
                    rotateAnim.cancel();
                }
                break;
            case "透明动画":
                if (alphaAnim != null) {
                    alphaAnim.cancel();
                }
                break;
        }
    }

    private void startAnim(String animType) {
        Log.d("picher_log", animType);
        switch (animType) {
            case "位移动画":
                translate();
                break;
            case "旋转动画":
                rotate();
                break;
            case "缩放动画":
                scale();
                break;
            case "透明动画":
                alpha();
                break;
            case "组合动画":
                animSet();
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /*组合动画 小球下落*/
    private void animSet() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0.4f);
        translateAnimation.setDuration(3000);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.5f);
        alphaAnimation.setDuration(3000);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setInterpolator(new BounceInterpolator());
        mAnimIv.startAnimation(animationSet);
    }

    /*透明动画*/
    private void alpha() {
        alphaAnim = new AlphaAnimation(1, 0f);
        alphaAnim.setDuration(800);
        alphaAnim.setRepeatCount(4);
        alphaAnim.setRepeatMode(Animation.REVERSE);
        mAnimIv.startAnimation(alphaAnim);
    }

    /*缩放动画*/
    private void scale() {
        scaleAnim = new ScaleAnimation(1, 2f, 1, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(2000);
        scaleAnim.setRepeatCount(1);
        scaleAnim.setRepeatMode(Animation.REVERSE);
        mAnimIv.startAnimation(scaleAnim);
    }

    /*旋转动画*/
    private void rotate() {
        rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(2000);
        rotateAnim.setRepeatCount(1);
        rotateAnim.setRepeatMode(Animation.REVERSE);
        mAnimIv.startAnimation(rotateAnim);
    }

    /*位移动画*/
    private void translate() {
        translateAnim = new TranslateAnimation(0, 200, 0, 200);
        translateAnim.setDuration(2000);//动画执行时间
        translateAnim.setRepeatCount(1);//重复两次
        translateAnim.setRepeatMode(Animation.REVERSE);//反向
        mAnimIv.startAnimation(translateAnim);//开始动画
    }


}
