package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 打家劫舍2
 *
 * 首尾相连
 */
public class Solution213 {

    public static void main(String[] args) {
        Solution213 solution213 = new Solution213();
        System.out.println(solution213.rob(new int[]{0}));
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robRange(nums, 0, nums.length - 1), robRange(nums, 1, nums.length));
    }

    private int robRange(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int prePre = 0;
        int pre = 0;
        for (int i = left; i < right; i++) {
            int temp = pre;
            pre = Math.max(nums[i] + prePre, pre);
            prePre = temp;
        }
        return pre;
    }
}
