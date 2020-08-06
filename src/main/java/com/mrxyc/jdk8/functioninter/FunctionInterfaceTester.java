package com.mrxyc.jdk8.functioninter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunctionInterfaceTester {
    public static void main(String[] args) {
        GreetingService greetingService = message -> {
            System.out.println(message);
        };
        greetingService.sayMessage("hello");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true

        System.out.println("输出所有数据:");
        eval(list, i -> i >= 3);
//        list = null;
        list.stream().filter(n -> true).forEach(System.out::println);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer i :
                list) {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }
}

@FunctionalInterface
interface GreetingService {
    void sayMessage(String message);
}