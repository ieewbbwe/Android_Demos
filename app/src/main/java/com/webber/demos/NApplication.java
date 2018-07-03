package com.webber.demos;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import com.webber.demos.database.ActiveAndroidManager;
import com.webber.demos.database.greendao.DaoMaster;
import com.webber.demos.database.greendao.DaoSession;
import com.webber.demos.database.greendao.GreenDaoManager;
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

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

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

        //setDatabase();
        GreenDaoManager.init(this);
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "sport-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
