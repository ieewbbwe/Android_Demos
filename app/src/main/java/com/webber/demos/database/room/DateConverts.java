package com.webber.demos.database.room;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by picher on 2018/5/24.
 * Describe：Use for save type Data https://juejin.im/entry/591d41c70ce463006923f937
 */
public class DateConverts {
    @TypeConverter
    public static Date revertDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long converterDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
