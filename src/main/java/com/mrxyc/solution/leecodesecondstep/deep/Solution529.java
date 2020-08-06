package com.mrxyc.solution.leecodesecondstep.deep;

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

    //未挖出的地雷
    private char M = 'M';
    //未挖出的方块
    private char E = 'E';
    //已经挖出的方块 周围没有地雷
    private char B = 'B';
    //已经挖出的地雷
    private char X = 'X';
    //数字 当前周围的地雷

    public char[][] updateBoard(char[][] board, int[] click) {
        int clickN = click[0];
        int clickM = click[1];
        //如果当前点击就是雷 则直接挖出退出
        if (M == board[clickN][clickM]) {
            board[clickN][clickM] = X;
            return board;
        }
        //定义步骤
        int[] nStep = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
        int[] mStep = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
        //判断当前节点的周围该给什么值
        deep(board, clickN, clickM, nStep, mStep);
        return board;
    }

    private void deep(char[][] board, int clickN, int clickM, int[] nStep, int[] mStep) {
        //退出条件 如果当前是雷 则退出
        if (M == board[clickN][clickM]) {
            board[clickN][clickM] = X;
        } else if (E == board[clickN][clickM]) {
            //本层处理
            //遍历周围节点 对结果计数
            int MCount = getMCount(board, clickN, clickM, nStep, mStep);
            //获取周围Mcount
            if (MCount == 0) {
                board[clickN][clickM] = B;
                for (int i = 0; i < nStep.length; i++) {

                    int curN = clickN + nStep[i];
                    int curM = clickM + mStep[i];

                    //节点可用性验证
                    if (curN >= 0 && curN < board.length
                            && curM >= 0 && curM < board[0].length) {
                        //递归
                        deep(board, curN, curM, nStep, mStep);
                    }

                }

            } else {
                //如果MCount>0 直接给数字
                board[clickN][clickM] = Character.forDigit(MCount, 10);
            }
        }
    }

    private int getMCount(char[][] board, int clickN, int clickM, int[] nStep, int[] mStep) {
        int MCount = 0;
        for (int i = 0; i < nStep.length; i++) {
            int curN = clickN + nStep[i];
            int curM = clickM + mStep[i];
            //节点可用性验证
            if (curN >= 0 && curN < board.length
                    && curM >= 0 && curM < board[0].length) {
                if (M == board[curN][curM]) {
                    MCount++;
                }
            }
        }
        return MCount;
    }
}
