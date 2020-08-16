package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 移动盒子
 */
public class Solution546 {

    public static void main(String[] args) {
        Solution546 solution546 = new Solution546();
        int[] boxes = new int[]{6, 3, 6, 5, 6, 7, 6, 6, 8, 6};
        int res = solution546.removeBoxes(boxes);
        System.out.println(res);
    }

    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
    }

    private int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (l > r) {
            return 0;
        }
        //如果已经计算好了，则直接返回
        if (dp[l][r][k] != 0) {
            return dp[l][r][k];
        }
        //寻找最左边的r
        //
        while (r > 1 && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k]
                        , calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
            }
        }
        return dp[l][r][k];
    }
}
