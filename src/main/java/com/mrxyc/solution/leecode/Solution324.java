package com.mrxyc.solution.leecode;

import java.util.Arrays;

/**
 * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 *
 * 示例 1:
 *
 * 输入: nums = [1, 5, 1, 1, 6, 4] 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6] 示例 2:
 *
 * 输入: nums = [1, 3, 2, 2, 3, 1] 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2] 说明: 你可以假设所有输入都会得到有效的结果。
 *
 * 进阶: 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class Solution324 {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        int copy[] = Arrays.copyOf(nums, len);
        Arrays.sort(copy);
        int mid = len / 2;
        if ((len & 1) == 0) {
            mid--;
        }
        for (int i = len - 1; i > mid; i--) {
            nums[2 * (len - i - 1) + 1] = copy[i];
        }
        for (int i = mid; i >= 0; i--) {
            nums[2 * (mid - i)] = copy[i];
        }
    }
}
