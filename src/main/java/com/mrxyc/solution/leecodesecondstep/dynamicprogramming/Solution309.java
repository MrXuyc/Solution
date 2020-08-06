package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 最佳买卖股票时机含冷冻期
 *
 * 输入: [1,2,3,0,2]
 *
 * 输出: 3
 *
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class Solution309 {
    public int maxProfit(int[] prices) {
        int max = 0;
        // f[i][0]: 不卖
        // f[i][1]: 刚卖，马上冷冻期，下一天不能买
        // f[i][2]: 之前卖的，下一天可买
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            //不卖。取上一天不卖的钱或者之前卖了今天买
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            //刚卖
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }


    public static void main(String[] args) {
        Solution309 solution309 = new Solution309();
        solution309.maxProfit(new int[]{1, 2, 3, 0, 2});
    }
}
