package com.yfs.test;

import java.util.Date;

/**
 * @Author 于凡粟
 * @Date 2020/5/22 8:39 上午
 */
public class TestValue {
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        char[] a = {'1','2','3','4'};
        char[] b = "1231313".toCharArray();
        System.out.println(new String(b,1,2));
        System.out.println(new Date());
    }
}
