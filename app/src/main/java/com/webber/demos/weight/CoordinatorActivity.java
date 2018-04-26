package com.webber.demos.weight;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        FrameLayout fragment = (FrameLayout) findViewById(R.id.m_container_fl);
        final ActionBar actionBar = getSupportActionBar();
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

        mTestWv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("picher","finish bottomPadding:"+view.getPaddingBottom());
            }
        });
        mTestWv.getSettings().setJavaScriptEnabled(true);

        //mTestWv.loadUrl("https://www.baidu.com/");
        //mTestWv.loadUrl("https://hk.appledaily.com/livestream/channel/legco/?&mobile=1");
        //mTestWv.loadUrl("https://hk.feature.appledaily.com/overseasproperty/");
        mTestWv.loadUrl("https://blog.csdn.net/mq2856992713/article/details/78574444");

        coordinatorLayout = findViewById(R.id.m_root_cdl);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("picher",""+verticalOffset);
            }
        });
        findViewById(R.id.m_expend_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appbar.setExpanded(false);
            }
        });
    }
}
