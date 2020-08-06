package com.mrxyc.solution.leecode;

/**
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 *
 * 示例 1:
 *
 * 输入: [10,2] 输出: 210 示例 2:
 *
 * 输入: [3,30,34,5,9] 输出: 9534330 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 */
public class Solution179 {
    public static void main(String[] args) {
        Solution179 solution179 = new Solution179();
        System.out.println(solution179.largestNumber(new int[]{1, 11, 2, 22, 3, 4, 5, 6, 7}));
    }


    public String largestNumber(int[] nums) {
        nums = sort(nums, 0, nums.length - 1);

        if (nums[0] == 0) {
            return "0";
        }

        String result = "";
        for (int i : nums) {
            result = result + i;
        }

        return result;
    }

    public int[] sort(int[] nums, int start, int end) {

        int op = nums[(start + end) / 2];
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && compare(nums[j], op) < 0) {
                j--;
            }

            while (i < j && compare(op, nums[i]) < 0) {
                i++;
            }

            if (i < j && compare(nums[i], nums[j]) == 0) {
                i++;
            } else {

                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (i - 1 > start) {
            nums = sort(nums, start, i - 1);
        }

        if (j + 1 < end) {
            nums = sort(nums, j + 1, end);
        }

        return nums;
    }

    public int compare(int a, int b) {

        return (a + "" + b).compareTo(b + "" + a);
    }

}
