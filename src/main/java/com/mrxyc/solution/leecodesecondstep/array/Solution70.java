package com.mrxyc.solution.leecodesecondstep.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 爬楼梯
 */
public class Solution70 {
    public int climbStairs(int n) {
        return climbStairs1(n);
    }


    //递归  从后到前
    private int climbStairs1(int n) {
        // 退出条件
        if (n <= 2) {
            return n;
        }
        //当前处理
        //递归
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    //优化递归 保存计算结果  从后到前

    private static Map<Integer, Integer> res = new HashMap<>();

    private int climbStairs2(int n) {
        //退出条件
        if (n <= 2) {
            return n;
        }
        if (res.containsKey(n)) {
            return res.get(n);
        }
        //递归
        int resInt = climbStairs2(n - 1) + climbStairs2(n - 2);
        //放结果
        res.put(n, resInt);
        return resInt;
    }

    //循环  从前到后  斐波那契数
    private int climbStairs3(int n) {
        //小于等于2直接返回
        if (n <= 2) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        int f3 = 3;
        for (int i = 3; i <= n; i++) {
            // 计算当前值
            f3 = f1 + f2;
            // 给下次的初始值
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    //动态规划


    //如果1，2，3步可选 并且不能连续相同步数
}
