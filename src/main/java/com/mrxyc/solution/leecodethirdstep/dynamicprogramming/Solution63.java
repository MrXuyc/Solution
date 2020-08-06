package com.mrxyc.solution.leecodethirdstep.dynamicprogramming;

/**
 * 不同路径2 有障碍物
 */
public class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }
        //特殊值
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        //初始化
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[0][i] == 1) {
                //有障碍 0
                dp[0][i] = 0;
            } else {
                //无障碍用左边值
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] == 1) {
                //有障碍 0
                dp[i][0] = 0;
            } else {
                //无障碍用上边值
                dp[i][0] = dp[i - 1][0];
            }
        }
        //动态规划
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
