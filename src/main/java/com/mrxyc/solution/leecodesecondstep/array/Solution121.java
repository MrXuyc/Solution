package com.mrxyc.solution.leecodesecondstep.array;

/**
 * 买卖股票的最佳时机
 */
public class Solution121 {

    public static void main(String[] args) {
        Solution121 solution121 = new Solution121();
        System.out.println(solution121.maxProfit(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }


    //选好卖出时间，找买入时间
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                maxProfit = Math.max(maxProfit, prices[i] - prices[j]);
            }
        }
        return maxProfit;
    }

    //选好买入时间，寻找卖出时间
    public int maxProfit1(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
        }
        return maxProfit;
    }

    //遍历一遍，找到当前最低点，计算价格。
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        int minProfit = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minProfit) {
                minProfit = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minProfit);
            }
        }
        return maxProfit;
    }
}
