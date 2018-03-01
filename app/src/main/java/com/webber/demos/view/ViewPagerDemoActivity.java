package com.webber.demos.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.webber.demos.R;
import com.webber.demos.fragmentdemo.BaseFragment;
import com.webber.demos.fragmentdemo.Fragment1;
import com.webber.demos.fragmentdemo.Fragment2;
import com.webber.demos.fragmentdemo.Fragment3;
import com.webber.demos.fragmentdemo.Fragment4;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDemoActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerFragmentAdapter moreAdapter;
    private List<View> views = new ArrayList<>();
    private List<BaseFragment> fragments = new ArrayList<>();
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo_acticity);
        mViewPager = findViewById(R.id.m_container_vp);
        tabLayout = findViewById(R.id.m_tab_tl);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        moreAdapter = new PagerFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(moreAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(1);

        /*-----Create Data----*/
        /*PageView pageView;
        PageView addMore = new PageView(this) {
            @Override
            protected int getLayoutRes() {
                return R.layout.layout_viewpager_add_more;
            }
        };
        for(int i=0;i<3;i++){
            pageView = new PageView(this) {
                @Override
                protected int getLayoutRes() {
                    return R.layout.layout_viewpager_normal;
                }
            };
            views.add(pageView);
        }
        views.add(addMore);4
        moreAdapter.setData(views);
        */
        fragments.add(new Fragment1("我是第"+1));
        fragments.add(new Fragment2("我是第"+2));
        fragments.add(new Fragment3("我是第"+3));
        fragments.add(new Fragment4("我是第"+4));

        moreAdapter.setData(fragments);
        moreAdapter.notifyDataSetChanged();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               // Log.d("picher", "onPageScrolled：" + position + "-->>positionOffset:" + positionOffset + "-->>positionOffsetPixels:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("picher", "onPageSelected:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Log.d("picher", "onPageScrollStateChanged:" + state);
            }
        });
    }

    public class AddMoreAdapter extends PagerAdapter {

        private List<View> data;

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = data.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public void setData(List<View> data) {
            this.data = data;
        }
    }
}
