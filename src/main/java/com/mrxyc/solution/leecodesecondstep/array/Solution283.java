package com.mrxyc.solution.leecodesecondstep.array;

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

    //一个指针记录最近一个要替换的索引位 另外一个指针遍历如果非0就移动到上个位置。
    //该指针到结尾 都是0
    public void moveZeros1(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    //一个指针记录停在最早需要替换的一个0位置。另外一个指针遇到非0就跟他交换，0就过
    public void moveZeros2(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != index) {
                    int tmp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = tmp;
                }
                index++;
            }
        }
    }

}
