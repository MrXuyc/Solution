package com.mrxyc.solution.leecode;

import java.util.Arrays;

public class Solution34 {
    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        int start = -1;
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                if (mid - 1 < 0 || nums[mid - 1] < nums[mid]) {
                    //当前就是起始
                    start = mid;
                    break;
                } else {
                    //继续寻找
                    high = mid - 1;
                }
            }
        }
        int end = -1;
        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                if (mid + 1 == nums.length || nums[mid + 1] > nums[mid]) {
                    //当前就是结尾
                    end = mid;
                    break;
                } else {
                    //继续寻找
                    low = mid + 1;
                }
            }
        }
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2};
        //int [] nums = new int[]{0,1,2,3,4,5,6,7,8,9,10};
        int target = 3;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }
}
