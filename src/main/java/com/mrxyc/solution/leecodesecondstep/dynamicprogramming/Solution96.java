package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 不同的二叉搜索树
 */
public class Solution96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        //分治思想
        for (int i = 2; i <= n; i++) {
            //选择不同节点当作根节点。两边区间的值相乘就是当前根节点的结果
            for (int j = 1; j <= i; j++) {
                dp[i] = dp[i] + (dp[j - 1] * dp[i - j]);
            }
        }
        return dp[n];
    }
}
