package com.mrxyc.solution.leecodesecondstep.array;

/**
 * 缺失的第一个正数
 */
public class Solution41 {
    //原地hash
    public int firstMissingPositive(int[] nums) {
        //原地hash
        //先将所有0，负值 变成nums.length+1的非区间值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }
        //遍历 将正负符合区间的进行标识为负当前值
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            if (val > 0 && val <= nums.length) {
                nums[val - 1] = -Math.abs(nums[val - 1]);
            }
        }
        //判断当前值如果不在1-nums.length之间认为是缺失
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }


    public static void main(String[] args) {
        Solution41 solution41 = new Solution41();
        System.out.println(solution41.firstMissingPositive1(new int[]{1, 1}));
    }

    //替换法
    public int firstMissingPositive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        //循环进行替换
        for (int index = 0; index < nums.length; index++) {
            //如果当前值不在该区间内 则不进行替换
            while (nums[index] > 0 && nums[index] <= nums.length && nums[index] != nums[nums[index] - 1]) {
                int tmp = nums[index];
                nums[index] = nums[nums[index] - 1];
                nums[tmp - 1] = tmp;
            }
        }
        //遍历 如果当前值不符合当前索引位的值 返回当前代表数字
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        //如果上述都满足 说明缺少
        return nums.length + 1;
    }

}
