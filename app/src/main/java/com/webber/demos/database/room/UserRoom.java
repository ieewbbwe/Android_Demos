package com.webber.demos.database.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by picher on 2018/5/25.
 * Describe：
 */
@Entity(tableName = "user_tb")
public class UserRoom {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int age;
    @ColumnInfo
    private Date birthday;

    @Ignore
    public UserRoom(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public UserRoom() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
