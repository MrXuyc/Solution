package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

import java.util.Arrays;

/**
 * 不同路径
 */
public class Solution62 {
    //动态规划
    public int uniquePaths(int m, int n) {
        int[][] counts = new int[n][m];
        //初始化
        for (int i = 0; i < m; i++) {
            counts[0][i] = 1;
        }
        for (int i = 0; i < n; i++) {
            counts[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                counts[i][j] = counts[i - 1][j] + counts[i][j - 1];
            }
        }
        return counts[n - 1][m - 1];
    }

    //动态规划 优化空间2n
    public int uniquePaths1(int m, int n) {
        int[] pre = new int[n];
        int[] cur = new int[n];
        Arrays.fill(pre, 1);
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] = cur[j - 1] + pre[j];
            }
            pre = cur.clone();
        }
        return pre[n - 1];
    }

    //动态规划 优化空间n

    public int uniquePath2(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] = cur[j] + cur[j - 1];
            }
        }
        return cur[n - 1];
    }
}
