package com.webber.demos.database

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.webber.demos.R
import kotlinx.android.synthetic.main.activity_provider_demo.*

class ProviderDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_demo)
        mTestBt.setOnClickListener({
            // ActiveAndroid
            //demo01()
            demo02()
        })
    }

    private fun demo02() {
        Log.d("picher",(5-1
                -2).toString())
    }

    private fun demo01() {
        val articleDBItem = ArticleDBItem()
        articleDBItem.mlArticleId = "test"
        articleDBItem.isRead = 1
        try {
            articleDBItem.save()
        } catch (e: SecurityException) {
            Log.d("picher",e.message)
        }
    }
}
