package com.mrxyc.solution.leecodethirdstep.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 爬楼梯
 */
public class Solution70 {
    private Map<Integer, Integer> data = new HashMap<>();

    //递归
    public int climbStairs(int n) {
        return deep(n);
    }

    private int deep(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }
        if (data.containsKey(n)) {
            return data.get(n);
        }
        int res = deep(n - 1) + deep(n - 2);
        data.put(n, res);
        return res;
    }


    //迭代

    public int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        int pre = 1;
        int res = 2;
        for (int i = 3; i <= n; i++) {
            int tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    /**
     * 动态规划
     */
    public int climbStairs2(int n) {
        int pre = 0;
        int res = 1;
        for (int i = 1; i <= n; i++) {
            int tmp = res;
            res = pre + res;
            pre = tmp;
        }

        return res;
    }


    //动态规划
}
