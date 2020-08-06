package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 通配符匹配
 *
 * 匹配? *
 */
public class Solution44 {

    public static void main(String[] args) {
        Solution44 solution44 = new Solution44();
        System.out.println(solution44.isMatch("adceb", "*a*b"));
    }

    //贪心
    public boolean isMatch1(String s, String p) {
        int sRight = s.length();
        int pRight = p.length();
        //寻找最后面一个*
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                sRight--;
                pRight--;
            } else {
                return false;
            }
        }
        //无* 判断是否全等
        if (pRight == 0) {
            return sRight == 0;
        }
        //验证*前面的匹配情况
        int sIndex = 0;
        int pIndex = 0;
        //记录s和p *的对应位置
        int sRecord = -1;
        int pRecord = -1;
        while (sIndex < sRight && pIndex < pRight) {
            if (p.charAt(pIndex) == '*') {
                pIndex++;
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                //相等 往后移动
                sIndex++;
                pIndex++;
            } else if (sRecord != -1 && sRecord + 1 < sRight) {
                sRecord++;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                return false;
            }
        }
        //处理是否全是*问题
        return allStars(p, pIndex, pRight);
    }

    private boolean allStars(String p, int pIndex, int pRight) {
        for (int i = pIndex; i < pRight; i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }


    private boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }

    //动态规划
    public boolean isMatch(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();
        boolean[][] dp = new boolean[pLength + 1][sLength + 1];
        //初始化
        dp[0][0] = true;
        for (int i = 1; i <= pLength; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = false;
            }
        }
        //动态规划处理
        for (int i = 1; i <= pLength; i++) {
            for (int j = 1; j <= sLength; j++) {
                if (s.charAt(j - 1) == p.charAt(i - 1) || p.charAt(i - 1) == '?') {
                    //相等逻辑
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //不等逻辑 或者是*
                    if (p.charAt(i - 1) == '*') {
                        dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }
        }

        return dp[pLength][sLength];
    }
}
