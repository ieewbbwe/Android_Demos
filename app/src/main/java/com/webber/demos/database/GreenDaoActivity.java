package com.webber.demos.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.webber.demos.R;

public class GreenDaoActivity extends AppCompatActivity implements GreenDaoContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
    }

    @Override
    public void showData() {

    }

    @Override
    public void refreshData() {

    }
}
