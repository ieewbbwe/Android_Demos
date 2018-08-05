package com.webber.demos;

import android.text.TextUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by picher on 2018/7/26.
 * Describe：
 */
public class NApplicationTest {

    @Test
    public void contains(){
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("Picher");
        strings.add("picher");
        System.out.print("Contains:"+strings.contains("picher1"));

        List<TestObj> testObjs = new ArrayList<>();
        testObjs.add(new TestObj("1"));
        testObjs.add(new TestObj("2"));

        TestObj testObj = new TestObj("picher");
        testObjs.add(testObj);
        System.out.print("Contains:"+ testObjs.contains(testObj));

    }

    @Test
    public void equals(){
        String a = "aaa";
        String b = a;
        b = "bbb";
        System.out.println("a:"+a + "b:"+b);
        System.out.println("a:"+a.toString().hashCode() + "b:"+b.toString().hashCode());
        System.out.println("a==b:" + (a==b));
    }

    public class TestObj{
        private String name;

        public TestObj(){}
        public TestObj(String n){name = n;}

    }

}