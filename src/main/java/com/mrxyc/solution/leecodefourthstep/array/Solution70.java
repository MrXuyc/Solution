package com.mrxyc.solution.leecodefourthstep.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 爬楼梯
 */
public class Solution70 {
    Map<Integer, Integer> data = new HashMap<>();

    //递归
    public int climbStairs(int n) {
        return deep(n);
    }

    private int deep(int i) {
        if (i <= 0) {
            return 0;
        }
        if (i <= 2) {
            return i;
        }
        if (data.containsKey(i)) {
            return data.get(i);
        }
        int val = deep(i - 1) + deep(i - 2);
        data.put(i, val);
        return val;
    }

    //迭代
    public int climbStairs1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }
        int pre = 1;
        int val = 2;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = val + pre;
            pre = val;
            val = res;
        }
        return res;
    }

    //动态规划
    public int climbStair2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
