package com.webber.demos.database;

import android.content.Context;
import android.provider.SyncStateContract;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.Configuration;
import com.activeandroid.serializer.UtilDateSerializer;


public class ActiveAndroidManager {

    private static final ActiveAndroidManager ourInstance = new ActiveAndroidManager();


    public static ActiveAndroidManager getInstance() {
        return ourInstance;
    }

    private ActiveAndroidManager() {
    }


    public void init(Context context) {
        Configuration dbConfiguration =
                new Configuration.Builder(context)
                        .setDatabaseName("android_demo")
                        .setDatabaseVersion(1)
                        .addModelClass(ArticleDBItem.class)
                        .addTypeSerializer(UtilDateSerializer.class)
                        .create();
        ActiveAndroid.initialize(dbConfiguration, true);
    }

    public boolean isInit() {
        return Cache.isInitialized();
    }

    public void onTerminate() {
        ActiveAndroid.dispose();
    }

}
