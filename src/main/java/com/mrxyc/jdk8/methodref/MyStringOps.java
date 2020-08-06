package com.mrxyc.jdk8.methodref;

public class MyStringOps {
    //静态方法： 反转字符串
    public static String strReverse(String str, int k) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}
