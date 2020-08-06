package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 整数拆分
 */
public class Solution343 {

    public static void main(String[] args) {
        Solution343 solution343 = new Solution343();
        System.out.println(solution343.integerBreak(9));
    }

    public int integerBreak2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                //要不分割两个数，要不继续分割
                max = Math.max(max, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public int integerBreak3(int n) {
        if (n < 4) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }
        return dp[n];
    }


    public int integerBreak1(int n) {
        int[] dp = new int[n + 2];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        if (n < 4) {
            return dp[n];
        }
        //
        int threeCount = n / 3;
        int twoCount = 0;
        if (n % 3 == 1) {
            //需要换成2*2 减去一个3 +2*2
            threeCount = threeCount - 1;
            twoCount = twoCount + 2;
        } else if (n % 3 == 2) {
            twoCount = twoCount + 1;
        }
        return (int) (Math.pow(3, threeCount) * Math.pow(2, twoCount));

    }


    //暴力递归
    public int integerBreak(int n) {
        //能拆分2 - n个数
        int max = 1;
        for (int i = 2; i < n; i++) {
            max = Math.max(max, maxRes(n, i));
        }
        return max;
    }

    //将n拆分成i个数 求最大值乘积
    private int maxRes(int n, int i) {
        Set<List<Integer>> values = new HashSet<>();
        deep(values, new ArrayList<>(), n, 0, i);
        int max = 0;
        for (List<Integer> value : values) {
            int curMax = 1;
            for (Integer val : value) {
                curMax = curMax * val;
            }
            max = Math.max(max, curMax);
        }
        return max;
    }

    private void deep(Set<List<Integer>> values, List<Integer> value, int n, int curN, int i) {
        //已经选好了
        if (value.size() >= i) {
            if (curN == n) {
                Collections.sort(value);
                values.add(value);
            }
            return;
        }
        //未选好，但是超出了 废弃
        if (curN >= n) {
            return;
        }
        for (int j = 1; j <= n - curN; j++) {
            List<Integer> list = new ArrayList<>(value);
            list.add(j);
            deep(values, list, n, curN + j, i);
        }
    }


}
