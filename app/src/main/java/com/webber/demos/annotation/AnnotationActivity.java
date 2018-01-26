package com.webber.demos.annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.webber.demos.R;

import cn.picher.annotations.BindView;

public class AnnotationActivity extends AppCompatActivity {

    @BindView(value = R.id.m_bind_id_tv)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
    }
}
