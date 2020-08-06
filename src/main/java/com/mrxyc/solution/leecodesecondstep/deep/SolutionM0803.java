package com.mrxyc.solution.leecodesecondstep.deep;

/**
 * 魔术索引
 */
public class SolutionM0803 {
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    //强行二分分治
    public int findMagicIndex1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        return deep(nums, left, right);
    }

    private int deep(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == mid) {
            return mid;
        }
        //优先用左边结果
        int leftVal = deep(nums, left, mid - 1);
        if (leftVal != -1) {
            return leftVal;
        } else {
            return deep(nums, mid + 1, right);
        }
    }


}
