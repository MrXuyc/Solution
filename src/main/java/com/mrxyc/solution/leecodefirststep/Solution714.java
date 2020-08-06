package com.mrxyc.solution.leecodefirststep;

/**
 * 买卖股票的最佳时机 手续费
 */
public class Solution714 {

    public static void main(String[] args) {
        Solution714 solution714 = new Solution714();
        System.out.println(solution714.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }


    public int maxProfit(int[] prices, int fee) {
        int cash = 0;
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}
