package com.webber.demos.mvvm;

import android.arch.persistence.room.Ignore;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.webber.demos.BR;

/**
 * Created by picher on 2017/11/29.
 * Describe：
 */

public class User extends BaseObservable {

    private String name;
    private String age;

    public User() {
    }

    @Ignore
    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
    @Bindable
    public String getName() {
        return name;
    }
    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.name);
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.age);
    }

    public void setData(View v){
        setName("change");
    }

    public void print(View v){
        Log.d("picher",String.format("name:%s-->> age:%s",name,age));
    }
}
