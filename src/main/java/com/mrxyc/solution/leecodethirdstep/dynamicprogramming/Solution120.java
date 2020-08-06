package com.mrxyc.solution.leecodethirdstep.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三角形最小的路径和
 */
public class Solution120 {

    public static void main(String[] args) {
        Solution120 solution120 = new Solution120();
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> t1 = new ArrayList<Integer>(Arrays.asList(2));
        List<Integer> t2 = new ArrayList<Integer>(Arrays.asList(3, 4));
        List<Integer> t3 = new ArrayList<Integer>(Arrays.asList(6, 5, 7));
        List<Integer> t4 = new ArrayList<Integer>(Arrays.asList(4, 8, 8, 3));
        triangle.add(t1);
        triangle.add(t2);
        triangle.add(t3);
        triangle.add(t4);
        System.out.println(solution120.minimumTotal(triangle));
    }

    //动态规划
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int m = triangle.get(n - 1).size();
        int[][] dp = new int[n + 1][m + 1];
        int minRes = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int min = Math.min(getCurVal(dp, i - 1, j - 1), getCurVal(dp, i - 1, j));
                dp[i][j] = (min == Integer.MAX_VALUE ? 0 : min) + getCurVal(triangle, i - 1, j - 1);
                if (i == n) {
                    minRes = Math.min(minRes, dp[i][j]);
                }
            }
        }
        return minRes;
    }

    public int getCurVal(int[][] dp, int n, int m) {
        if (n >= dp.length || n < 0) {
            return Integer.MAX_VALUE;
        }
        if (m >= dp[n].length || m < 0) {
            return Integer.MAX_VALUE;
        }
        return dp[n][m];
    }

    public int getCurVal(List<List<Integer>> triangle, int n, int m) {
        if (n >= triangle.size() || n < 0) {
            return Integer.MAX_VALUE;
        }
        if (m >= triangle.get(n).size() || m < 0) {
            return Integer.MAX_VALUE;
        }
        return triangle.get(n).get(m);
    }


    //递归 遍历所有路径
    public int minimumTotal1(List<List<Integer>> triangle) {
        int minRes = 0;
        return minRes;
    }
}
