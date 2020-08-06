package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 最长重复子数组
 */
public class Solution718 {

    public static void main(String[] args) {
        Solution718 solution718 = new Solution718();

        int[] A = new int[]{1, 2, 3, 2, 1};
        int[] B = new int[]{3, 2, 1, 4, 7};
        System.out.println(solution718.findLength(A, B));
    }

    public int findLength(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int max = 0;
        int[][] counts = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    //相等取左上角+1
                    counts[i][j] = counts[i - 1][j - 1] + 1;
                } else {
                    counts[i][j] = 0;
                }
                max = Math.max(max, counts[i][j]);
            }
        }
        return max;
    }
}
