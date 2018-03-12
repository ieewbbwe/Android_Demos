package com.webber.demos.four;

import android.content.Intent;
import android.os.Build;
import android.os.LocaleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.webber.demos.R;

import java.util.List;
import java.util.Locale;

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

        Locale locale = Locale.getDefault();
        Log.d("picher","country:"+locale.getCountry()+"toString:"+locale.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
            //locale = LocaleList.getDefault().get(0);
        }else{
            locale = getResources().getConfiguration().locale;
            //locale = Locale.getDefault();
        }
        Log.d("picher","country:"+locale.getCountry()+"toString:"+locale.toString());
    }
}
