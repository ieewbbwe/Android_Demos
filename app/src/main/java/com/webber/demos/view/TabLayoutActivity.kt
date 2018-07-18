package com.webber.demos.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.design.widget.TabLayout.GRAVITY_CENTER
import com.webber.demos.R
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayout.tabGravity = GRAVITY_CENTER

        for(i in 1..10){
            tabLayout.addTab(tabLayout.newTab().setText("title"+i))
        }
    }
}
