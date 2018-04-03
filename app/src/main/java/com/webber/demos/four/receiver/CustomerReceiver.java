package com.webber.demos.four.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by picher on 2018/4/3.
 * Describe：
 */

public class CustomerReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("picher","onReceiver():"+intent.getAction());
    }

}
