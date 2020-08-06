package com.mrxyc.solution.leecodesecondstep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 三数之和 a+b+c=0  a+b=-c
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        return violenceMethod(nums);
    }

    //暴力法 n^3  需要排序去重
    public List<List<Integer>> violenceMethod(int[] nums) {
        Set<List<Integer>> res = new LinkedHashSet<>();
        //排序 去重
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (-nums[i] == nums[j] + nums[k]) {
                        List<Integer> singleRes = Arrays.asList(nums[i], nums[j], nums[k]);
                        res.add(singleRes);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    //hash存target值，减少n^2
    public List<List<Integer>> violenceMethodOpt(int[] nums) {
        Set<List<Integer>> res = new LinkedHashSet<>();
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            targetMap.put(nums[i], i);
        }
        for (int j = 0; j < nums.length - 1; j++) {
            for (int k = j + 1; k < nums.length; k++) {
                int target = nums[j] + nums[k];
                if (targetMap.containsKey(-target)) {
                    Integer index = targetMap.get(-target);
                    if (index != j && index != k) {
                        List<Integer> list = Arrays.asList(nums[j], nums[k], -target);
                        Collections.sort(list);
                        res.add(list);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    //排序 双指针
    //hash存target值，减少n^2
    public List<List<Integer>> twoPoint(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //异常值处理 如果排序后数组 都大于0则直接退出
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                //不重复元素 进行判断存储
                int sum = nums[low] + nums[high] + nums[i];
                //相等的话存储
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    low++;
                    while (nums[low] == nums[low - 1] && low < high) {
                        low++;
                    }
                    high--;
                    while (nums[high] == nums[high + 1] && low < high) {
                        high--;
                    }
                } else if (sum < 0) {
                    //重复元素 需要跳过
                    low++;
                    while (nums[low] == nums[low - 1] && low < high) {
                        low++;
                    }
                } else if (sum > 0) {
                    //重复元素 需要跳过
                    high--;
                    while (nums[high] == nums[high + 1] && high > low) {
                        high--;
                    }
                }
            }
        }
        return res;
    }
}
