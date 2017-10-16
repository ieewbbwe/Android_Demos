package com.webber.demos.weight;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.webber.demos.R;

public class ToolBarActivity extends AppCompatActivity {

    private AppBarLayout mAppBar;
    private Toolbar mToolBar;
    private ArrayAdapter<String> arrayAdapter;
    private MAutoTextView mAutoTv;
    private MenuItem mSearchMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        init();
    }

    private void init() {
        mAppBar = (AppBarLayout) findViewById(R.id.appbar);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        int styleId = R.style.OneEcToolBar;
        TypedArray array = getTheme().obtainStyledAttributes(styleId, new int[]{android.R.attr.background
                , android.support.v7.appcompat.R.attr.titleTextColor});
        mToolBar.setBackgroundDrawable(array.getDrawable(0));
        mToolBar.setTitleTextColor(array.getColor(1, Color.WHITE));
        array.recycle();

       /* mAutoTv = (AutoCompleteTextView) findViewById(R.id.m_auto_tv);

        String[] arr = {"aa", "aab", "aac"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        mAutoTv.setAdapter(arrayAdapter);*/

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        setSupportMenu(menu);
        return true;
    }

    private void setSupportMenu(Menu menu) {
        mSearchMe = menu.findItem(R.id.menu_search);
        mAutoTv = (MAutoTextView) mSearchMe.getActionView();

        mSearchMe.expandActionView();

        MenuItemCompat.setOnActionExpandListener(mSearchMe, mAutoTv);

        mAutoTv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    mSearchMe.collapseActionView();
                }
            }
        });
    }
}
