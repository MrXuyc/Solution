package com.mrxyc.solution.leecodesecondstep.array;
/**
 * 两数之和
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{2, 2, 11, 15}, 11);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] twoSum(int[] nums, int target) {
        return null;
    }

    public int[] oneHash(int[] nums, int target) {
        Map<Integer, Integer> tmp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (tmp.containsKey(target - nums[i])) {
                return new int[]{i, tmp.get(target - nums[i])};
            }
            tmp.put(nums[i], i);
        }
        return new int[0];
    }

    public int[] twoHash(int[] nums, int target) {
        //hash表法 暂存所有数组值
        Map<Integer, Integer> tmp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            tmp.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer index = tmp.get(target - nums[i]);
            if (index != null && i != index) {
                return new int[]{i, index};
            }
        }
        return new int[0];
    }


    public int[] Violence(int[] nums, int target) {
        //暴力法
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
