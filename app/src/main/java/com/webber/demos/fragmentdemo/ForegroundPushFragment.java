package com.webber.demos.fragmentdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.webber.demos.R;

public class ForegroundPushFragment extends DialogFragment {

    private ForegroundPushMessage message;

    public static ForegroundPushFragment create(ForegroundPushMessage message) {
        ForegroundPushFragment fragment = new ForegroundPushFragment();
        fragment.setMessage(message);
        return fragment;
    }

    public void setMessage(ForegroundPushMessage message) {
        this.message = message;
    }

    public ForegroundPushMessage getMessage() {
        return message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                   /* final Intent deeplinkIntent = UrbanAirshipManager.createDeeplinkIntent(message.getDeeplink());
                    deeplinkIntent.setComponent(new ComponentName(getContext(), SplashScreenActivity.class));
                    startActivity(deeplinkIntent);
                    getActivity().finish();*/
                }

            }
        };
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .setNegativeButton("取消", listener)
                .setPositiveButton("前往", listener);
        if (message.getLoadedImage() != null) {
            final View contentView = LayoutInflater.from(getContext()).inflate(R.layout.notificaiton_big_image_expanded, null);
            final ImageView imageView = (ImageView) contentView.findViewById(R.id.custom_image);
            imageView.setImageBitmap(message.getLoadedImage());
            final TextView textView = (TextView) contentView.findViewById(R.id.custom_text);
            textView.setText(message.getMessage());
            final View titleView = LayoutInflater.from(getContext()).inflate(R.layout.notification_foreground_title, null);
            final TextView titleText = (TextView) titleView.findViewById(R.id.text_title);
            titleText.setText(TextUtils.isEmpty(message.getTitle()) ? getString(R.string.app_name) : message.getTitle());
            builder.setCustomTitle(titleView)
                    .setView(contentView);
        } else {
            builder.setTitle(TextUtils.isEmpty(message.getTitle()) ? getString(R.string.app_name) : message.getTitle())
                    .setMessage(message.getMessage());
        }

        return builder.create();
    }

    public static class ForegroundPushMessage implements Parcelable {

        private static final String KEY_FOREGROUND_PUSH_DATA = "foregound_push_message";

        protected ForegroundPushMessage(Parcel in) {
            title = in.readString();
            message = in.readString();
            deeplink = in.readString();
            imageUrl = in.readString();
            loadedImage = in.readParcelable(Bitmap.class.getClassLoader());
        }

        public static final Creator<ForegroundPushMessage> CREATOR = new Creator<ForegroundPushMessage>() {
            @Override
            public ForegroundPushMessage createFromParcel(Parcel in) {
                return new ForegroundPushMessage(in);
            }

            @Override
            public ForegroundPushMessage[] newArray(int size) {
                return new ForegroundPushMessage[size];
            }
        };

        public static ForegroundPushMessage fromBundle(final Bundle bundle) {
            if (bundle != null && bundle.containsKey(KEY_FOREGROUND_PUSH_DATA)) {
                return ((ForegroundPushMessage) bundle.get(KEY_FOREGROUND_PUSH_DATA));
            }

            return null;
        }

        private final String title;
        private final String message;
        private final String deeplink;
        private final String imageUrl;
        private Bitmap loadedImage;

        ForegroundPushMessage(String title, String message, String deeplink, String imageUrl, Bitmap loadedImage) {
            this.title = title;
            this.message = message;
            this.deeplink = deeplink;
            this.imageUrl = imageUrl;
            this.loadedImage = loadedImage;
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return message;
        }

        public String getDeeplink() {
            return deeplink;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public Bitmap getLoadedImage() {
            return loadedImage;
        }

        public void setLoadedImage(Bitmap loadedImage) {
            this.loadedImage = loadedImage;
        }

        public Bundle toBundle() {
            final Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_FOREGROUND_PUSH_DATA, this);
            return bundle;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(message);
            dest.writeString(deeplink);
            dest.writeString(imageUrl);
            dest.writeParcelable(loadedImage, flags);
        }
    }

}
