package com.mrxyc.solution.leecodefirststep;

/**
 * 最短回文串
 */
public class Solution214 {

    public static void main(String[] args) {
        Solution214 solution214 = new Solution214();
        System.out.println(solution214.shortestPalindrome("baacb"));
    }

    //双指针补全
    public String shortestPalindrome(String s) {
        int n = s.length();
        int base = 131;
        int mod = 1000000007;
        int left = 0;
        int right = 0;
        int mul = 1;
        int best = -1;
        for (int i = 0; i < n; i++) {
            left = (int) (((long) left * base + s.charAt(i)) % mod);
            right = (int) ((right + (long) mul * s.charAt(i)) % mod);
            if (left == right) {
                best = i;
            }
            mul = (int) ((long) mul * base % mod);
        }
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuilder sb = new StringBuilder(add).reverse();
        sb.append(s);
        return sb.toString();
    }
}
