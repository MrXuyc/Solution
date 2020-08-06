package com.mrxyc.solution.leecodesecondstep.divide;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * -100.0 < x < 100.0
 *
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class Solution50 {
    public double myPow(double x, int n) {
        long N = n;
        return N > 0 ? quickMul(x, N) : 1 / quickMul(x, -N);
    }

    //递归
    private double quickMul(double x, long l) {
        if (l == 0) {
            return 1;
        }
        double i = quickMul(x, l / 2);

        return l % 2 == 0 ? i * i : i * i * x;
    }

    //迭代
    private double quickMul1(double x, long l) {
        double res = 1;
        double xc = x;
        while (l > 0) {
            if (l % 2 != 0) {
                res = res * xc;
            }
            xc = xc * xc;
            l = l / 2;
        }
        return res;
    }

}
