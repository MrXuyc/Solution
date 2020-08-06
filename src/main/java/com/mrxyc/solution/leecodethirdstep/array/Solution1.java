package com.mrxyc.solution.leecodethirdstep.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 */
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        //暴力法
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i != j) {
                    return new int[]{i, j};
                }
            }
        }
//        return new int[0];
        //两遍hash法
        Map<Integer, Integer> tmp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            tmp.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmp.containsKey(target - nums[i])) {
                int index = tmp.get(target - nums[i]);
                if (index != i) {
                    return new int[]{i, tmp.get(target - nums[i])};
                }
            }
        }
//        return new int[0];
        //一遍hash
        Map<Integer, Integer> tmp1 = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (tmp1.containsKey(target - nums[i])) {
                return new int[]{i, tmp1.get(target - nums[i])};
            } else {
                tmp1.put(nums[i], i);
            }
        }
        return new int[0];
    }
}
