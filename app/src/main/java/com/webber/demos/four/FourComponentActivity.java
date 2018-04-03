package com.webber.demos.four;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.LocaleList;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.webber.demos.R;
import com.webber.demos.four.receiver.CustomerReceiver;

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

        findViewById(R.id.m_receiver_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.urbanairship.push.RECEIVED");
                Bundle bundle = new Bundle();
                bundle.putString("sound","off");
                intent.putExtra("com.urbanairship.push.EXTRA_PUSH_MESSAGE_BUNDLE",bundle);

                sendBroadcast(intent);
            }
        });

        activityDemo();
        receiverDemo();
    }

    private void receiverDemo() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("");


        registerReceiver(new CustomerReceiver(), intentFilter);
    }

    private void activityDemo() {

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("picher","onStart");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d("picher","onCreate");

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("picher","onSaveInstanceState");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("picher","onResume");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("picher","onRestoreInstanceState");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("picher","onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("picher","onRestart");
    }
}
