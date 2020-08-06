package com.mrxyc.solution.leecodesecondstep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两个数组的交集2
 */
public class Solution350 {
    //排序双数组 双指针比较
    public int[] intersect1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int leftN = 0;
        int leftM = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new ArrayList<>();
        while (leftN < n && leftM < m) {
            if (nums1[leftN] == nums2[leftM]) {
                res.add(nums1[leftN]);
                leftN++;
                leftM++;
            } else if (nums1[leftN] < nums2[leftM]) {
                leftN++;
            } else {
                leftM++;
            }
        }
        int[] resArr = new int[res.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }


    //hashmap 存数和count
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> data = new HashMap<>();
        for (int num : nums2) {
            Integer curCount = data.getOrDefault(num, 0);
            data.put(num, curCount + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int num : nums1) {
            if (data.containsKey(num)) {
                res.add(num);
                Integer curVal = data.get(num);
                if (curVal == 1) {
                    data.remove(num);
                } else {
                    data.put(num, curVal - 1);
                }
            }
        }
        int[] resArr = new int[res.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }
}
