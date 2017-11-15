package com.webber.demos.weight;

/**
 * Created by mxh on 2017/10/24.
 * Describe：
 */

public class Test {

    public static void main(String[] args){
        aline(1024*1024*1024-1024*1024-1024-1);
    }

    public static void aline(int n){
        int c;
        for(c=0;c<n;c++){
            n &= n-1;
        }
        System.out.print(""+c);
    }
}
