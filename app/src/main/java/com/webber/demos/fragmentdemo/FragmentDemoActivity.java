package com.webber.demos.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.webber.demos.R;

public class FragmentDemoActivity extends AppCompatActivity {

    private FrameLayout mContainer;
    private Button mAddFragmentBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        mContainer = (FrameLayout) findViewById(R.id.m_fragment_container_fl);
        mAddFragmentBt = (Button) findViewById(R.id.m_add_fragment_bt);

        mAddFragmentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment listFragment = new ListFragment();
                switchFragment(listFragment);
            }
        });

    }

    private void switchFragment(ListFragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment findFragment = manager.findFragmentByTag(fragment.getClass().getSimpleName());
        if (findFragment != null) {
            Log.d("picher", "根据TAG找到Fragment：" + findFragment.getClass().getSimpleName());
        }

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.m_content_fl, fragment, fragment.getClass().getSimpleName());

        transaction.commit();
    }
    
}
