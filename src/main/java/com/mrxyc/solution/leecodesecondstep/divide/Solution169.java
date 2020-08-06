package com.mrxyc.solution.leecodesecondstep.divide;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class Solution169 {
    public static void main(String[] args) {
        Solution169 solution169 = new Solution169();
        solution169.majorityElement(new int[]{3, 3, 4});
    }

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //hashmap 存count
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = data.getOrDefault(nums[i], 0);
            data.put(nums[i], count + 1);
        }
        int res = 0;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> val : data.entrySet()) {
            Integer value = val.getValue();
            if (value > maxCount) {
                maxCount = value;
                res = val.getKey();
            }
        }
        return res;
    }

    //分治
    public int majorityElement1(int[] nums) {
        return maxDeep(nums, 0, nums.length - 1);
    }

    public int maxDeep(int[] nums, int start, int end) {
        //退出条件
        if (start == end) {
            return nums[start];
        }
        //本层处理
        int mid = start + (end - start) / 2;
        int maxPre = maxDeep(nums, start, mid);
        int maxPost = maxDeep(nums, mid + 1, end);
        //两个相等那就是最大
        if (maxPre == maxPost) {
            return maxPre;
        }
        //如果两个不等，需要检验这两值，在上述这个大区间的count
        int maxPreCount = count(nums, maxPre, start, end);
        int maxPostCount = count(nums, maxPost, start, end);
        //递归
        return maxPreCount > maxPostCount ? maxPre : maxPost;
    }

    private int count(int[] nums, int maxPost, int start, int end) {
        int res = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] == maxPost) {
                res++;
            }
        }
        return res;
    }

}
