package com.webber.demos.database.realm;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by picher on 2018/6/4.
 * Describe：
 */

public class Book extends RealmObject {

    public String id = UUID.randomUUID().toString();
    public String name;
    public Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
