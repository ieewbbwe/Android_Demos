package com.webber.demos.four;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.webber.demos.R;

public class FourComponentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_component);

        findViewById(R.id.m_test_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FourComponentActivity.this,Test1Activity.class);
                intent1.putExtra(Test3Activity.ARG_TEST_PARAM,0);

                Intent intent2 = new Intent(FourComponentActivity.this,Test2Activity.class);

                startActivities(new Intent[]{intent1,intent2});
            }
        });
    }
}
