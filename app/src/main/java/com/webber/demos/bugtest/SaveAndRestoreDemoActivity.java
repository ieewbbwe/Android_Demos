package com.webber.demos.bugtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.webber.demos.R;

/**
 * Created by picher on 2018/3/23.
 * Describe：
 */

public class SaveAndRestoreDemoActivity extends AppCompatActivity {

    private FrameLayout mContainerFl;
    private FirstFragment tabFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_and_restore_demo);
        mContainerFl = findViewById(R.id.m_container_fl);

        tabFragment = new FirstFragment();

        switchFragment(tabFragment);

    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.m_container_fl,fragment).addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(popLanding()){
            Log.d("picher","返回成功");
        }
    }

    protected boolean popLanding() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        try {
            if (fragmentManager.getBackStackEntryCount() > 1) {
                fragmentManager.popBackStack();
                //fragmentManager.popBackStackImmediate(FirstFragment.class.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                //this method should only do one thing, below code should move to side
                //onNavigationDrawerItemSelected(0); // ensure proper landing is selected

                return true;
            }
        } catch (Exception ex) {
        }

        return false;
    }
}
