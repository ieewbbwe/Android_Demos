package com.webber.demos.four;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.webber.demos.R;

public class Test3Activity extends TestBaseActivity {

    public static final String ARG_TEST_PARAM = "arg_test_param";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        Log.d("picher","接收得数据："+getIntent().getIntExtra(ARG_TEST_PARAM,0));
    }
}
