package com.mrxyc.solution.leecodesecondstep.deep;

/**
 * 图像渲染
 */
public class Solution733 {

    public static void main(String[] args) {
        Solution733 solution733 = new Solution733();
        int[][] image = new int[][]{{0, 0, 0}, {0, 1, 1}};
        solution733.floodFill(image, 1, 1, 1);
        System.out.println("");
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }
        deep(image, sr, sc, newColor, image[sr][sc], new boolean[image.length][image[0].length]);
        return image;
    }


    public void deep(int[][] image, int sr, int sc, int newColor, int curColor, boolean[][] visited) {
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if (image[sr][sc] == curColor) {
            image[sr][sc] = newColor;
            visited[sr][sc] = true;
        } else {
            return;
        }
        for (int i = 0; i < direction.length; i++) {
            int curSr = sr + direction[i][0];
            int curSc = sc + direction[i][1];
            if (curSr >= 0 && curSr < image.length && curSc >= 0 && curSc < image[curSr].length
                    && !visited[curSr][curSc]) {
                deep(image, curSr, curSc, newColor, curColor, visited);
            }
        }
    }
}
