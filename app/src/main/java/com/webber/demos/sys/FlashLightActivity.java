package com.webber.demos.sys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.webber.demos.R;

public class FlashLightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flash_light);

    }

    public void flashClick(View v) {
        flash(500, 3, 500);
    }

    private void flash(int falshTime, int repeat, int interval) {
        Toast.makeText(this,"click",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction(FlushLightReceiver.FLUSHLIGHT_ACTION);
        intent.putExtra("repeat", repeat);
        /*intent.setAction("com.google.android.c2dm.intent.REGISTRATION");
        intent.setAction("com.google.android.c2dm.intent.RECEIVE");
        Bundle bundle = new Bundle();
        bundle.putString("FB_PUSH_CARD","test");
        intent.putExtras(bundle);*/
        sendBroadcast(intent);

    }
}
