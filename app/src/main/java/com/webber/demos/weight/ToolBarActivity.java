package com.webber.demos.weight;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.webber.demos.R;

public class ToolBarActivity extends AppCompatActivity {

    private AppBarLayout mAppBar;
    private Toolbar mToolBar;
    private AutoCompleteTextView mAutoTv;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        init();
    }

    private void init() {
        mAppBar = (AppBarLayout) findViewById(R.id.appbar);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
       /* mAutoTv = (AutoCompleteTextView) findViewById(R.id.m_auto_tv);

        String[] arr = {"aa", "aab", "aac"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        mAutoTv.setAdapter(arrayAdapter);*/

        setSupportActionBar(mToolBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        setSupportMenu(menu);
        return true;
    }

    private void setSupportMenu(Menu menu) {
        MenuItem mSearchMe = menu.findItem(R.id.menu_search);
        MAutoTextView mAutoTv = (MAutoTextView) mSearchMe.getActionView();

        mSearchMe.expandActionView();

        MenuItemCompat.setOnActionExpandListener(mSearchMe, mAutoTv);
    }
}
