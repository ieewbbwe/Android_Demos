package com.webber.demos.database.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Intent;

import java.util.Date;

/**
 * Created by picher on 2018/5/25.
 * Describe：
 */
@Entity(tableName = "user_tb")
public class UserRoom {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo
    private int age;
    @ColumnInfo
    private Date birthday;
    @Embedded(prefix = "add1_")
    EmbedObject obj;
    @ColumnInfo
    private String addString1;
    @ColumnInfo
    private Integer addInt;

    public Integer getAddInt() {
        return addInt;
    }

    public void setAddInt(Integer addInt) {
        this.addInt = addInt;
    }

    public String getAddString1() {
        return addString1;
    }

    public void setAddString1(String addString1) {
        this.addString1 = addString1;
    }

    public EmbedObject getObj() {
        return obj;
    }

    public void setObj(EmbedObject obj) {
        this.obj = obj;
    }

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
