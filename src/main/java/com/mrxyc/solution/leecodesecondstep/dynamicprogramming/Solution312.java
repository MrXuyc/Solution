package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

import java.util.Arrays;

/**
 * 戳气球
 */
public class Solution312 {

    public static void main(String[] args) {
        Solution312 solution312 = new Solution312();
        solution312.maxCoins1(new int[]{3, 1, 5, 8});
    }

    //记忆化搜索
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] val = new int[n + 2];
        val[0] = 1;
        val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        //记录以左右两个节点 能获取的值
        int[][] dp = new int[n + 2][n + 2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        //增加两个左右边界
        return deep(val, dp, 0, n + 1);
    }

    //分治
    public int deep(int[] val, int[][] dp, int left, int right) {
        //退出条件 如果中间没有值了，返回0
        if (left >= right - 1) {
            return 0;
        }
        //避免重复计算
        if (dp[left][right] != -1) {
            return dp[left][right];
        }
        //本层处理
        //选取任意一个节点为中心节点 分治计算最大值
        for (int i = left + 1; i < right; i++) {
            int sum = val[left] * val[i] * val[right];
            //递归
            sum = sum + deep(val, dp, left, i) + deep(val, dp, i, right);
            dp[left][right] = Math.max(dp[left][right], sum);
        }
        return dp[left][right];
    }

    //动态规划
    public int maxCoins1(int[] nums) {
        int n = nums.length;
        int[] val = new int[n + 2];
        val[0] = 1;
        val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum = sum + dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }

        return dp[0][n + 1];
    }
}
