package com.mrxyc.solution.leecodesecondstep.greedy;

/**
 * 买卖股票的最佳时机
 */
public class Solution122 {

    public static void main(String[] args) {
        Solution122 solution122 = new Solution122();
        System.out.println(solution122.maxProfit(new int[]{1, 7, 3, 4, 5, 6, 7}));
    }

    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            //判断下一个节点大于当前节点
            if (prices[i + 1] > prices[i]) {
                profit = profit + prices[i + 1] - prices[i];
            }
        }
        return profit;
    }
}
