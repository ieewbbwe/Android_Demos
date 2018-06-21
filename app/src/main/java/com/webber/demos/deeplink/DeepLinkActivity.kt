package com.webber.demos.deeplink

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.webber.demos.R
import kotlinx.android.synthetic.main.activity_deep_link.*

class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link)
        initData()
    }

    private fun initData() {
        val uri = intent.data
        Log.d("picher",uri.toString())
        deepLinkTv.text = uri.toString()
    }
}
