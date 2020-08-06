package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 乘积最大子数组
 */
public class Solution152 {

    public static void main(String[] args) {
        Solution152 solution152 = new Solution152();
        System.out.println(solution152.maxProduct(new int[]{-2, 1, -1}));
    }

    //动态规划
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //需要保存当前min的值和 max的值 达到能处理负负得正等方式
        int[] dpMin = new int[nums.length];
        int[] dpMax = new int[nums.length];
        int max = nums[0];
        dpMin[0] = nums[0];
        dpMax[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(nums[i] * dpMin[i - 1], nums[i] * dpMax[i - 1]));
            dpMin[i] = Math.min(nums[i], Math.min(nums[i] * dpMin[i - 1], nums[i] * dpMax[i - 1]));
            max = Math.max(max, dpMax[i]);
        }
        return max;
    }
}
