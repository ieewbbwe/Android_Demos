package com.webber.demos;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.webber.demos.anim.AnimActivity;
import com.webber.demos.annotation.AnnotationActivity;
import com.webber.demos.customerview.CustomerViewActivity;
import com.webber.demos.database.GreenDaoActivity;
import com.webber.demos.four.FourComponentActivity;
import com.webber.demos.fragmentdemo.FragmentDemoActivity;
import com.webber.demos.media.MediaDemoActivity;
import com.webber.demos.mvvm.DataBindingActivity;
import com.webber.demos.nest.ViewPagerFragmentActivity;
import com.webber.demos.notify.NotificationActivity;
import com.webber.demos.primary.ReflectActivity;
import com.webber.demos.sys.FlashLightActivity;
import com.webber.demos.trandfer.TransferActivity;
import com.webber.demos.tv.TvDemosActivity;
import com.webber.demos.update.VersionUpdateActivity;
import com.webber.demos.view.SurfaceViewActivity;
import com.webber.demos.view.ViewPagerDemoActivity;
import com.webber.demos.view.WebViewActivity;
import com.webber.demos.view.camera.CameraActivity;
import com.webber.demos.weight.BottomSheetActivity;
import com.webber.demos.weight.CoordinatorActivity;
import com.webber.demos.weight.RecyclerViewActivity;
import com.webber.demos.weight.SpannableActivity;
import com.webber.demos.weight.ToolBarActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mContentRlv;
    private DemoInfo[] DEMOS = {
            new DemoInfo("四大组件", "Activity、Service、BroadcastReceiver、ContentProvider", FourComponentActivity.class),
            new DemoInfo("Fragment", "Fragment 生命周期，replace、add", FragmentDemoActivity.class),
            new DemoInfo("MediaPlayer", "多媒體資源專題", MediaDemoActivity.class),
            new DemoInfo("数据库相关GreenDao", "GreenDao的增删改查", GreenDaoActivity.class),
            new DemoInfo("版本升级更新", "8.0版本升级", VersionUpdateActivity.class),
            new DemoInfo("BottomSheet底部弹出框", "BottomSheet", BottomSheetActivity.class),
            new DemoInfo("列表相关", "RecycleView的坑", RecyclerViewActivity.class),
            new DemoInfo("ViewPager", "ViewPager的例子", ViewPagerDemoActivity.class),
            new DemoInfo("VP&FGM嵌套", "各种嵌套问题", ViewPagerFragmentActivity.class),
            new DemoInfo("加载网页", "使用WebView加载", WebViewActivity.class),
            new DemoInfo("Java反射", "Java反射专题", ReflectActivity.class),
            new DemoInfo("注解", "Annotation練習",AnnotationActivity.class),
            new DemoInfo("Android 通讯", "Socket、USB、Bluetooth", TransferActivity.class),
            new DemoInfo("CoordinatorLayout", "CoordinatorLayout处理联动", CoordinatorActivity.class),
            new DemoInfo("SurfaceView", "SurfaceView学习", SurfaceViewActivity.class),
            new DemoInfo("动画专题", "属性动画、布局动画", AnimActivity.class),
            new DemoInfo("相机数据实时显示", "使用SurfaceView显示相机", CameraActivity.class),
            new DemoInfo("TV相关示例", "TV开发相关示例", TvDemosActivity.class),
            new DemoInfo("ToolBar学习", "官方推出的头部控件", ToolBarActivity.class),
            new DemoInfo("Fragment", "Fragment这个坑是要填一填", ToolBarActivity.class),
            new DemoInfo("FlashLight", "闪光灯Demo",FlashLightActivity.class),
            new DemoInfo("自定义控件", "CustomerView",CustomerViewActivity.class),
            new DemoInfo("MVVM", "使用DataBinding",DataBindingActivity.class),
            new DemoInfo("通知相关Demo", "Notify",NotificationActivity.class),
            new DemoInfo("富文本", "富文本练习",SpannableActivity.class),
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
        //Locale.setDefault(Locale.TRADITIONAL_CHINESE);
        forceLocale(Locale.TRADITIONAL_CHINESE);
        Log.d("picher","setDefault:"+Locale.getDefault().toString());
    }

    public void forceLocale(Locale locale) {
        Configuration conf = getResources().getConfiguration();
        updateConfiguration(conf, locale);
        getResources().updateConfiguration(conf, getResources().getDisplayMetrics());

        Configuration systemConf = Resources.getSystem().getConfiguration();
        updateConfiguration(systemConf, locale);
        Resources.getSystem().updateConfiguration(conf, getResources().getDisplayMetrics());

        Locale.setDefault(locale);
    }

    public void updateConfiguration(Configuration conf, Locale locale) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            conf.setLocale(locale);
        }else {
            //noinspection deprecation
            conf.locale = locale;
        }
    }
}

