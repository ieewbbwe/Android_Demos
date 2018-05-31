package com.webber.demos.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.webber.demos.BR;
import com.webber.demos.R;

public class DataBindingActivity extends AppCompatActivity {

    private ViewDataBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_bindding);
        dataBinding.setVariable(BR.user,new User("picher","24"));
        dataBinding.setVariable(BR.person,new User("person","24"));

    }
}
