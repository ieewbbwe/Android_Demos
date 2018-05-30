package com.webber.demos.database;

import java.util.Date;

/**
 * Created by picher on 2018/2/6.
 * Describe：
 */

public class User {
    private String name;
    private int age;
    private boolean gender;
    private long insertTime;

    public User() {
    }


    public User(String name, int age, boolean gender, long insertTime) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.insertTime = insertTime;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
