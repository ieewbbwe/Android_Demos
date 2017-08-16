package com.webber.demos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.webber.demos.four.FourComponentActivity;
import com.webber.demos.primary.ReflectActivity;
import com.webber.demos.trandfer.TransferActivity;
import com.webber.demos.weight.CoordinatorActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mContentRlv;
    private DemoInfo[] DEMOS = {
            new DemoInfo("四大组件", "Activity、Service、BroadcastReceiver、ContentProvider", FourComponentActivity.class),
            new DemoInfo("Java反射", "Java反射专题", ReflectActivity.class),
            new DemoInfo("Android 通讯", "Socket、USB、Bluetooth", TransferActivity.class),
            new DemoInfo("CoordinatorLayout", "CoordinatorLayout处理联动", CoordinatorActivity.class),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentRlv = (RecyclerView) findViewById(R.id.m_content_rlv);
        mContentRlv.setLayoutManager(new LinearLayoutManager(this));
        DemoAdapter demoAdapter = new DemoAdapter();
        demoAdapter.setData(DEMOS);
        mContentRlv.setAdapter(demoAdapter);

        demoAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class classes = DEMOS[position].demoClass;
                if (classes != null) {
                    Intent intent;
                    intent = new Intent(MainActivity.this, classes);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "模块还未接入!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

