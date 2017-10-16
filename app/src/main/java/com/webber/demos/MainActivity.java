package com.webber.demos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.webber.demos.anim.AnimActivity;
import com.webber.demos.four.FourComponentActivity;
import com.webber.demos.primary.ReflectActivity;
import com.webber.demos.sys.FlashLightActivity;
import com.webber.demos.trandfer.TransferActivity;
import com.webber.demos.tv.TvDemosActivity;
import com.webber.demos.view.SurfaceViewActivity;
import com.webber.demos.view.camera.CameraActivity;
import com.webber.demos.weight.CoordinatorActivity;
import com.webber.demos.weight.ToolBarActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mContentRlv;
    private DemoInfo[] DEMOS = {
            new DemoInfo("四大组件", "Activity、Service、BroadcastReceiver、ContentProvider", FourComponentActivity.class),
            new DemoInfo("Java反射", "Java反射专题", ReflectActivity.class),
            new DemoInfo("Android 通讯", "Socket、USB、Bluetooth", TransferActivity.class),
            new DemoInfo("CoordinatorLayout", "CoordinatorLayout处理联动", CoordinatorActivity.class),
            new DemoInfo("SurfaceView", "SurfaceView学习", SurfaceViewActivity.class),
            new DemoInfo("动画专题", "属性动画、布局动画", AnimActivity.class),
            new DemoInfo("相机数据实时显示", "使用SurfaceView显示相机", CameraActivity.class),
            new DemoInfo("TV相关示例", "TV开发相关示例", TvDemosActivity.class),
            new DemoInfo("ToolBar学习", "官方推出的头部控件", ToolBarActivity.class),
            new DemoInfo("Fragment", "Fragment这个坑是要填一填", ToolBarActivity.class),
            new DemoInfo("FlashLight", "闪光灯Demo",FlashLightActivity.class),
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

