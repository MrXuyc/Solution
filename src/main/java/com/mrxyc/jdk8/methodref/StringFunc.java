package com.mrxyc.jdk8.methodref;

@FunctionalInterface
/**
 * 函数式接口里允许定义默认方法
 *函数式接口里允许定义静态方法
 * 函数式接口里允许定义 java.lang.Object 里的 public 方法
 */
public interface StringFunc {
    String func(String n, int i);
}
