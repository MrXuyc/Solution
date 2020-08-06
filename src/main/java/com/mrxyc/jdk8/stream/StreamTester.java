package com.mrxyc.jdk8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamTester {
    public static void main(String[] args) {
        //sorted
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        strings.stream().filter(str -> str != null && !str.equals("")).forEach(x -> System.out.print(x + " "));
        List<String> collect = strings.stream().sorted((x, y) -> x.compareTo(y)).filter(str -> str != null && !str.equals("")).limit(3).collect(Collectors.toList());
        collect.stream().forEach(x -> System.out.print(x + " "));
        System.out.println(" ");
        //forEach  limit
        //获取0-9的随机数  6位 作为一个字符串
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        random.ints(0, 10).limit(6).forEach(x -> str.append(x));
        System.out.println(str.toString());

        //map
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> collect1 = numbers.stream().map(x -> x * x).distinct().collect(Collectors.toList());
        collect.stream().forEach(x -> System.out.print(x + " "));
        System.out.println(" ");
        //sorted  limit
        random.ints().limit(10).sorted().forEach(System.out::println);
        //filter
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        List<String> s1 = strings.stream().filter(string -> string.isEmpty()).collect(Collectors.toList());
        //并行流
        long count1 = strings.parallelStream().filter(s -> !s.isEmpty()).count();
        System.out.println(count1);
        //合并字符串
        String collect2 = strings.parallelStream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
        System.out.println(collect2);
        List<String> filtered = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        IntSummaryStatistics stats = Arrays.stream(nums).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        stats = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
}
