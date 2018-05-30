package com.webber.demos.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.webber.demos.R;
import com.webber.demos.database.room.AndroidDataBases;
import com.webber.demos.database.room.UserDao;
import com.webber.demos.database.room.UserRoom;

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

public class GreenDaoActivity extends AppCompatActivity implements GreenDaoContract.View {

    private Button insertBt;
    private Button searchBt;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        insertBt = findViewById(R.id.insetBt);
        searchBt = findViewById(R.id.searchBt);
        AndroidDataBases.getInstance().getOpenHelper().getReadableDatabase().getPath();
        userDao = AndroidDataBases.getInstance().userDao();
        insertBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Completable.fromAction(new Action() {
                @Override
                public void run() throws Exception {
                    UserRoom user = new UserRoom("admin", 25,new Date(System.currentTimeMillis()));
                    long raw = userDao.insertUser(user);
                    Log.d("picher","插入完成："+raw);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action() {
                        @Override
                        public void run() throws Exception {
                            Log.d("picher","插入成功");
                        }
                    });
            }
        });

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //userDao.queryForAll();
                Flowable.just("1").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.d("picher","RXjieshou");
                            }
                        });
                userDao.queryAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                       .map(new Function<List<UserRoom>, String>() {
                           @Override
                           public String apply(List<UserRoom> userRooms) throws Exception {
                               return userRooms.size() + "";
                           }
                       }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("picher","数据库数："+s);
                    }
                });
            }
        });
    }

    @Override
    public void showData() {

    }

    @Override
    public void refreshData() {

    }
}
