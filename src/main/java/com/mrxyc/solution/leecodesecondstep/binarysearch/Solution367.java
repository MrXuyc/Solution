package com.mrxyc.solution.leecodesecondstep.binarysearch;

/**
 * 有效的完全平方数
 */
public class Solution367 {

    public static void main(String[] args) {
        Solution367 solution367 = new Solution367();
        System.out.println(solution367.isPerfectSquare(14));
    }

    public boolean isPerfectSquare(int num) {
        boolean flag = false;
        int left = 0;
        int right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long val = (long) mid * mid;
            if (val == num) {
                return true;
            } else if (val > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return flag;
    }

    //牛顿迭代法
    public boolean isPerfectSquare1(int num) {
        if (num < 2) {
            return true;
        }
        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }
}
