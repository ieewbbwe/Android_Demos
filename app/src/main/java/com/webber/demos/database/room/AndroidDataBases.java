package com.webber.demos.database.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by picher on 2018/5/25.
 * Describe：
 */
@Database(entities = {UserRoom.class},version = 4,exportSchema = false)
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
                            .addMigrations(MIGRATION_2_3,MIGRATION_3_4)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user_tb ADD COLUMN addInt INTEGER");
        }
    };

    private static Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user_tb ADD COLUMN addString1 TEXT");
        }
    };

    public static AndroidDataBases getInstance(){
        return INSTANCE;
    }
}
