package com.mrxyc.solution.leecodethirdstep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 三数之和 a+b+c=0
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }

    //暴力法
    public List<List<Integer>> threeSum1(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> subRes = new ArrayList<>();
                        subRes.add(nums[i]);
                        subRes.add(nums[j]);
                        subRes.add(nums[k]);
                        Collections.sort(subRes);
                        res.add(subRes);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    //空间 存储法

    public List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Map<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            data.put(nums[i], i);
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int target = -nums[i] - nums[j];
                Integer integer = data.get(target);
                if (integer != null && integer != i && integer != j) {
                    List<Integer> subRes = new ArrayList<>();
                    subRes.add(nums[i]);
                    subRes.add(nums[j]);
                    subRes.add(target);
                    Collections.sort(subRes);
                    res.add(subRes);
                }
            }
        }

        return new ArrayList<>(res);
    }

    //排序 双指针

    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (sum > 0) {
                    end--;
                    //需要移动 处理重复
                    while (nums[end] == nums[end + 1]) {
                        end--;
                    }
                } else if (sum < 0) {
                    start++;
                    //需要移动 处理重复
                    while (nums[start] == nums[start - 1]) {
                        start++;
                    }
                } else {
                    List<Integer> subRes = new ArrayList<>();
                    subRes.add(nums[i]);
                    subRes.add(nums[start]);
                    subRes.add(nums[end]);
                    res.add(subRes);
                    //需要移动 处理重复
                    end--;
                    while (nums[end] == nums[end + 1]) {
                        end--;
                    }
                    start++;
                    while (nums[start] == nums[start - 1]) {
                        start++;
                    }
                }
            }
        }
        return res;
    }
}
