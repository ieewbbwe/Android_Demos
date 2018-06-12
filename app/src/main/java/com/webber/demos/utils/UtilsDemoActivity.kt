package com.webber.demos.utils

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.webber.demos.R
import kotlinx.android.synthetic.main.activity_utils_demo.*
import java.lang.reflect.AccessibleObject.setAccessible
import android.util.DisplayMetrics
import android.view.*
import android.widget.FrameLayout


private val runnable = Runnable {
    var mLayoutComplete = true
    Log.e("picher", "content 布局完成")
}

class UtilsDemoActivity : AppCompatActivity() {
    var mLayoutComplete = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utils_demo)
        virtualBarCheck.setOnClickListener({
            Log.d("picher", "虚拟键是否显示：" + checkVirtualBar())
            Log.d("picher", "虚拟键高度：" + getNavigationBarHeight(this))
        })
        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {
            Log.d("picher","decorH:"+window.decorView.height)
            Log.d("picher","hasH:"+getHasVirtualKey())
            Log.d("picher","状态栏高度:"+getResources().getIdentifier("status_bar_height", "dimen", "android"))
            Log.d("picher","状态栏高度:"+getStatueBarHeight())
            Log.d("picher","screenH:"+getScreenHeight(this))
        }

        var content = findViewById<View>(android.R.id.content) as FrameLayout
        content.post({
            mLayoutComplete = true
            Log.d("picher", "content 布局完成")
        })
        content.viewTreeObserver.addOnGlobalLayoutListener({
            Log.d("picher", "布局回掉")
            if (mLayoutComplete){
                Log.d("picher", "布局高度："+content.height)
            }
        })

    }

    fun getStatueBarHeight():Int{
        var statusBarHeight2 = -1
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(`object`).toString())
            statusBarHeight2 = resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return statusBarHeight2
    }

    fun getScreenHeight(context: Context): Int {
        val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }
    private fun getHasVirtualKey(): Int {
        var dpi = 0
        val display = windowManager.defaultDisplay
        val dm = DisplayMetrics()
        val c: Class<*>
        try {
            c = Class.forName("android.view.Display")
            val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
            method.invoke(display, dm)
            dpi = dm.heightPixels
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dpi
    }

    //获取虚拟按键的高度
    fun getNavigationBarHeight(context: Context): Int {
        var result = 0
        if (hasNavBar(context)) {
            val res = context.resources
            val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId)
            }
        }
        return result
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    fun hasNavBar(context: Context): Boolean {
        val res = context.resources
        val resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android")
        if (resourceId != 0) {
            var hasNav = res.getBoolean(resourceId)
            // check override flag
            val sNavBarOverride = getNavBarOverride()
            if ("1" == sNavBarOverride) {
                hasNav = false
            } else if ("0" == sNavBarOverride) {
                hasNav = true
            }
            return hasNav
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey()
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private fun getNavBarOverride(): String? {
        var sNavBarOverride: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                val c = Class.forName("android.os.SystemProperties")
                val m = c.getDeclaredMethod("get", String::class.java)
                m.isAccessible = true
                sNavBarOverride = m.invoke(null, "qemu.hw.mainkeys") as String
            } catch (e: Throwable) {
            }

        }
        return sNavBarOverride
    }

    private fun checkVirtualBar(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val display = windowManager.defaultDisplay
            val size = Point()
            val realSize = Point()
            display.getSize(size)
            display.getRealSize(realSize)
            val result = realSize.y !== size.y
            realSize.y !== size.y
        } else {
            val menu = ViewConfiguration.get(this).hasPermanentMenuKey()
            val back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            !(menu || back)
        }
    }
}
