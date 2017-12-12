package com.webber.demos.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webber.demos.R;

/**
 * Created by picher on 2017/12/12.
 * Describe：
 */

public class NormalFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_normal_layout, container, false);
        return v;
    }
}
