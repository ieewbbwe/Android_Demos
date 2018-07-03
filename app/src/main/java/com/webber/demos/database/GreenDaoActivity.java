package com.webber.demos.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.webber.demos.R;
import com.webber.demos.database.greendao.GreenDaoManager;
import com.webber.demos.database.greendao.UserGreen;
import com.webber.demos.database.greendao.UserGreenDao;
import com.webber.demos.database.room.UserDao;

import java.util.Date;
import java.util.List;

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

        insertBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //简单插入
                insertDemo1();
            }
        });

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //简餐查询
                searchDemo1();
            }
        });

    }

    private void searchDemo1() {
        UserGreenDao dao = GreenDaoManager.daoSession.getUserGreenDao();
        // 查询所有
        List<UserGreen> userGreenDaoDaos = dao.loadAll();
        Log.d("picher","GreenDao查询所有：" + new Gson().toJson(userGreenDaoDaos));
        // 单一条件查询
        UserGreen user = dao.queryBuilder().where(UserGreenDao.Properties.Name.eq("picher")).limit(1).unique();
        Log.d("picher","单一条件查询：" + new Gson().toJson(user));
        // 模糊查询
        UserGreen userLike = dao.queryBuilder().where(UserGreenDao.Properties.Name.like("pi%")).limit(1).unique();
        Log.d("picher","模糊查询：" + new Gson().toJson(userLike));
        // SQL 语句查询
        List<UserGreen> userGreens = dao.queryRaw(" where name = ?", "%ich%");
        Log.d("picher","语句查询：" + new Gson().toJson(userGreens));
    }

    private void insertDemo1() {
        UserGreen userGreen = new UserGreen("picher",new Date(System.currentTimeMillis()),18);
        GreenDaoManager.daoSession.getUserGreenDao().insert(userGreen);
    }

    @Override
    public void showData() {

    }

    @Override
    public void refreshData() {

    }
}
