package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

import java.util.Arrays;

/**
 * 零钱兑换 最少兑换次数，不行的话返回-1
 *
 * 输入: coins = [1, 2, 5], amount = 11
 *
 * 输出: 3
 *
 * 解释: 11 = 5 + 5 + 1
 */
public class Solution322 {

    public static void main(String[] args) {
        Solution322 solution322 = new Solution322();
        System.out.println(solution322.coinChange(new int[]{1, 2, 5}, 11));
    }

    private int min = Integer.MAX_VALUE;

    //贪心 深搜
    public int coinChange3(int[] coins, int amount) {
        Arrays.sort(coins);
        deep(coins.length - 1, coins, 0, amount);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public void deep(int index, int[] coins, int count, int needAmount) {
        if (needAmount == 0) {
            min = Math.min(min, count);
            return;
        }
        if (index < 0) {
            return;
        }
        int val = needAmount / coins[index];
        for (int i = val; i >= 0 && count + val < min; i--) {
            deep(index - 1, coins, count + i, needAmount - i * coins[index]);
        }
    }

    //动态规划  自上而下需要使用递归
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return deep(coins, new int[amount + 1], amount);
    }

    public int deep(int[] coins, int[] dp, int amount) {
        //退出条件
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (dp[amount] != 0) {
            return dp[amount];
        }
        //本层处理
        //遍历所有的硬币选取 判断是否是符合阈值的
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int val = deep(coins, dp, amount - coins[i]);
            if (val >= 0 && val < min) {
                min = val + 1;
            }
        }
        dp[amount] = min == Integer.MAX_VALUE ? -1 : min;
        //递归
        return dp[amount];
    }

    //动态规划  自下而上
    public int coinChange2(int[] coins, int amount) {
        //定义一个最大步骤值
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                //当前硬币可选
                if (coins[j] <= i) {
                    //从已有结果和 去掉当前硬币的amount值+1
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    //递归  会超过时间限制
    public int coinChange1(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        return deep(coins, 0, amount);
    }

    private int deep(int[] coins, int count, int amount) {
        //退出
        if (amount == 0) {
            return count;
        }
        if (amount < 0) {
            return -1;
        }
        //本层处理
        //递归
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int val = deep(coins, count + 1, amount - coins[i]);
            if (val > 0) {
                min = Math.min(min, val);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
