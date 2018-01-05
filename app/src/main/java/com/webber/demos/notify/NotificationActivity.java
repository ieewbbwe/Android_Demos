package com.webber.demos.notify;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RemoteViews;

import com.webber.demos.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        findViewById(R.id.m_send_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotify();
            }
        });
    }

    private void sendNotify() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.notify_layout);
        remoteViews.setImageViewBitmap(R.id.m_notify_iv, convertBtm(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_169)));
        remoteViews.setTextViewText(R.id.m_notify_info_tv, "我是一行通知的文本消息！！！\n我是第二行通知的消息！！");

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

        Bitmap bitmapFromURL = convertBtm(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
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
