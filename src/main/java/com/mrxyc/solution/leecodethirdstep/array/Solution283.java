package com.mrxyc.solution.leecodethirdstep.array;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12] 输出: [1,3,12,0,0] 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
 */
public class Solution283 {
    public void moveZeroes(int[] nums) {

    }

    public void moveZeroes1(int[] nums) {
        //双指针
        //一个指针指向需要替换的，一个指针遍历数组
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (slow != i) {
                    nums[slow] = nums[i];
                    nums[i] = 0;
                }
                slow++;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[slow] = nums[i];
                slow++;
            }
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
