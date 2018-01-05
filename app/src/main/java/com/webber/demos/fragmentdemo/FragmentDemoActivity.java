package com.webber.demos.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.webber.demos.R;

public class FragmentDemoActivity extends AppCompatActivity {

    private FrameLayout mContainer;
    private Button mAddFragmentBt;
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        mContainer = (FrameLayout) findViewById(R.id.m_fragment_container_fl);
        mAddFragmentBt = (Button) findViewById(R.id.m_add_fragment_bt);

        mAddFragmentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                if (click == 0) {
                    fragment = new ListFragment();
                } else {
                    fragment = new NormalFragment();
                }
                click++;
                switchFragment(fragment);
            }
        });

    }

    private void switchFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment findFragment = manager.findFragmentByTag(fragment.getClass().getSimpleName());
        if (findFragment != null) {
            Log.d("picher", "根据TAG找到Fragment：" + findFragment.getClass().getSimpleName());
        }

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.replace(R.id.m_fragment_container_fl, fragment, fragment.getClass().getSimpleName());
        transaction.commit();

        manager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    Log.d("picher", "添加了：" + getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName());
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("picher", "keyCode:" + keyCode + "  KeyEvent:" + event.getAction());
        switch (event.getAction()) {
            case KeyEvent.ACTION_DOWN:

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        Log.d("picher", "stackCount:" + getSupportFragmentManager().getBackStackEntryCount() + "fragments:" + getSupportFragmentManager().getFragments().size());

                        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                            Log.d("picher", "backStackName:" + getSupportFragmentManager().getBackStackEntryAt(i).getName());
                        }
                        getSupportFragmentManager().popBackStackImmediate();
                        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                            Log.d("picher", "弹栈之后backStackName:" + getSupportFragmentManager().getBackStackEntryAt(i).getName());
                        }
                        return true;
                    }

                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}