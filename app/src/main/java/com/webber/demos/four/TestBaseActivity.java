package com.webber.demos.four;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by picher on 2018/1/25.
 * Describe：
 */

public class TestBaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d("picher",getClass().getSimpleName()+"--->>> onCreate()");
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.d("picher",getClass().getSimpleName()+"--->>> onCreateView()");
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("picher",getClass().getSimpleName()+"--->>> onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("picher",getClass().getSimpleName()+"--->>> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("picher",getClass().getSimpleName()+"--->>> onPause()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("picher",getClass().getSimpleName()+"--->>> onStop()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("picher",getClass().getSimpleName()+"--->>> onReStart()");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("picher",getClass().getSimpleName()+"--->>> onNewIntent()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("picher",getClass().getSimpleName()+"--->>> onDestroy()");

    }
}
