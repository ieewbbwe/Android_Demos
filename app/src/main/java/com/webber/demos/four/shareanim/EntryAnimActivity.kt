package com.webber.demos.four.shareanim

import android.annotation.SuppressLint
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import com.webber.demos.R

class EntryAnimActivity : AppCompatActivity() {

    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_anim)

        val openStyle = intent.getStringExtra("openStyle")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when (openStyle) {
                OpenStyle.EXPLAIN.name -> {
                    // 分解打开
                    window.enterTransition = Explode().setDuration(2000)
                    window.exitTransition = Explode().setDuration(2000)
                }
                OpenStyle.SLIDE.name -> {
                    // 滑动打开
                    window.enterTransition = Slide(Gravity.LEFT).setDuration(2000)
                    window.exitTransition = Slide(Gravity.LEFT).setDuration(2000)
                }
                OpenStyle.FADE.name -> {
                    // 渐变打开
                    window.enterTransition = Fade().setDuration(2000)
                    window.exitTransition = Fade().setDuration(2000)
                }
            }
        }

    }

    enum class OpenStyle{
        EXPLAIN,SLIDE,FADE
    }
}
