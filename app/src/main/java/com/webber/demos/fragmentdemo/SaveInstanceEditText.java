package com.webber.demos.fragmentdemo;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.webber.demos.R;

/**
 * Created by picher on 2017/12/28.
 * Describe：
 */

public class SaveInstanceEditText extends EditText {
    public SaveInstanceEditText(Context context) {
        super(context);
    }

    public SaveInstanceEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SaveInstanceEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Parcelable onSaveInstanceState() {

        return new SaveState(getText().toString().trim());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        Log.d("picher","onRestoreInstanceState");
        if(state instanceof SaveState){
            setText(((SaveState) state).getEditStr());
            Toast.makeText(getContext(),((SaveState) state).getEditStr(),Toast.LENGTH_SHORT).show();
        }
        super.onRestoreInstanceState(state);
    }

    private class SaveState implements Parcelable{
        private String editStr;

        public String getEditStr() {
            return editStr;
        }

        public void setEditStr(String editStr) {
            this.editStr = editStr;
        }

        public SaveState(String editStr) {
            this.editStr = editStr;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.editStr);
        }

        public SaveState() {
        }

        protected SaveState(Parcel in) {
            this.editStr = in.readString();
        }

        public final Creator<SaveState> CREATOR = new Creator<SaveState>() {
            @Override
            public SaveState createFromParcel(Parcel source) {
                return new SaveState(source);
            }

            @Override
            public SaveState[] newArray(int size) {
                return new SaveState[size];
            }
        };
    }
}
