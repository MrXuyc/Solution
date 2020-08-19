package com.mrxyc.solution.leecodesecondstep.string;

/**
 * 回文子串个数
 *
 * @author yanchun.xu
 */
public class Solution647 {

    public static void main(String[] args) {
        Solution647 solution647 = new Solution647();
        System.out.println(solution647.countSubstrings("aaa"));
        System.out.println(solution647.countSubstrings1("aaa"));
        System.out.println(solution647.countSubstrings2("aaa"));
        System.out.println(solution647.countSubstrings3("aaa"));
    }


    /**
     * 中心扩展
     */
    public int countSubstrings2(String s) {
        int length = s.length();
        int count = 0;
        for (int i = 0; i < length * 2 - 1; i++) {
            int left = i / 2;
            int right = i / 2 + i % 2;
            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count++;
            }
        }
        return count;
    }

    /**
     * 马拉车
     */
    public int countSubstrings3(String s) {
        int n = s.length();
        StringBuffer sb = new StringBuffer("$#");
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        n = sb.length();
        sb.append('!');
        int[] f = new int[n];
        int iMax = 0;
        int rMax = 0;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            //初始化f[i]
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            while (sb.charAt(i + f[i]) == sb.charAt(i - f[i])) {
                f[i]++;
            }
            //动态维护iMax rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            ans += f[i] / 2;
        }
        return ans;
    }


    /**
     * dp判断是否是回文串
     */
    public int countSubstrings1(String s) {
        //计算count
        int count = 0;
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int j = 0; j < length; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    dp[i][j] = true;
                    count++;
                } else {
                    //如果两个相等 则看
                    if (s.charAt(i) == s.charAt(j)) {
                        //相邻是true
                        if (i + 1 == j) {
                            dp[i][j] = true;
                            count++;
                        } else {
                            //不相邻看中间
                            dp[i][j] = dp[i + 1][j - 1];
                            if (dp[i][j]) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }


    /**
     * 暴力法双重循环n^2*log n
     */
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isSubString(s, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isSubString(String s, int left, int right) {
        //双指针判断是否是回文串
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
