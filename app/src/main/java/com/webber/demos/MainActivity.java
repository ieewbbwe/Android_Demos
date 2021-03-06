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
import com.webber.demos.bugtest.SaveAndRestoreDemoActivity;
import com.webber.demos.customerview.CustomerViewActivity;
import com.webber.demos.database.GreenDaoActivity;
import com.webber.demos.database.ProviderDemoActivity;
import com.webber.demos.database.realm.RealmDataBasesActivity;
import com.webber.demos.database.room.RoomDateBasesActivity;
import com.webber.demos.example.ControlListActivity;
import com.webber.demos.example.ExampleActivity;
import com.webber.demos.four.FourComponentActivity;
import com.webber.demos.four.shareanim.ShareAnimActivity;
import com.webber.demos.fragmentdemo.FragmentDemoActivity;
import com.webber.demos.google.AdvertiseActivity;
import com.webber.demos.google.GoogleDemoActivity;
import com.webber.demos.media.MediaDemoActivity;
import com.webber.demos.mvvm.DataBindingActivity;
import com.webber.demos.nest.ViewPagerFragmentActivity;
import com.webber.demos.notify.NotificationActivity;
import com.webber.demos.primary.ReflectActivity;
import com.webber.demos.sys.FlashLightActivity;
import com.webber.demos.sys.SystemInfoDemoActivity;
import com.webber.demos.trandfer.TransferActivity;
import com.webber.demos.tv.TvDemosActivity;
import com.webber.demos.update.VersionUpdateActivity;
import com.webber.demos.utils.UtilsDemoActivity;
import com.webber.demos.view.SurfaceViewActivity;
import com.webber.demos.view.ViewDemoActivity;
import com.webber.demos.view.ViewPagerDemoActivity;
import com.webber.demos.view.WebViewActivity;
import com.webber.demos.view.camera.CameraActivity;
import com.webber.demos.weight.BottomSheetActivity;
import com.webber.demos.weight.CoordinatorActivity;
import com.webber.demos.weight.CoordinatorFloatActivity;
import com.webber.demos.weight.RecyclerViewActivity;
import com.webber.demos.weight.SpannableActivity;
import com.webber.demos.weight.ToolBarActivity;
import com.webber.demos.weight.ToolBarDemoActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mContentRlv;
    private DemoInfo[] DEMOS = {
            new DemoInfo("四大组件", "Activity、Service、BroadcastReceiver、ContentProvider", FourComponentActivity.class),
            new DemoInfo("Fragment", "Fragment 生命周期，replace、add", FragmentDemoActivity.class),
            new DemoInfo("各种实例", "应用主页，带侧滑菜单，视差特效", ExampleActivity.class),
            new DemoInfo("Activity转场动画", "转场动画，共享元素", ShareAnimActivity.class),
            new DemoInfo("8.0适配问题", "ContentProvider 传递报错", ProviderDemoActivity.class),
            new DemoInfo("广告权限", "Google 库权限--广告", AdvertiseActivity.class),
            new DemoInfo("锐化、活化问题", "ViewPager 嵌套fragment嵌套ViewPager 的问题", SaveAndRestoreDemoActivity.class),
            new DemoInfo("View学习", "Android控件、自定义控件", ViewDemoActivity.class),
            new DemoInfo("Google全家桶", "语音、支付", GoogleDemoActivity.class),
            new DemoInfo("工具类测试", "虚拟键显示状态", UtilsDemoActivity.class),
            new DemoInfo("获取设备信息", "getSystemInfo", SystemInfoDemoActivity.class),
            new DemoInfo("MediaPlayer", "多媒體資源專題", MediaDemoActivity.class),
            new DemoInfo("数据库相关GreenDao", "GreenDao的增删改查", GreenDaoActivity.class),
            new DemoInfo("数据库相关Room", "Room的增删改查及测试", RoomDateBasesActivity.class),
            new DemoInfo("数据库相关Realm", "Realm的增删改查及测试", RealmDataBasesActivity.class),
            new DemoInfo("版本升级更新", "8.0版本升级", VersionUpdateActivity.class),
            new DemoInfo("Java反射", "Java反射专题", ReflectActivity.class),
            new DemoInfo("注解", "Annotation練習",AnnotationActivity.class),
            new DemoInfo("Android 通讯", "Socket、USB、Bluetooth", TransferActivity.class),
            new DemoInfo("动画专题", "属性动画、布局动画", AnimActivity.class),
            new DemoInfo("相机数据实时显示", "使用SurfaceView显示相机", CameraActivity.class),
            new DemoInfo("TV相关示例", "TV开发相关示例", TvDemosActivity.class),
            new DemoInfo("Fragment", "Fragment这个坑是要填一填", ToolBarActivity.class),
            new DemoInfo("FlashLight", "闪光灯Demo",FlashLightActivity.class),
            new DemoInfo("MVVM", "使用DataBinding",DataBindingActivity.class),
            new DemoInfo("通知相关Demo", "Notify",NotificationActivity.class),
            new DemoInfo("富文本", "富文本练习",SpannableActivity.class),
            new DemoInfo("回车Demo", "list中回车可以添加下一行",ControlListActivity.class),
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

