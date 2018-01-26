package com.webber.demos.four;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.webber.demos.R;

import java.util.List;

public class Test2Activity extends TestBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        findViewById(R.id.m_test_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Test2Activity.this,Test1Activity.class);
                intent.putExtra(Test3Activity.ARG_TEST_PARAM,1);
                startActivity(intent);
            }
        });
    }
}
