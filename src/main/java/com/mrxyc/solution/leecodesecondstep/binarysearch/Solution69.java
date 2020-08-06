package com.mrxyc.solution.leecodesecondstep.binarysearch;

/**
 * x的平方根
 */
public class Solution69 {

    public static void main(String[] args) {
        Solution69 solution69 = new Solution69();
        System.out.println(solution69.mySqrt(2147395599));
    }

    //api
    public int mySqrt1(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    //二分查找
    public int mySqrt(int x) {
        int start = 0;
        int end = x;
        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    //牛顿迭代
    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        double c = x;
        double x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + c / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }
}
