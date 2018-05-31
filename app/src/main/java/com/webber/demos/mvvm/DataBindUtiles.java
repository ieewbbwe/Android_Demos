package com.webber.demos.mvvm;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by picher on 2018/5/31.
 * Describe：
 */

public class DataBindUtiles {

    @BindingAdapter("converToUpperCase")
    public static String converToUpperCase(TextView textView, String upText){
        return upText.toUpperCase();
    }
}
