package com.webber.demos.database.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by picher on 2018/6/29.
 * Describe：
 */

public class GreenDaoManager {
    private static final java.lang.String DATABASES_NAME = "greendao_db";
    public static DaoSession daoSession;

    private GreenDaoManager(){}

    private static GreenDaoManager mInstance;

    public static GreenDaoManager getInstance(){
        if(mInstance == null){
            synchronized (GreenDaoManager.class){
                if(mInstance == null){
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public static void init(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASES_NAME,null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
