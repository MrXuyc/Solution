package com.mrxyc.solution.leecodesecondstep.array;

/**
 * 删除有序数组重复元素
 */
public class Solution26 {
    public int removeDuplicates(int[] nums) {
        return 0;
    }

    //双指针法 一个标记要移动的位置，另外一个遍历元素，遇到不相等的，就替换
    public int removeDuplicate(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                //不用本地交换
                if (i != j) {
                    nums[i] = nums[j];
                }
            }
        }
        return i + 1;
    }
}
