package com.webber.demos.database.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by picher on 2018/5/25.
 * Describe：
 */
@Database(entities = {UserRoom.class},version = 1,exportSchema = false)
@TypeConverters({DateConverts.class})
public abstract class AndroidDataBases extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile AndroidDataBases INSTANCE;

    public static AndroidDataBases crateDatabases(Context context) {
        if (INSTANCE == null) {
            synchronized (AndroidDataBases.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AndroidDataBases.class, "room.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static AndroidDataBases getInstance(){
        return INSTANCE;
    }
}
