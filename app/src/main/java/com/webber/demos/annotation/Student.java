package com.webber.demos.annotation;

/**
 * Created by picher on 2018/1/22.
 * Describe：
 */

public class Student {

    @RunTimeAnnotation("run time test value")
    public String RuntimeAnnotation(){
        return "";
    }

    @BuildAnnotation("build time test value")
    public void buildAnnotation(){

    }


}
