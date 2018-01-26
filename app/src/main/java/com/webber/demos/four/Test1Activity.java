package com.webber.demos.four;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.webber.demos.R;

import static com.webber.demos.four.Test3Activity.ARG_TEST_PARAM;

public class Test1Activity extends TestBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        Log.d("picher","接收得数据："+getIntent().getIntExtra(ARG_TEST_PARAM,-1));

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("picher","接收得数据："+intent.getIntExtra(ARG_TEST_PARAM,-1));
    }
}
