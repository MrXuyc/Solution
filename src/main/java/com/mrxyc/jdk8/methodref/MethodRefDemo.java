package com.mrxyc.jdk8.methodref;

public class MethodRefDemo {
    public static String stringOp(StringFunc sf, int s, String s1) {
        return sf.func(s1, s);
    }

    public static void main(String[] args) {
        String inStr = "lambda add power to Java";
        //MyStringOps::strReverse 相当于实现了接口方法func()
        // 并在接口方法func()中作了MyStringOps.strReverse()操作
        String outStr = stringOp(MyStringOps::strReverse, 1, inStr);
        System.out.println("Original string: " + inStr);
        System.out.println("String reserved: " + outStr);
    }
}
