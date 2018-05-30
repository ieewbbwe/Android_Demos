package com.webber.demos;

import android.app.Application;
import android.util.Log;

import com.webber.demos.database.ActiveAndroidManager;
import com.webber.demos.database.room.AndroidDataBases;

/**
 * Created by picher on 2018/5/23.
 * Describe：
 */

public class NApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("picher","---Application OnCrate---");
        ActiveAndroidManager.getInstance().init(this);
        AndroidDataBases.crateDatabases(this);
    }
}
