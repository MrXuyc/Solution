package com.mrxyc.solution.leecodesecondstep.array;

import java.util.Arrays;

/**
 * 最接近的三数之和
 */
public class Solution16 {
    //暴力法 n^3
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int curDiff = Math.abs(target - sum);
                    if (curDiff < diff) {
                        diff = curDiff;
                        res = sum;
                    }
                }
            }
        }
        return res;
    }

    public int threeSumClosest1(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int res = Integer.MIN_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                //计算寻找值
                int sum = nums[start] + nums[end] + nums[i];
                //寻找当前curVal 在nums中最接近的值
                int curDiff = Math.abs(target - sum);
                if (curDiff < diff) {
                    diff = curDiff;
                    res = sum;
                }
                if (sum > target) {
                    end--;
                    while (start < end && nums[end] == nums[end + 1]) {
                        end--;
                    }
                } else {
                    start++;
                    while (start < end && nums[start] == nums[start - 1]) {
                        start++;
                    }
                }
            }
        }
        return res;
    }


}
