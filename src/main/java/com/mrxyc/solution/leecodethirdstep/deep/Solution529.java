package com.mrxyc.solution.leecodethirdstep.deep;

/**
 * 扫雷游戏
 */
public class Solution529 {

    public static void main(String[] args) {
        Solution529 solution529 = new Solution529();
        char[][] board = new char[][]{{'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'M'}, {'E', 'E', 'M', 'E', 'E', 'E', 'E', 'E'}, {'M', 'E', 'E', 'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'M', 'E', 'E', 'E', 'E'}};
        int[] click = new int[]{0, 0};
        char[][] chars = solution529.updateBoard(board, click);
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        //特殊情况判断
        if (board == null || board.length == 0 || board[0].length == 0) {
            return board;
        }
        //如果一上来挖的是地雷则直接退出
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            //递归扩散
            deep(board, click[0], click[1], board.length, board[0].length);
        }
        return board;
    }

    public void deep(char[][] board, int i, int j, int n, int m) {
        if (board[i][j] != 'E') {
            return;
        }
        //递归看到雷
        int[][] direction = new int[][]{{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        int mCount = 0;
        for (int d = 0; d < direction.length; d++) {
            int curI = i + direction[d][0];
            int curJ = j + direction[d][1];
            if (curI >= 0 && curI < n && curJ >= 0 && curJ < m) {
                if (board[curI][curJ] == 'M') {
                    mCount++;
                }
            }
        }
        if (mCount == 0) {
            board[i][j] = 'B';
            for (int d = 0; d < direction.length; d++) {
                int curI = i + direction[d][0];
                int curJ = j + direction[d][1];
                if (curI >= 0 && curI < n && curJ >= 0 && curJ < m) {
                    deep(board, curI, curJ, n, m);
                }
            }
        } else {
            board[i][j] = Character.forDigit(mCount, 10);
        }
    }
}
