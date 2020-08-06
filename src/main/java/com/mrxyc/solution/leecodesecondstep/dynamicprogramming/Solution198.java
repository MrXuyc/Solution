package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 打家劫舍
 */
public class Solution198 {

    public static void main(String[] args) {
        Solution198 solution198 = new Solution198();
        System.out.println(solution198.rob1(new int[]{2, 7, 9, 3, 1}));
    }


    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int prePre = nums[0];
        int pre = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int temp = pre;
            pre = Math.max(nums[i] + prePre, pre);
            prePre = temp;
        }
        return pre;
    }


    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            if (i - 3 >= 0) {
                dp[i] = Math.max(nums[i] + dp[i - 2], nums[i] + dp[i - 3]);
            } else {
                dp[i] = nums[i] + dp[i - 2];
            }
        }
        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }
}
