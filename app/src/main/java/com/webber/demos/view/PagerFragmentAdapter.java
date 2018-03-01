package com.webber.demos.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.webber.demos.fragmentdemo.BaseFragment;
import com.webber.demos.fragmentdemo.Fragment1;

import java.util.List;

/**
 * Created by picher on 2018/2/12.
 * Describe：
 */

public class PagerFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public PagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<BaseFragment> data){
        this.fragments = data;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null?0:fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).title;
    }
}
