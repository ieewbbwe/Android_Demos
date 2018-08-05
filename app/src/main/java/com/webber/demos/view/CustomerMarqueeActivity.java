package com.webber.demos.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.webber.demos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by picher on 2018/8/5.
 * Describe：
 */

public class CustomerMarqueeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_marquee);
        CustomerTextView textView = findViewById(R.id.m_customer);
        List<String> strings = new ArrayList<>();
        strings.add("Start! 11111111111111111111111111111111111111111111111111 End!");
        //strings.add("Start! 111111 End!");
        strings.add("Start! 222222 End!");
        strings.add("Start! 333333 End!");
        textView.setContentList(strings);
    }
}
