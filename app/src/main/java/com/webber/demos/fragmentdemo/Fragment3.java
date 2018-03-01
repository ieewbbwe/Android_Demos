package com.webber.demos.fragmentdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webber.demos.R;

/**
 * Created by picher on 2018/2/12.
 * Describe：
 */

public class Fragment3 extends BaseFragment {

    private TextView textView;

    public Fragment3() {
    }

    @SuppressLint("ValidFragment")
    public Fragment3(String title){
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.m_test_fm);
        textView.setText(title);
    }
}
