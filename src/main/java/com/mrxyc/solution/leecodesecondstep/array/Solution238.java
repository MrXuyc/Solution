package com.mrxyc.solution.leecodesecondstep.array;

/**
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 可以使用int
 *
 * 进阶 常数空间复杂度，输出数组不视为额外空间
 */
public class Solution238 {
    public int[] productExceptSelf(int[] nums) {
        return nums;
    }

    //暴力法 乘 n^2
    public int[] productExceptSelf1(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    if (j == 0) {
                        tmp = nums[0];
                    } else {
                        tmp = tmp * nums[j];
                    }
                }
            }
            res[i] = tmp;
        }
        return res;
    }

    //除数法  但是解决不了里面如果有0的问题

    public int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];
        int val = nums[0];
        for (int i = 1; i < nums.length; i++) {
            val = val * nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            res[i] = val / nums[i];
        }
        return res;
    }

    //空间法 左右乘积表
    public int[] productExceptSelf3(int[] nums) {
        int[] res = new int[nums.length];
        //左乘积表
        int[] left = new int[nums.length];
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        //右乘积表
        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        //左右相乘
        for (int i = 0; i < nums.length; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }


    public int[] productExceptSelf4(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i < nums.length - 1) {
                right = right * nums[i + 1];
            }
            res[i] = res[i] * right;
        }
        return res;
    }

}
