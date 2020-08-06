package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 不同路径2
 *
 * 带障碍物
 */
public class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        //特殊情况校验
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1) {
            return 0;
        }
        int[][] counts = new int[n][m];
        //初始化起点
        counts[0][0] = 1;
        //从第一排初始化情况
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[0][i] == 1) {
                counts[0][i] = 0;
            } else {
                counts[0][i] = counts[0][i - 1];
            }
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] == 1) {
                counts[i][0] = 0;
            } else {
                counts[i][0] = counts[i - 1][0];
            }
        }
        //从第一列初始化情况
        //从1，1初始化情况
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    counts[i][j] = 0;
                } else {
                    counts[i][j] = counts[i - 1][j] + counts[i][j - 1];
                }
            }
        }
        return counts[n - 1][m - 1];
    }
}
