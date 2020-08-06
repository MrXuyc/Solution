package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 矩阵中的最长递增路径
 */
public class Solution329 {

    public static void main(String[] args) {
        Solution329 solution329 = new Solution329();
//        int[][] matrix = new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        int[][] matrix = new int[][]{{3, 4, 5}, {3, 2, 6}, {9, 8, 7}};
        System.out.println(solution329.longestIncreasingPath1(matrix));
    }

    //模拟 需要记录避免重复计算
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return 0;
        }
        int m = matrix[0].length;
        //定义暂存结果
        int[][] record = new int[n][m];
        //定义方向 顺时针 上 右 下 左
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //设置当前最大值
                deep(record, matrix, i, j, n, m);
                max = Math.max(max, record[i][j]);
            }
        }
        return max;
    }

    public void deep(int[][] record, int[][] matrix, int i, int j, int n, int m) {
        int[][] direction = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        record[i][j] = 1;
        //选择当前节点 遍历周围四个节点
        //如果遇到大的就增加记住 如果遇到小的相等的则用默认1
        for (int k = 0; k < direction.length; k++) {
            int[] direct = direction[k];
            int ci = i + direct[0];
            int cj = j + direct[1];
            if (ci >= 0 && ci < n && cj >= 0 && cj < m) {
                if (matrix[i][j] < matrix[ci][cj]) {
                    if (record[ci][cj] == 0) {
                        deep(record, matrix, ci, cj, n, m);
                    }
                    record[i][j] = Math.max(record[i][j], 1 + record[ci][cj]);
                }
            }
        }
    }

    //拓扑排序
    public int longestIncreasingPath1(int[][] matrix) {
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] outDegrees = new int[rows][columns];
        //遍历每个节点有多少出路
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int[] dir : dirs) {
                    int newRow = i + dir[0];
                    int newColumn = j + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns
                            && matrix[newRow][newColumn] > matrix[i][j]) {
                        ++outDegrees[i][j];
                    }
                }
            }
        }
        //队列存储没有处理的
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (outDegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int column = cell[1];
                for (int[] dir : dirs) {
                    int newRow = row + dir[0];
                    int newColumn = column + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns
                            && matrix[newRow][newColumn] < matrix[row][column]) {
                        --outDegrees[newRow][newColumn];
                        if (outDegrees[newRow][newColumn] == 0) {
                            queue.offer(new int[]{newRow, newColumn});
                        }
                    }
                }
            }
        }
        return ans;
    }
}
