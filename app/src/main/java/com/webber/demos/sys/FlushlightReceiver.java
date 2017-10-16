package com.webber.demos.sys;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import com.webber.demos.MainActivity;
import com.webber.demos.R;

/**
 * Created by picher on 2017/10/16.
 * Describe：
 */

public class FlushLightReceiver extends BroadcastReceiver {
    public static final String FLUSHLIGHT_ACTION = "com.webber.demos.sys.flushLight";

    @Override
    public void onReceive(Context context, Intent intent) {
        int repeat = intent.getIntExtra("repeat",0);
        Log.d("picher","閃爍次數："+repeat);
        notification(context);
    }

    private void notification(Context context) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentInfo("补充内容");
        builder.setContentText("主内容区");
        builder.setContentTitle("通知标题");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("新消息");
        builder.setAutoCancel(true);

        builder.setWhen(System.currentTimeMillis());//设置时间，设置为系统当前的时间
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        /**
         * vibrate属性是一个长整型的数组，用于设置手机静止和振动的时长，以毫秒为单位。
         * 参数中下标为0的值表示手机静止的时长，下标为1的值表示手机振动的时长， 下标为2的值又表示手机静止的时长，以此类推。
         */
        long[] vibrates = { 0, 1000, 1000, 1000 };
        notification.vibrate = vibrates;

        /**
         * 手机处于锁屏状态时， LED灯就会不停地闪烁， 提醒用户去查看手机,下面是绿色的灯光一 闪一闪的效果
         */
        notification.ledARGB = Color.GREEN;// 控制 LED 灯的颜色，一般有红绿蓝三种颜色可选
        notification.ledOnMS = 1000;// 指定 LED 灯亮起的时长，以毫秒为单位
        notification.ledOffMS = 1000;// 指定 LED 灯暗去的时长，也是以毫秒为单位
        notification.flags = Notification.FLAG_SHOW_LIGHTS;// 指定通知的一些行为，其中就包括显示
        // LED 灯这一选项
//                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//                notification.sound = uri;
//                notification.defaults = Notification.DEFAULT_ALL;
        manager.notify(1, notification);
    }
}
