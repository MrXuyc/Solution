package com.mrxyc.solution.leecodesecondstep.binarysearch;

/**
 * 搜索插入位置
 */
public class Solution35 {

    //O n
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        Solution35 solution35 = new Solution35();
        System.out.println(solution35.searchInsert1(new int[]{1, 3, 5, 6}, 2));
    }

    //二分
    public int searchInsert1(int[] nums, int target) {
        if (target <= nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
