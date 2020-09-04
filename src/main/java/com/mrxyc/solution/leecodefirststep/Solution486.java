package com.mrxyc.solution.leecodefirststep;

/**
 * 预测赢家
 */
public class Solution486 {

    public static void main(String[] args) {
        Solution486 solution486 = new Solution486();
        System.out.println(solution486.predictTheWinner(new int[]{1, 5, 2}));
    }

    public boolean predictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    private int total(int[] nums, int start, int end, int trun) {
        if (start == end) {
            return nums[start] * trun;
        }
        int scoreStart = nums[start] * trun + total(nums, start + 1, end, -trun);
        int scoreEnd = nums[end] * trun + total(nums, start, end - 1, -trun);
        return Math.max(scoreStart * trun, scoreEnd * trun) * trun;
    }


    public boolean predictTheWinner1(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] >= 0;
    }
}
