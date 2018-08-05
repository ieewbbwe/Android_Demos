package com.webber.demos.view

import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.webber.demos.DemoAdapter
import com.webber.demos.DemoInfo
import com.webber.demos.R
import com.webber.demos.customerview.CustomerViewActivity
import com.webber.demos.four.FourComponentActivity
import com.webber.demos.fragmentdemo.FragmentDemoActivity
import com.webber.demos.nest.ViewPagerFragmentActivity
import com.webber.demos.view.dialog.DialogDemoActivity
import com.webber.demos.weight.*
import kotlinx.android.synthetic.main.activity_view_demo.*

class ViewDemoActivity : AppCompatActivity() {

    var DEMOS : Array<DemoInfo> = arrayOf(
            DemoInfo("Marquee", "List中跑马灯效果实现", ListMarqueeActivity::class.java),
            DemoInfo("Marquee Draw", "自定义跑马灯，draw", CustomerMarqueeActivity::class.java),
            DemoInfo("Dialog", "彈出框專場", DialogDemoActivity::class.java),
            DemoInfo("TabLayout", "TabLayout学习", TabLayoutActivity::class.java),
            DemoInfo("BottomSheet底部弹出框", "BottomSheet", BottomSheetActivity::class.java),
            DemoInfo("列表相关", "RecycleView的坑", RecyclerViewActivity::class.java),
            DemoInfo("新布局Demo", "Constraint学习", ConstraintLayoutActivity::class.java),
            DemoInfo("ViewPager", "ViewPager的例子", ViewPagerDemoActivity::class.java),
            DemoInfo("VP&FGM嵌套", "各种嵌套问题", ViewPagerFragmentActivity::class.java),
            DemoInfo("加载网页", "使用WebView加载", WebViewActivity::class.java),
            DemoInfo("CoordinatorLayout", "CoordinatorLayout处理联动", CoordinatorActivity::class.java),
            DemoInfo("悬浮菜单", "CoordinatorLayout实现悬浮菜单", CoordinatorFloatActivity::class.java),//KT
            DemoInfo("SurfaceView", "SurfaceView学习", SurfaceViewActivity::class.java),
            DemoInfo("ToolBar学习", "官方推出的头部控件", ToolBarActivity::class.java),
            DemoInfo("自定义控件", "CustomerView", CustomerViewActivity::class.java),
            DemoInfo("顶部导航栏的应用", "ToolBar学习", ToolBarDemoActivity::class.java)
    )

    var mAdapter : DemoAdapter = DemoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_demo)
        m_demo_rlv.layoutManager = LinearLayoutManager(this)
        m_demo_rlv.adapter =mAdapter

        mAdapter.setData(DEMOS)

        mAdapter.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val classes = DEMOS[position].demoClass
            if (classes != null) {
                val intent = Intent(this@ViewDemoActivity, classes)
                startActivity(intent)
            } else {
                Toast.makeText(this@ViewDemoActivity, "模块还未接入!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
