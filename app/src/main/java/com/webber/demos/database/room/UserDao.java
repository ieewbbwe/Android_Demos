package com.webber.demos.database.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.databinding.ObservableList;

import com.webber.demos.mvvm.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by picher on 2018/5/25.
 * Describe：
 */
@Dao
public interface UserDao {

    @Insert
    Long insertUser(UserRoom user);

    @Query("SELECT * FROM user_tb")
    Flowable<List<UserRoom>> queryAll();

    @Query("SELECT * FROM user_tb")
    List<UserRoom> queryForAll();

    @Query("SELECT * FROM user_tb WHERE name = :name")
    int deleteByName(String name);

    @Query("SELECT * FROM user_tb WHERE datetime(birthday/1000, 'unixepoch') >= datetime('now','-1 hour') ")
    Flowable<List<UserRoom>> queryWithinHour();

   /* @Query("SELECT * FROM user_tb")
    Observable<List<UserRoom>> queryForAllObs();*/
}
