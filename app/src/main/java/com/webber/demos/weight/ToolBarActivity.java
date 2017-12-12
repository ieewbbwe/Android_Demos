package com.webber.demos.weight;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.webber.demos.R;

public class ToolBarActivity extends AppCompatActivity {

    private AppBarLayout mAppBar;
    private Toolbar mToolBar;
    private ArrayAdapter<String> arrayAdapter;
    private MAutoTextView mAutoTv;
    private MenuItem mSearchMe;
    private TabLayout mTabL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        init();

    }

    private void init() {
        mAppBar = (AppBarLayout) findViewById(R.id.appbar);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        //mBorderEt = (EditText) findViewById(R.id.edtEmail);
        mTabL = (TabLayout) findViewById(R.id.m_tab_l);

        mTabL.addTab(mTabL.newTab().setText("11"));
        mTabL.addTab(mTabL.newTab().setText("222"));

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

        setTextBorder();
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

    public void setTextBorder(){
        GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(this,
                R.drawable.rounded_edittext);
        drawable.setStroke((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                getResources().getDimension(R.dimen.edittext_border_width),getResources().getDisplayMetrics()), Color.parseColor("#0094ff"));
       // setViewBackground(mBorderEt, drawable);
    }

    private static void setViewBackground(View view, Drawable background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            view.setBackground(background);
        else
            view.setBackgroundDrawable(background);
    }
}
