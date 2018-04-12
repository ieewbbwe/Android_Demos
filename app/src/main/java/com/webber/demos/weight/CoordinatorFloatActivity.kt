package com.webber.demos.weight

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import com.webber.demos.R
import com.webber.demos.adapter.TextAdapter
import kotlinx.android.synthetic.main.activity_coordinator_flot.*

class CoordinatorFloatActivity : AppCompatActivity() {

    var mAdapter:TextAdapter? = null
    var listData:MutableList<String>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator_flot)
        for(i in 1..10){
            val tab:TabLayout.Tab = m_float_tab_tl.newTab()
            tab.text = "标签" + i
            m_float_tab_tl.addTab(tab)
        }

        mAdapter = TextAdapter()
        m_data_rv.adapter = mAdapter
        m_data_rv.layoutManager = LinearLayoutManager(this)
        for(i in 1..50){
            listData!!.add(i.toString())
        }
        mAdapter!!.setDatas(listData)
    }


}

