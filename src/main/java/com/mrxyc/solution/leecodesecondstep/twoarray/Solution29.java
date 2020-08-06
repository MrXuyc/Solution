package com.mrxyc.solution.leecodesecondstep.twoarray;

public class Solution29 {

    public static void main(String[] args) {
        Solution29 solution29 = new Solution29();
        solution29.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

    public int[] spiralOrder(int[][] matrix) {
        //异常条件
        if (matrix.length == 0) {
            return new int[]{};
        }
        int total = matrix.length * matrix[0].length;
        int[] res = new int[total];
        //定义方向 0右 1下 2左 3上 默认是右
        int directionIndex = 0;
        //定义方向需要处理的坐标处理
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //定义是否访问的矩阵
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        //默认起始坐标
        int lastNodeX = 0;
        int lastNodeY = 0;
        visited[0][0] = true;
        res[0] = matrix[0][0];
        for (int i = 1; i < total; i++) {
            //根据上个节点和历史方向 处理当前方向
            int[] direction = directions[directionIndex];
            //越界则需要切换
            int tmpLastNodeX = lastNodeX + direction[0];
            int tmpLastNodeY = lastNodeY + direction[1];
            if (tmpLastNodeX < 0 || tmpLastNodeX > matrix.length - 1
                    || tmpLastNodeY < 0 || tmpLastNodeY > matrix[0].length - 1
                    || visited[tmpLastNodeX][tmpLastNodeY]) {
                directionIndex = (directionIndex + 1) % directions.length;
                direction = directions[directionIndex];
                tmpLastNodeX = lastNodeX + direction[0];
                tmpLastNodeY = lastNodeY + direction[1];
            }
            //选取坐标处理，获取坐标
            lastNodeX = tmpLastNodeX;
            lastNodeY = tmpLastNodeY;
            //设置结果
            //设置节点 xy
            res[i] = matrix[lastNodeX][lastNodeY];
            visited[lastNodeX][lastNodeY] = true;
        }
        return res;
    }

    public int[] sprialOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] res = new int[rows * columns];
        //划边界，四步填充，之后换边界
        int left = 0;
        int right = columns - 1;
        int top = 0;
        int bottom = rows - 1;
        int index = 0;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res[index++] = matrix[top][i];
            }
            for (int i = top + 1; i <= bottom; i++) {
                res[index++] = matrix[i][right];
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i > left; i--) {
                    res[index++] = matrix[bottom][i];
                }
                for (int i = bottom; i > top; i--) {
                    res[index++] = matrix[i][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }

}
