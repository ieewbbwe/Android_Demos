package com.bana.libcore.core

import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.v4.app.DialogFragment
import android.util.Pair
import android.view.*

import io.reactivex.ObservableTransformer
import io.reactivex.subjects.BehaviorSubject
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.os.Build.VERSION
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import android.view.ViewGroup
import android.util.DisplayMetrics
import android.util.Log
import com.webber.demos.R


/**
 * @author keep2iron [Contract me.](http://keep2iron.github.io)
 * @version 1.0
 * @since 2018/01/29 16:02
 */
abstract class AbstractDialogFragment<DB : ViewDataBinding> : DialogFragment() {


    protected lateinit var mContentView: View

    protected lateinit var dataBinding: DB

    protected lateinit var contextHolder: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextHolder = context
    }

    /**
     * 映射布局方法
     *
     * @return 被映射的布局id
     */
    @get:LayoutRes
    protected abstract val resId: Int

    /**
     * 窗体动画
     */
    open fun getWindowAnim(): Int = -1

    /**
     * 初始化方法
     *
     * @param container 被映射的container对象
     */
    abstract fun initVariables(container: View)

    /**
     * 在初始化方法之前进行调用的方法，子类可以选择性重写
     */
    protected fun beforeInitVariables() {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = activity ?: contextHolder

        val dialog = InnerDialog(context, R.style.dialog)
        dialog.setCanceledOnTouchOutside(true)
        // 设置宽度为屏宽、靠近屏幕底部。
        val window = dialog.window
        window?.let {
            window.setDimAmount(getBackgroundDimAmount())
            val params = window.attributes
            params.gravity = gravity()
            window.attributes = params
            val anim = getWindowAnim()
            if (anim != -1) {
                window.attributes.windowAnimations = anim
            }
        }

        //setStatusBarColorIfPossible(dialog,ContextCompat.getColor(context,android.R.color.transparent))

        return dialog
    }

    private fun setStatusBarColorIfPossible(dialog: Dialog, color: Int) {
        Log.d("picher",""+(dialog.window!!.decorView.rootView as ViewGroup).getChildAt(0).layoutParams.height)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            // DeviceScreenHeight
            val windowManager = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val deviceScreenHeight = displayMetrics.heightPixels
            Log.d("picher","devicesH:"+deviceScreenHeight)

            // Change height child At index zero
            (dialog.window!!.decorView.rootView as ViewGroup).getChildAt(0).layoutParams.height = deviceScreenHeight
            dialog.window.statusBarColor = color
        }
    }

    /**
     * 就是用来控制灰度的值，当为1时，界面除了我们的dialog内容是高亮显示的，dialog以外的区域是黑色的，完全看不到其他内容，系统的默认值是0.5
     */
    open fun getBackgroundDimAmount() = 0.35f

    abstract fun gravity(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val genDataBinding = DataBindingUtil.inflate<DB>(inflater, resId, null, false)
        mContentView = if (genDataBinding != null) {
            dataBinding = genDataBinding
            dataBinding.root
        } else {
            inflater.inflate(resId, null, false)
        }

        beforeInitVariables()
        mContentView.isClickable = true

        initVariables(mContentView)

        return mContentView
    }


    fun <V : View> findViewById(@IdRes id: Int): V {
        return mContentView.findViewById(id)
    }

    abstract fun widthAndHeight(): Pair<Int, Int>

    open fun onTouchOutside() {
    }

    internal inner class InnerDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {

        override fun onTouchEvent(event: MotionEvent): Boolean {
            if (isCancelable && isShowing && event.action == MotionEvent.ACTION_DOWN && isTouchOutside(event)) {
                this@AbstractDialogFragment.onTouchOutside()
                dismiss()
                return true
            }

            return false
        }

        private fun isTouchOutside(event: MotionEvent): Boolean {
            val x = event.x.toInt()
            val y = event.y.toInt()
            val slop = ViewConfiguration.get(context).scaledWindowTouchSlop
            val decorView = window!!.decorView
            return (x < -slop || y < -slop
                    || x > decorView.width + slop
                    || y > decorView.height + slop)
        }
    }
}