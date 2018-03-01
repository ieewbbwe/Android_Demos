package com.webber.demos.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.webber.demos.R;

/**
 * Created by picher on 2018/2/9.
 * Describe：
 */

public abstract class PageView extends FrameLayout {

    private View rootView;

    public PageView(@NonNull Context context) {
        this(context,null);
    }

    public PageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rootView = LayoutInflater.from(getContext())
                .inflate(getLayoutRes(),this,false);
        addView(rootView);
    }

    protected abstract int getLayoutRes();
}
