package com.webber.demos.bugtest;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by picher on 2018/3/23.
 * Describe：
 */

public class CustomerViewPager extends ViewPager {
    public CustomerViewPager(Context context) {
        this(context,null);
    }

    public CustomerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Log.d("picher","onSaveInstanceState");
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        Log.d("picher","onRestoreInstanceState");

    }
}
