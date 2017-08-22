package com.webber.demos.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.webber.demos.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnimActivity extends AppCompatActivity {

    @Bind(R.id.m_frame_bt)
    Button mFrameBt;
    @Bind(R.id.m_tween_bt)
    Button mTweenBt;
    @Bind(R.id.m_property_bt)
    Button mPropertyBt;
    @Bind(R.id.m_nine_old_bt)
    Button mNineOldBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);
        mFrameBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushActivity(FrameAnimActivity.class);
            }
        });
        mTweenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushActivity(TweenAnimActivity.class);
            }
        });
        mPropertyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushActivity(PropertyAnimActivity.class);
            }
        });
        mNineOldBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushActivity(NineAnimActivity.class);
            }
        });
    }

    private void pushActivity(Class aClass) {
        startActivity(new Intent(AnimActivity.this, aClass));
    }
}
