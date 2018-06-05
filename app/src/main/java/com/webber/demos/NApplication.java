package com.webber.demos;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import com.webber.demos.database.ActiveAndroidManager;
import com.webber.demos.database.realm.Migration;
import com.webber.demos.database.room.AndroidDataBases;

import java.io.FileNotFoundException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import io.realm.rx.RxObservableFactory;

/**
 * Created by picher on 2018/5/23.
 * Describe：
 */

public class NApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this) ;
        Log.d("picher","---Application OnCrate---");
        ActiveAndroidManager.getInstance().init(this);
        AndroidDataBases.crateDatabases(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("realm_demo.realm")
                .rxFactory(new RealmObservableFactory())
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
        try {
            Realm.migrateRealm(config, new Migration());
        } catch (FileNotFoundException ignored) {
            // If the Realm file doesn't exist, just ignore.
        }

        Stetho.initialize(//Stetho初始化
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build()
        );
    }
}
