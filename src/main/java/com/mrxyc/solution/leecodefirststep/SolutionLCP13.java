package com.mrxyc.solution.leecodefirststep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 寻宝
 */
public class SolutionLCP13 {
    public int minimalSteps(String[] maze) {
        //S 起点 T 终点 M 机关点  .普通节点 # 墙壁  O 石头节点
        int mazeLength = maze.length;
        int mazeSingleLength = maze[0].length();
        //顺时针
        int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        //记录全局M O个数
        List<int[]> buttons = new ArrayList<int[]>();
        List<int[]> stones = new ArrayList<int[]>();
        //记录S T 位置
        int sIndexI = -1;
        int sIndexJ = -1;
        int tIndexI = -1;
        int tIndexJ = -1;
        for (int i = 0; i < mazeLength; i++) {
            for (int j = 0; j < mazeSingleLength; j++) {
                if ('M' == maze[i].charAt(j)) {
                    buttons.add(new int[]{i, j});
                } else if ('S' == maze[i].charAt(j)) {
                    sIndexI = i;
                    sIndexJ = j;
                } else if ('T' == maze[i].charAt(j)) {
                    tIndexI = i;
                    tIndexJ = j;
                } else if ('O' == maze[i].charAt(j)) {
                    stones.add(new int[]{i, j});
                }
            }
        }
        int mCount = buttons.size();
        int oCount = stones.size();
        int[][] startDist = bfs(sIndexI, sIndexJ, maze, direction);

        if (oCount == 0) {
            return startDist[tIndexI][tIndexJ];
        }
        //从某个机关到其他机关/起点与终点的最短距离
        int[][] dist = new int[mCount][mCount + 2];
        for (int i = 0; i < mCount; i++) {
            Arrays.fill(dist[i], -1);
        }
        //中间结果
        int[][][] dd = new int[mCount][][];
        for (int i = 0; i < mCount; i++) {
            int[][] d = bfs(buttons.get(i)[0], buttons.get(i)[1], maze, direction);
            dd[i] = d;
            //从某点到终点不需要拿石头
            dist[i][mCount + 1] = d[tIndexI][tIndexJ];
        }

        for (int i = 0; i < mCount; i++) {
            int tmp = -1;
            for (int k = 0; k < oCount; k++) {
                int midX = stones.get(k)[0], midY = stones.get(k)[1];
                if (dd[i][midX][midY] != -1 && startDist[midX][midY] != -1) {
                    if (tmp == -1 || tmp > dd[i][midX][midY] + startDist[midX][midY]) {
                        tmp = dd[i][midX][midY] + startDist[midX][midY];
                    }
                }
            }
            dist[i][mCount] = tmp;
            for (int j = i + 1; j < mCount; j++) {
                int mn = -1;
                for (int k = 0; k < oCount; k++) {
                    int midX = stones.get(k)[0], midY = stones.get(k)[1];
                    if (dd[i][midX][midY] != -1 && dd[j][midX][midY] != -1) {
                        if (mn == -1 || mn > dd[i][midX][midY] + dd[j][midX][midY]) {
                            mn = dd[i][midX][midY] + dd[j][midX][midY];
                        }
                    }
                }
                dist[i][j] = mn;
                dist[j][i] = mn;
            }
        }
        int minStep = -1;
        // 无法达成的情形
        for (int i = 0; i < mCount; i++) {
            if (dist[i][mCount] == -1 || dist[i][mCount + 1] == -1) {
                return -1;
            }
        }

        // dp 数组， -1 代表没有遍历到
        int[][] dp = new int[1 << mCount][mCount];
        for (int i = 0; i < 1 << mCount; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < mCount; i++) {
            dp[1 << i][i] = dist[i][mCount];
        }

        // 由于更新的状态都比未更新的大，所以直接从小到大遍历即可
        for (int mask = 1; mask < (1 << mCount); mask++) {
            for (int i = 0; i < mCount; i++) {
                // 当前 dp 是合法的
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < mCount; j++) {
                        // j 不在 mask 里
                        if ((mask & (1 << j)) == 0) {
                            int next = mask | (1 << j);
                            if (dp[next][j] == -1 || dp[next][j] > dp[mask][i] + dist[i][j]) {
                                dp[next][j] = dp[mask][i] + dist[i][j];
                            }
                        }
                    }
                }
            }
        }
        int finalMask = (1 << mCount) - 1;
        for (int i = 0; i < mCount; i++) {
            if (minStep == -1 || minStep > dp[finalMask][i] + dist[i][mCount + 1]) {
                minStep = dp[finalMask][i] + dist[i][mCount + 1];
            }
        }
        return minStep;
    }

    public int[][] bfs(int i, int j, String[] maze, int[][] direction) {
        int mazeLength = maze.length;
        int mazeSingleLength = maze[0].length();
        int[][] res = new int[mazeLength][mazeSingleLength];
        for (int x = 0; x < mazeLength; x++) {
            Arrays.fill(res[x], -1);
        }
        res[i][j] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curI = cur[0];
            int curJ = cur[1];
            for (int k = 0; k < direction.length; k++) {
                int nI = curI + direction[k][0];
                int nJ = curJ + direction[k][1];
                if (inBound(nI, nJ, maze) && maze[nI].charAt(nJ) != '#' && res[nI][nJ] == -1) {
                    res[nI][nJ] = res[curI][curJ] + 1;
                    queue.add(new int[]{nI, nJ});
                }
            }
        }
        return res;
    }

    private boolean inBound(int nI, int nJ, String[] maze) {
        return nI >= 0 && nI < maze.length && nJ >= 0 && nJ < maze[0].length();
    }

}
