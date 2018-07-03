package com.webber.demos.database.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by picher on 2018/6/29.
 * Describe：
 */

@Entity
public class UserGreen {

    @Id(autoincrement = true)
    private Long id;

    private String name;
    private Date birthday;
    private int age;

    public UserGreen() {
    }

    public UserGreen(String name, Date birthday, int age) {
        this.name = name;
        this.birthday = birthday;
        this.age = age;
    }

    @Generated(hash = 1796632767)
    public UserGreen(Long id, String name, Date birthday, int age) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
