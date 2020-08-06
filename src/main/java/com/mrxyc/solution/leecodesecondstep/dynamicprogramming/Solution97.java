package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 交错字符串
 */
public class Solution97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int r = s3.length();
        if (n + m != r) {
            return false;
        }
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (s1.charAt(i - 1) == s3.charAt(p) && dp[i - 1][j]);
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (s2.charAt(j - 1) == s3.charAt(p) && dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
