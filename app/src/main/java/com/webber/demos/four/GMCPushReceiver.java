package com.webber.demos.four;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by picher on 2017/11/3.
 * Describe：
 */

public class GMCPushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("webber", "" + intent.getAction());
        Log.d("webber", "" + intent.getExtras().getString("bundle1"));
        Log.d("webber", "" + intent.getExtras().getInt("bundle2"));

    }
}
