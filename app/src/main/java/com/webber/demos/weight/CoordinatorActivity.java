package com.webber.demos.weight;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.webber.demos.R;

import static android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS;
import static android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED;
import static android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL;
import static android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP;

public class CoordinatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FrameLayout fragment = (FrameLayout) findViewById(R.id.m_container_fl);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);

        CoordinatorLayout c = (CoordinatorLayout) fragment.getParent();
        //((CoordinatorLayout.LayoutParams)fragment.getLayoutParams()).setBehavior(null);
       // ((AppBarLayout.LayoutParams)toolbar.getLayoutParams()).setScrollFlags(SCROLL_FLAG_SCROLL | SCROLL_FLAG_SNAP | SCROLL_FLAG_ENTER_ALWAYS);
        WebView mTestWv = (WebView) findViewById(R.id.m_test_wv);
        mTestWv.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.d("picher",""+newProgress);
            }
        });

        mTestWv.setWebViewClient(new WebViewClient());

        //mTestWv.loadUrl("https://www.baidu.com/");
        mTestWv.loadUrl("https://hk.appledaily.com/livestream/channel/legco/?&mobile=1");
    }
}
