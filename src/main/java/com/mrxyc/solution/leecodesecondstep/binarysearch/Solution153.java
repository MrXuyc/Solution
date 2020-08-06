package com.mrxyc.solution.leecodesecondstep.binarysearch;

/**
 * 旋转数组 找寻最小值
 *
 * 找到旋转点
 */
public class Solution153 {

    public static void main(String[] args) {
        Solution153 solution153 = new Solution153();
        System.out.println(solution153.findMin(new int[]{1, 2, 3}));
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = nums[mid];
            if (nums[Math.abs(mid - 1) % nums.length] >= val
                    && nums[(mid + 1) % nums.length] >= val) {
                return val;
            }
            if (nums[0] <= val) {
                if (val <= nums[nums.length - 1]) {
                    //无转折点
                    right = mid - 1;
                } else {
                    //转折点在右边
                    left = mid + 1;
                }
            } else {
                //转折点在左边
                right = mid - 1;
            }
        }
        return -1;
    }
}
