package com.webber.demos.anim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.webber.demos.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NineAnimActivity extends AppCompatActivity {

    @Bind(R.id.m_start_bt)
    Button mStartBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_anim);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mStartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
