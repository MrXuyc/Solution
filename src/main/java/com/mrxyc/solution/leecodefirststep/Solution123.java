package com.mrxyc.solution.leecodefirststep;

/**
 * 购买股票的最佳时机3
 *
 * 只能买两笔  不能同时
 */
public class Solution123 {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int len = prices.length;
        int[][] dp = new int[len][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        //实际状态依赖与上一状态，以下情况实际不存
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;
        for (int i = 1; i < len; ++i) {
            dp[i][0] = 0;
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return Math.max(dp[len - 1][2], dp[len - 1][4]);
    }
}
