package com.webber.demos.bugtest;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webber.demos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by picher on 2018/3/23.
 * Describe：
 */

public class FirstFragment extends Fragment{

    private List<FirstInnerFragment> pagerItems = new ArrayList<>();
    private TabLayout mDemoTl;
    private ViewPager mDemoVp;
    private PagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDemoTl = view.findViewById(R.id.m_demo_tl);
        mDemoVp = view.findViewById(R.id.m_demo_vp);

        for (int i = 0; i <= 10; i++) {
            pagerItems.add(new FirstInnerFragment(new ViewPagerItem("Fragment 编号" + i)));
        }
        pagerAdapter = new FirstViewPagerAdapter(getActivity().getSupportFragmentManager());
        mDemoVp.setAdapter(pagerAdapter);
        mDemoVp.setOffscreenPageLimit(1);

        mDemoTl.setupWithViewPager(mDemoVp);
    }


    public class FirstViewPagerAdapter extends FragmentStatePagerAdapter {
        public FirstViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pagerItems.get(position).getViewPagerItem().getName();
        }

        @Override
        public Fragment getItem(int position) {
            return pagerItems.get(position);
        }

        @Override
        public int getCount() {
            return pagerItems.size();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            super.restoreState(state, loader);
            Log.d("picher","adapter restoreState");

        }

        @Override
        public Parcelable saveState() {
            Log.d("picher","adapter saveState");
            return super.saveState();
        }
    }
}
