package com.mrxyc.solution.leecodefirststep;

/**
 * 最长回文子串
 */
public class Solution5 {

    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        System.out.println(solution5.longestPalindrome2("babad"));
    }

    //动态规划
    public String longestPalindrome2(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // 1. 状态定义
        // dp[i][j] 表示s[i...j] 是否是回文串
        // 2. 初始化
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        // 3. 状态转移
        // 注意：先填左下角
        // 填表规则：先一列一列的填写，再一行一行的填，保证左下方的单元格先进行计算
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 头尾字符不相等，不是回文串
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // 相等的情况下
                    // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 状态转移
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要dp[i][j] == true 成立，表示s[i...j] 是否是回文串
                // 此时更新记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        // 4. 返回值
        return s.substring(begin, begin + maxLen);
    }


    public String longestPalindrome1(String s) {
        String res = "";
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            //两种情况 1.都是从当前节点开始 2.从i和i+1节点开始
            String length1 = matchLength(s, i, i);
            String length2 = matchLength(s, i, i + 1);
            String curRes = "";
            if (length1.length() > length2.length()) {
                curRes = length1;
            } else {
                curRes = length2;
            }
            if (curRes.length() > max) {
                max = curRes.length();
                res = curRes;
            }

        }
        return res;
    }

    private String matchLength(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    //暴力
    public String longestPalindrome(String s) {
        //给字符串加空格 变成0---2n-1 遍历 选取核心节点
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (i + 1 < s.length()) {
                sb.append(" ");
            }
        }
        s = sb.toString();
        int max = 0;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i;
            while (s.charAt(left) == s.charAt(right)) {
                String curStr = s.substring(left, right + 1).replace(" ", "");
                if (curStr.length() > max) {
                    max = curStr.length();
                    res = curStr;
                }
                if (left > 0 && right + 1 < s.length()) {
                    left--;
                    right++;
                } else {
                    break;
                }
            }
        }
        return res;
    }
}
