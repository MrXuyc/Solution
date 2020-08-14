package com.mrxyc.solution.leecodesecondstep.deep;

/**
 * 被围绕的区域
 * <p>
 * 讲被X包围的改为O
 */
public class Solution130 {

    public static void main(String[] args) {
        Solution130 solution130 = new Solution130();
        char[][] board = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solution130.solve(board);
        System.out.println("");
    }


    public void solve1(char[][] board) {
        //遍历边界，如果有的话，就往里面一直寻找标记
        int n = board.length;
        int m = board[0].length;
        //从四个边进行遍历
        for (int i = 0; i < n; i++) {
            deep(board, i, 0, n, m);
            deep(board, i, m - 1, n, m);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    //被遍历标记到的点
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void deep(char[][] board, int curN, int curM, int n, int m) {
        if (curN < 0 || curN >= n || curM < 0 || curM >= m || board[curN][curM] != 'O') {
            return;
        }
        board[curN][curM] = 'A';
        deep(board, curN + 1, curM, n, m);
        deep(board, curN - 1, curM, n, m);
        deep(board, curN, curM + 1, n, m);
        deep(board, curN, curM - 1, n, m);
    }


    //暴力 扩散 递归
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ('O' == board[i][j]) {
                    //递归判断 判断是否周边是X
                    if (deep(board, i, j, visited)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    private boolean deep(char[][] board, int i, int j, boolean[][] visited) {
        //如果周边都是X则成功
        int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[] resList = new boolean[direction.length];

        for (int d = 0; d < direction.length; d++) {
            if (isOk(board, i + direction[d][0], j + direction[d][1])) {
                if (board[i + direction[d][0]][j + direction[d][1]] == 'X') {
                    resList[d] = true;
                } else {
                    visited[i][j] = true;
                    if (visited[i + direction[d][0]][j + direction[d][1]]) {
                        resList[d] = true;
                    } else {
                        resList[d] = deep(board, i + direction[d][0], j + direction[d][1], visited);
                    }
                    visited[i][j] = false;
                }
            }
        }
        return resList[0] && resList[1] && resList[2] && resList[3];
    }

    private boolean isOk(char[][] board, int i, int j) {
        //超出边界则认为是false
        if (i < 0 || i >= board.length) {
            return false;
        }
        if (j < 0 || j >= board[i].length) {
            return false;
        }
        return true;
    }
}
