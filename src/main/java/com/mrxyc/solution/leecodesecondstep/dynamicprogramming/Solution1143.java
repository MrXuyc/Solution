package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 最长公共子序列
 */
public class Solution1143 {
    public static void main(String[] args) {
        Solution1143 solution1143 = new Solution1143();
        System.out.println(solution1143.longestCommonSubSequence("abcba", "abcbcba"));
    }

    public int longestCommonSubSequence(String text1, String text2) {
        int text1Length = text1.length();
        int text2Length = text2.length();
        int[][] counts = new int[text2Length + 1][text1Length + 1];
        for (int i = 1; i < text2Length + 1; i++) {
            for (int j = 1; j < text1Length + 1; j++) {
                if (text1.charAt(j - 1) == text2.charAt(i - 1)) {
                    //相等则取左上角+1
                    counts[i][j] = counts[i - 1][j - 1] + 1;
                } else {
                    counts[i][j] = Math.max(counts[i - 1][j], counts[i][j - 1]);
                }
            }
        }
        return counts[text2Length][text1Length];
    }
}
