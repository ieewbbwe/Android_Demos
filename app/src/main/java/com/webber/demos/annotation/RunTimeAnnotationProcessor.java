package com.webber.demos.annotation;

import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by picher on 2018/1/22.
 * Describe：運行時注解采取反射的機制 拿到注解的值
 */

public class RunTimeAnnotationProcessor {

    public static void main(String[] args) {
        Method[] methods = Student.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print("方法名："+method.getName());
            RunTimeAnnotation runTimeAnnotion = method.getAnnotation(RunTimeAnnotation.class);
            if(runTimeAnnotion != null){
                System.out.print("運行時注解的值："+ runTimeAnnotion.value());
            }

            // Log.d("picher", "運行時注解的值：" + runTimeAnnotion.value());
        }
    }
}
