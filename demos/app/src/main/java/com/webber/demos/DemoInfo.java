package com.webber.demos;

import android.app.Activity;

/**
 * Created by mxh on 2017/8/14.
 * Describe：
 */

public class DemoInfo {
    private String title;
    private String desc;
    public Class<? extends Activity> demoClass;

    public DemoInfo() {
    }

    public DemoInfo(String title, String desc, Class<? extends Activity> demoClass) {
        this.title = title;
        this.desc = desc;
        this.demoClass = demoClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Class<? extends Activity> getDemoClass() {
        return demoClass;
    }

    public void setDemoClass(Class<? extends Activity> demoClass) {
        this.demoClass = demoClass;
    }
}
