package com.webber.demos.database.room;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.reactivestreams.Publisher;

import java.util.Date;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by picher on 2018/5/25.
 * Describe：
 */
public class UserDaoTest {
    private static UserRoom userRoom = new UserRoom("static",25,new Date(System.currentTimeMillis()));

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao = AndroidDataBases.getInstance().userDao();
    }

    @After
    public void tearDown() throws Exception {
        AndroidDataBases.getInstance().close();
    }

    @Test
    public void insertUser() throws Exception {
        for(int i=0;i<10;i++){
            Completable.fromAction(new Action() {
                @Override
                public void run() throws Exception {
                    userDao.insertUser(new UserRoom("aaa",14,new Date(System.currentTimeMillis())));
                }
            }).subscribeOn(Schedulers.io());
        }
    }

    @Test
    public void queryAll() throws Exception {
        userDao.queryAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<UserRoom>>() {
                    @Override
                    public void accept(List<UserRoom> userRooms) throws Exception {
                        Log.d("picher","User数："+userRooms.size());
                    }
                });
    }

    @Test
    public void queryForAll() throws Exception {
        inserData().subscribeOn(Schedulers.io()).flatMap(new Function<Long, Publisher<List<UserRoom>>>() {
            @Override
            public Publisher<List<UserRoom>> apply(Long aLong) throws Exception {
                return userDao.queryAll();
            }
        }).subscribe(new Consumer<List<UserRoom>>() {
            @Override
            public void accept(List<UserRoom> userRooms) throws Exception {
                Log.d("picher","數據庫數據："+userRooms.size());
                for(UserRoom userRoom:userRooms){
                    Log.d("picher","數據庫數據："+userRoom.getName());
                }
            }
        });
    }

    public Flowable<Long> inserData(){
        return Flowable.create(new FlowableOnSubscribe<Long>() {
            @Override
            public void subscribe(FlowableEmitter<Long> e) throws Exception {
                e.onNext(userDao.insertUser(userRoom));
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR);
    }

}