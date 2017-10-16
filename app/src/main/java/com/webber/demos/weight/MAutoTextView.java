package com.webber.demos.weight;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mxh on 2017/9/29.
 * Describe：
 */

public class MAutoTextView extends AppCompatAutoCompleteTextView implements MenuItemCompat.OnActionExpandListener {

    public MAutoTextView(Context context) {
        this(context, null);
    }

    public MAutoTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MAutoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

        String[] arr = {"aa", "aab", "aac"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, arr);
        setAdapter(adapter);
        setDropDownBackgroundDrawable(new ColorDrawable(-1));
    }

    @Override
    public void showDropDown() {
        super.showDropDown();
        Log.d("picher", "showDropDown");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d("picher", String.format("w:%d->>h:%d->>ow:%d->>oh:%d", w, h, oldw, oldh));
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("picher", String.format("w:%d->>h:%d->>ow:%d->>oh:%d", w, h, oldw, oldh));
        int rw = this.getRootView().getWidth();
        this.setDropDownWidth(rw);
       // this.setDropDownVerticalOffset((this.getMeasuredHeight() - this.getMeasuredHeight()) / 2);
        Log.d("picher", "calcSize:" + ((this.getMeasuredHeight() - this.getMeasuredHeight()) / 2));
        Log.d("picher", "rw:" + rw);
    }

    private void init(Context context) {
        Method method = null;

        try {
            method = this.getClass().getMethod("setDropDownAlwaysVisible", new Class[]{Boolean.TYPE});
        } catch (SecurityException var7) {
            ;
        } catch (NoSuchMethodException var8) {
            ;
        }

        try {
            if (method != null) {
                method.invoke(this, new Object[]{Boolean.valueOf(true)});
            }
        } catch (IllegalArgumentException var4) {
            ;
        } catch (IllegalAccessException var5) {
            ;
        } catch (InvocationTargetException var6) {
            ;
        }

    }


    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        Log.d("picher", "onMenuItemActionExpand");
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        Log.d("picher", "onMenuItemActionCollapse");
        return false;
    }
}
