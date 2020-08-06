package com.mrxyc.solution.leecodesecondstep.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3] 输出: [1,2,4] 解释: 输入数组表示数字 123。 示例 2:
 *
 * 输入: [4,3,2,1] 输出: [4,3,2,2] 解释: 输入数组表示数字 4321。
 */
public class Solution66 {
    public static void main(String[] args) {
        Solution66 solution66 = new Solution66();
        solution66.plusOne1(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0});
    }

    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return new int[0];
        }
        //从后往前遍历
        int jinwei = 0;
        digits[digits.length - 1] = digits[digits.length - 1] + 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int singleRes = digits[i] + jinwei;
            jinwei = singleRes / 10;
            digits[i] = singleRes % 10;
        }
        //首位进位
        if (jinwei != 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            System.arraycopy(digits, 0, res, 1, digits.length);
            return res;
        }
        return digits;
    }

    /**
     * 如果数组较短就可以
     */
    public int[] plusOne1(int[] digits) {
        if (digits.length == 0) {
            return new int[0];
        }
        long num = 0;
        int n = 0;
        for (int i = digits.length - 1; i >= 0; i--, n++) {
            num = num + (digits[i] * (long) Math.pow(10, n));
        }
        num = num + 1;
        List<Integer> res = new ArrayList<>();
        while (num != 0) {
            res.add(0, (int) (num % 10));
            num = num / 10;
        }
        Integer[] resArray = new Integer[res.size()];
        res.toArray(resArray);
        int[] resArrayInt = new int[res.size()];
        for (int i = 0; i < resArray.length; i++) {
            resArrayInt[i] = resArray[i];
        }
        return resArrayInt;
    }
}
