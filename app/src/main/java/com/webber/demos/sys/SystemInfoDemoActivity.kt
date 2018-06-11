package com.webber.demos.sys

import android.content.Context
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.TextView
import com.bana.libcore.core.AbstractDialogFragment
import com.webber.demos.R
import com.webber.demos.view.TestDialog
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.activity_system_info_demo.*
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION
import android.os.Build.VERSION.SDK_INT
import android.view.View


class SystemInfoDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
        setContentView(R.layout.activity_system_info_demo)
        val gsfid = getGsfAndroidId(this)
        Log.d("picher",""+gsfid)
        m_gsf_id.text = gsfid
        /*val mGsfIdTv = findViewById<TextView>(R.id.m_gsf_id)
        mGsfIdTv.text = gsfid*/
        m_test_id.setOnClickListener({
            var dialog:TestDialog = TestDialog()
            dialog.show(supportFragmentManager,"dialog")
        })
    }

    private fun getGsfAndroidId(context: Context): String? {
        val URI = Uri.parse("content://com.google.android.gsf.gservices")
        val ID_KEY = "android_id"
        val params = arrayOf(ID_KEY)
        val c = context.contentResolver.query(URI, null, null, params, null)
        if (!c!!.moveToFirst() || c.columnCount < 2)
            return null
        try {
            return java.lang.Long.toHexString(java.lang.Long.parseLong(c.getString(1)))
        } catch (e: NumberFormatException) {
            return null
        }

    }
}
