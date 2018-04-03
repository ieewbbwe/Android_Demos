package com.webber.demos.notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.webber.demos.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        findViewById(R.id.m_send_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotifyDemo();
            }
        });

        findViewById(R.id.m_send_big_image_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBigImageDemo();
            }
        });

        findViewById(R.id.m_send_not_channel_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWithNotChannel();
            }
        });
        findViewById(R.id.m_send_remote_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRemote();
            }
        });
    }

    private void sendRemote() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notifyId = 1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置左上角小图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_16_18));//右侧图片
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentTitle("This is Content Title~");//设置内容信息
        builder.setContentInfo("This is Content Info~");//8.0 上无效

        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.notify_layout);
        remoteViews.setImageViewBitmap(R.id.m_notify_iv, convertBtm(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_169)));
        remoteViews.setTextViewText(R.id.m_notify_info_tv, "我是一行通知的文本消息！！！\n我是第二行通知的消息！！");
        builder.setCustomBigContentView(remoteViews);

        notificationManager.notify(notifyId,builder.build());

    }

    private void sendWithNotChannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notifyId = 1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("This is Content Title~");//设置内容信息
        builder.setContentInfo("This is Content Info~");//8.0 上无效

        builder.setOngoing(true);//禁止滑动删除
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置左上角小图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_16_18));//右侧图片
        //builder.setDefaults(Notification.DEFAULT_SOUND);//设置默认声音
        builder.setVibrate(new long[]{100,200,100,500,500});//设置震动 {间隔，时长，间隔，时长...}
        builder.setLights(Color.BLUE,1000,5000);//设置呼吸灯颜色 亮时间 暗时间 有些机型不支持
        notificationManager.notify(notifyId,builder.build());
    }

    private void sendBigImageDemo() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //创建channel
                NotificationChannel notificationChannel = new NotificationChannel("picher2", "channel02_name"
                        , NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"picher2");
            //builder.setContentTitle("This is Content Title~");//设置内容信息
            //builder.setContentInfo("This is Content Info~");//8.0 上无效
            builder.setSmallIcon(R.mipmap.ic_launcher);//设置左上角小图标
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_16_18));//右侧图片
            NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
            style.bigText("这里是点击通知后要显示的正文，可以换行可以显示很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
            style.setBigContentTitle("点击后的标题");
            //SummaryText没什么用 可以不设置
            style.setSummaryText("末尾只一行的文字内容");

            builder.setStyle(style);
            int notifyId = 2;

            notificationManager.notify(notifyId, builder.build());

        }
    }

    private void sendNotifyDemo(){
        sendSimpleText();
        //sendTextWithIcon();
        //sendBigImgIcon();
    }

    //发送简单的一条信息 适配8.0 channel
    // IMPORTANCE_HIGH 发出提示音 该参数已用户配置的为优先
    private void sendSimpleText() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(notificationManager != null){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                //创建channel
                NotificationChannel notificationChannel = new NotificationChannel("channel01","channel01_name"
                        ,NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.enableLights(true);//是否允许桌面小红点， 8.0 上无效？
                notificationChannel.setLightColor(Color.RED);//桌面红点颜色
                notificationChannel.setSound(null,null);
                Toast.makeText(this,"创建",Toast.LENGTH_SHORT).show();
                notificationManager.createNotificationChannel(notificationChannel);
            }

            int notifyId = 1;
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"channel01");
            builder.setContentTitle("This is Content Title~");//设置内容信息
            builder.setContentInfo("This is Content Info~");//8.0 上无效

            builder.setOngoing(true);//禁止滑动删除
            builder.setSmallIcon(R.mipmap.ic_launcher);//设置左上角小图标
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_16_18));//右侧图片
            builder.setDefaults(Notification.DEFAULT_SOUND);//设置默认声音
            builder.setVibrate(new long[]{100,200,100,500,500});//设置震动 {间隔，时长，间隔，时长...}
            builder.setLights(Color.BLUE,1000,5000);//设置呼吸灯颜色 亮时间 暗时间 有些机型不支持
            builder.setChannelId("channel01");//设置通道，8.0 以上有用
            notificationManager.notify(notifyId,builder.build());
        }
    }


    private void sendNotify() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.notify_layout);
        remoteViews.setImageViewBitmap(R.id.m_notify_iv, convertBtm(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_169)));
        remoteViews.setTextViewText(R.id.m_notify_info_tv, "我是一行通知的文本消息！！！\n我是第二行通知的消息！！");

        builder.setDefaults(Notification.DEFAULT_SOUND);

        //setBigPictureProperties(builder, "notify", this, createResizedBitmapNew(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_1618)));
        setBigPictureProperties(builder, "notify", this, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_1618));
        //setRegularPushProperties(builder,"notify",this);
        //setCommonBuilderProperties(builder,"notify",this);
        notificationManager.notify(1, builder.build());
    }

    private Bitmap createResizedBitmapNew(Bitmap bitmap) {
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        float newWidth = getResources().getDisplayMetrics().widthPixels;
        float newHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 256,getResources().getDisplayMetrics());

        if (width > height) {
            if ((newWidth / width) * height <= newHeight) {
                newHeight = (newWidth / width) * height;
            }
        } else {
            if ((newHeight / height) * width <= newWidth) {
                newWidth = (newHeight / height) * width;
            }
        }

        return Bitmap.createScaledBitmap(bitmap, (int) newWidth, (int) newHeight, true);
    }

    public void setBigPictureProperties(NotificationCompat.Builder builder, String alert, Context context, Bitmap resizedImage) {
        final NotificationCompat.Style style = new NotificationCompat.BigPictureStyle().setSummaryText(alert);
        builder.setStyle(style);

        final RemoteViews collapsedView = new RemoteViews(context.getPackageName(), R.layout.notification_big_image_collapsed);
        collapsedView.setTextViewText(R.id.custom_text, alert);
        collapsedView.setImageViewBitmap(R.id.custom_image, resizedImage);
        builder.setContent(collapsedView);

        final RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.notificaiton_big_image_expanded);
        expandedView.setTextViewText(R.id.custom_text, alert);
        expandedView.setImageViewBitmap(R.id.custom_image, resizedImage);
        builder.setCustomBigContentView(expandedView);
        setCommonBuilderProperties(builder, alert, context);
    }
    private Bitmap createResizedBitmap(Bitmap bitmap) {
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        float size = getResources().getDisplayMetrics().widthPixels;
        float newHeight = (size / width) * height;
        return Bitmap.createScaledBitmap(bitmap, (int) size, (int) newHeight, true);
    }



    public void setRegularPushProperties(final NotificationCompat.Builder builder, final String alert, final Context context) {
        if (builder == null) {
            return;
        }

        final NotificationCompat.Style style = new NotificationCompat.BigTextStyle().bigText(alert);
        builder.setStyle(style);
        setCommonBuilderProperties(builder, alert, context);
    }


    private void setCommonBuilderProperties(final NotificationCompat.Builder builder, final String alert, final Context context) {
        if (builder == null) {
            return;
        }

        builder.setAutoCancel(true);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        builder.setContentText(alert);

        Bitmap bitmapFromURL = convertBtm(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_16_18));
        if (bitmapFromURL != null) {
            NotificationCompat.BigPictureStyle bigPicStyle = new NotificationCompat.BigPictureStyle();
            bigPicStyle.bigPicture(bitmapFromURL);
            bigPicStyle.setBigContentTitle(alert);
            builder.setStyle(bigPicStyle);
        }

        builder.setPriority(NotificationCompat.PRIORITY_MAX);
    }


    private Bitmap convertBtm(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int screenW = getResources().getDisplayMetrics().widthPixels;
        int screenH = getResources().getDisplayMetrics().heightPixels;
        float scaleW = (float) screenW / (float) width;
        float scaleH = (float) (scaleW * height) / (float) height;
        Log.d("picher", String.format("w:%s,h:%s,sW:%s,sH:%s,scale:%s", width, height, screenW, screenH, scaleW));
        int realW = screenW;
        int realH = height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleW, scaleW);

        //return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return Bitmap.createScaledBitmap(bitmap,screenW, (int) (scaleW*height), true);
    }

    public int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

}
