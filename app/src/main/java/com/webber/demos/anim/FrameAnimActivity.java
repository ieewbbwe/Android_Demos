package com.webber.demos.anim;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.webber.demos.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FrameAnimActivity extends AppCompatActivity {

    @Bind(R.id.m_start_bt)
    Button mStartBt;
    @Bind(R.id.m_stop_bt)
    Button mStopBt;
    @Bind(R.id.m_content_iv)
    ImageView mContentIv;
    private AnimationDrawable mWifiAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim);
        ButterKnife.bind(this);
        init();
        codeRealize();
    }

    /*代码实现*/
    private void codeRealize() {
        AnimationDrawable animDrawableBg = new AnimationDrawable();
        animDrawableBg.addFrame(getResources().getDrawable(R.mipmap.wifi1), 500);
        animDrawableBg.addFrame(getResources().getDrawable(R.mipmap.wifi2), 500);
        animDrawableBg.addFrame(getResources().getDrawable(R.mipmap.wifi3), 500);
        animDrawableBg.addFrame(getResources().getDrawable(R.mipmap.wifi4), 500);
        animDrawableBg.addFrame(getResources().getDrawable(R.mipmap.wifi5), 500);
        animDrawableBg.addFrame(getResources().getDrawable(R.mipmap.wifi6), 500);
        animDrawableBg.setOneShot(false);//设置循环播
        mContentIv.setImageDrawable(animDrawableBg);
    }

    private void init() {
        mContentIv.setImageResource(R.drawable.anim_frame_wifi);
        mWifiAd = (AnimationDrawable) mContentIv.getDrawable();

        mStartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWifiAd.start();
                //animDrawableBg.start();
            }
        });
        mStopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWifiAd.stop();
                //  animDrawableBg.stop();
            }
        });
    }
}
