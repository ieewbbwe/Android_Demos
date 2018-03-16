package com.webber.demos.weight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.webber.demos.R;

public class ToolBarDemoActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_demo);
        toolbar = findViewById(R.id.m_tool_bar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);// 给左上角图标的左边加上一个返回的图标
        //getSupportActionBar().setHomeButtonEnabled(true);//左上角图标是否可以点击
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toor_bar_demo, menu);
        return true;
    }
}
