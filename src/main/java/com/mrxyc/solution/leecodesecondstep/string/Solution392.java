package com.mrxyc.solution.leecodesecondstep.string;

/**
 * 判断子序列
 *
 * 判断s是否是t的子序列
 */
public class Solution392 {

    public static void main(String[] args) {
        Solution392 solution392 = new Solution392();
        System.out.println(solution392.isSubSequence1("abc", "ahbgdc"));
    }

    //双指针
    public boolean isSubSequence(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        while (sIndex < s.length() && tIndex < t.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
            }
            tIndex++;
        }
        return sIndex == s.length();
    }

    //动态规划
    public boolean isSubSequence1(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        boolean[][] dp = new boolean[sLength + 1][tLength + 1];
        for (int i = 0; i <= tLength; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= sLength; i++) {
            dp[i][0] = false;
        }
        for (int i = 1; i <= sLength; i++) {
            for (int j = 1; j <= tLength; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[sLength][tLength];
    }

}
