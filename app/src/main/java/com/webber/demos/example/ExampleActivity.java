package com.webber.demos.example;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webber.demos.R;
import com.webber.demos.weight.RecycleViewTestAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExampleActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationView mSlidNv;
    private DrawerLayout mDrawableDl;
    private RecyclerView mExampleRlv;
    private List<String> strings = new ArrayList<>();
    private ExampleAdapter mExampleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        mToolbar = findViewById(R.id.m_example_tb);
        mSlidNv = findViewById(R.id.m_example_slid_nv);
        mDrawableDl = findViewById(R.id.m_example_dl);
        mExampleRlv = findViewById(R.id.m_example_rlv);

        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,mDrawableDl,mToolbar,R.string.app_name,R.string.label_name);
        drawerToggle.syncState();
        mDrawableDl.addDrawerListener(drawerToggle);


        for(int i=0;i<50;i++){
            strings.add("列表数据"+i);
        }
        mExampleRlv.setLayoutManager(new LinearLayoutManager(this));
        mExampleAdapter = new ExampleAdapter();
        mExampleRlv.setAdapter(mExampleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.example_tool_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.m_toast_bt:
                snackToast("我是一个toast");
                break;
            case R.id.m_clickable_bt:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void snackToast(String str) {
        Snackbar.make(mDrawableDl,str,Snackbar.LENGTH_SHORT).show();
    }

    public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(strings.get(position));
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
