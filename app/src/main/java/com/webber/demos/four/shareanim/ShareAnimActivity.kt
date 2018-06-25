package com.webber.demos.four.shareanim

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Pair
import android.view.View
import com.webber.demos.R
import kotlinx.android.synthetic.main.activity_shere_anim.*

class ShareAnimActivity : AppCompatActivity(), View.OnClickListener {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.openExplain -> openByAnim(EntryAnimActivity.OpenStyle.EXPLAIN)
            R.id.openSlide -> openByAnim(EntryAnimActivity.OpenStyle.SLIDE)
            R.id.openFade -> openByAnim(EntryAnimActivity.OpenStyle.FADE)
            R.id.shareBt1 -> openByShare(v)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun openByShare(v: View?) {
        startActivity(Intent(this, ShareElementActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this,
                        Pair.create(v,"mShareBt1"),
                        Pair.create(v,"mShareBt2")
                ).toBundle())
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun openByAnim(openStyle: EntryAnimActivity.OpenStyle) {
        val intent = Intent(this, EntryAnimActivity::class.java)
        intent.putExtra("openStyle",openStyle.name)
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shere_anim)
        openExplain.setOnClickListener(this)
        openSlide.setOnClickListener(this)
        openFade.setOnClickListener(this)
        shareBt1.setOnClickListener(this)
    }
}
