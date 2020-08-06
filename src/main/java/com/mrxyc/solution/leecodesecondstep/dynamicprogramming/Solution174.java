package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 地下城游戏
 */
public class Solution174 {

    public static void main(String[] args) {
        Solution174 solution174 = new Solution174();
        int[][] dungeon = new int[][]{{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}};
        System.out.println(solution174.calculateMinimumHP1(dungeon));
    }

    public int calculateMinimumHP1(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                //计算最小花费的血量
                //选两个中需要血值最少的
                int min = Math.min(dp[i + 1][j], dp[i][j + 1]);
                //需要花费的血量 至少需要1
                dp[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }


    //递归超时
    public int calculateMinimumHP(int[][] dungeon) {
        List<List<Integer>> steps = new ArrayList<>();
        deep(dungeon, steps, new ArrayList<Integer>(), 0, 0, dungeon.length, dungeon[0].length);
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < steps.size(); i++) {
            int curMin = 0;
            int curCount = 0;
            List<Integer> stepCounts = steps.get(i);
            for (int j = 0; j < stepCounts.size(); j++) {
                curCount = curCount + stepCounts.get(j);
                curMin = Math.min(curMin, curCount);
            }
            min = Math.max(min, curMin);
        }
        return min < 0 ? Math.abs(min) + 1 : 1;
    }

    private void deep(int[][] dungeon, List<List<Integer>> steps, List<Integer> curStep, int curN, int curM, int n, int m) {
        //退出条件 走到最后节点
        if (curN == n - 1 && curM == m - 1) {
            curStep.add(dungeon[curN][curM]);
            steps.add(curStep);
            return;
        }
        //退出条件 走到边界
        if (curN >= n || curM >= m) {
            return;
        }
        //本层处理
        curStep.add(dungeon[curN][curM]);
        //递归
        deep(dungeon, steps, new ArrayList<>(curStep), curN + 1, curM, n, m);

        deep(dungeon, steps, new ArrayList<>(curStep), curN, curM + 1, n, m);

    }
}
