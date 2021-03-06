package com.webber.demos.bugtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webber.demos.R;
import com.webber.demos.anim.AnimActivity;

/**
 * Created by picher on 2018/3/23.
 * Describe：
 */

public class SecondInnerFragment extends Fragment {

    private TextView mDemoTv;
    private ViewPagerItem viewPagerItem;

    public SecondInnerFragment() {
    }

    public SecondInnerFragment(ViewPagerItem viewPagerItem) {
        this.viewPagerItem = viewPagerItem;
    }

    public ViewPagerItem getViewPagerItem() {
        return viewPagerItem;
    }

    public void setViewPagerItem(ViewPagerItem viewPagerItem) {
        this.viewPagerItem = viewPagerItem;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_demo,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDemoTv = view.findViewById(R.id.m_demo_tv);
        mDemoTv.setText(viewPagerItem.getName());

        mDemoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AnimActivity.class));
            }
        });
    }
}
