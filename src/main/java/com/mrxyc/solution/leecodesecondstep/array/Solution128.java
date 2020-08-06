package com.mrxyc.solution.leecodesecondstep.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列
 *
 * 无序数组
 *
 * On 算法时间复杂度
 */
public class Solution128 {
    public int longestConsecutive(int[] nums) {
        return 0;
    }


    //排序遍历
    public int longestConsecutive1(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        Arrays.sort(nums);
        int res = 0;
        int tmp = 1;
        for (int i = 1; i < nums.length; i++) {
            //如果相等 则++
            if (nums[i] - 1 == nums[i - 1]) {
                tmp++;
            } else if (nums[i] != nums[i - 1]) {
                //不等 选取值，进行清除
                res = Math.max(tmp, res);
                tmp = 0;
            }
        }
        return Math.max(tmp, res);
    }

    public int longestConsecutive2(int[] nums) {

        int res = 0;
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }
        for (Integer num : numSet) {
            if (!numSet.contains(num - 1)) {
                int tmp = 0;
                int cur = num;
                while (numSet.contains(cur)) {
                    tmp++;
                    cur++;
                }
                res = Math.max(tmp, res);
            }
        }
        return res;
    }
}
