package com.mrxyc.solution.leecode;

public class Solution7 {

    public static void main(String[] args) {
        Solution7 solution7 = new Solution7();
        System.out.println(solution7.reverse(25));
    }

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            res = res * 10 + pop;
        }
        return res;
    }
}
