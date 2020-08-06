package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.ArrayList;
import java.util.List;

/**
 * n皇后
 *
 * 输入: 4
 *
 * 输出: [
 *
 * [".Q..",  // 解法 1
 *
 * "...Q",
 *
 * "Q...",
 *
 * "..Q."],
 *
 * ["..Q.",  // 解法 2
 *
 * "Q...",
 *
 * "...Q",
 *
 * ".Q.."] ]
 */
public class Solution51 {

    public static void main(String[] args) {
        Solution51 solution51 = new Solution51();
        System.out.println(solution51.solveNQueens(4));
    }

    private int[] rows;
    private int[] hills;
    private int[] dales;
    private int n;
    private List<List<String>> res = new ArrayList<>();
    private int[] queens;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        rows = new int[n];
        hills = new int[4 * n - 1];
        dales = new int[2 * n - 1];
        queens = new int[n];
        backtrack(0);
        return res;
    }

    private void backtrack(int row) {
        for (int col = 0; col < n; col++) {
            //是否可放
            if (isNotUnderAttack(row, col)) {
                //放
                placeQueen(row, col);
                if (row + 1 == n) {
                    //已经放满 打印结果
                    addSolution();
                } else {
                    backtrack(row + 1);
                }
                //清除本次
                removeQueens(row, col);
            }
        }
    }

    private void removeQueens(int row, int col) {
        queens[row] = 0;
        rows[col] = 0;
        hills[row - col + 2 * n] = 0;
        dales[row + col] = 0;
    }

    private void addSolution() {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int col = queens[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (col == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            solution.add(sb.toString());
        }
        res.add(solution);
    }

    private void placeQueen(int row, int col) {
        queens[row] = col;
        rows[col] = 1;
        hills[row - col + 2 * n] = 1;
        dales[row + col] = 1;
    }

    private boolean isNotUnderAttack(int row, int col) {
        int flag = rows[col] + hills[row - col + 2 * n] + dales[row + col];
        return flag == 0;
    }

}
