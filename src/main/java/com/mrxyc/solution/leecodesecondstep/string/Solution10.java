package com.mrxyc.solution.leecodesecondstep.string;

/**
 * 正则 实现
 *
 * .匹配任意字符
 *
 * *匹配前面任意个的逻辑
 */
public class Solution10 {
    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.isMatch("a"
                , "a.*b*b*.*.*.*a*.*a"));
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    if (matches(s, p, i, j - 1)) {
                        //如果匹配的话 当前结果就是消失某* 或者 取某* 成功的结果。
                        f[i][j] = f[i][j - 2] || f[i - 1][j];
                    } else {
                        //如果当前是* 并且字符不匹配，那么就消失处理，使用之前的匹配
                        f[i][j] = f[i][j - 2];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        //如果匹配则取 两边都去除的结果
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
