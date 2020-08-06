package com.mrxyc.solution.leecodesecondstep.binarysearch;

import java.util.Arrays;

/**
 * 有序矩阵中第k个小的元素
 */
public class Solution378 {
    //二分查找
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + (right - left) / 2;
            //计算当前选中的mid 有多少个超过该值得
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                count = count + i + 1;
                j++;
            } else {
                i--;
            }
        }
        return count >= k;
    }


    public int kthSmallest(int[][] matrix, int k) {
        //直接排序 n^2 + nlogn
        int n = matrix.length;
        int[] array = new int[n * n];
        int arrayIndex = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[arrayIndex++] = matrix[i][j];
            }
        }
        Arrays.sort(array);
        return array[k - 1];
    }

    //k*n
    public int kthSmallest1(int[][] matrix, int k) {
        //归并排序
        int n = matrix.length;
        int[] sortIndex = new int[n];
        int val = -1;
        for (int i = 0; i < k; i++) {
            //选出最小index
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < n; j++) {
                if (sortIndex[j] < n && matrix[j][sortIndex[j]] < min) {
                    minIndex = j;
                    min = matrix[j][sortIndex[j]];
                }
            }
            val = min;
            sortIndex[minIndex] = sortIndex[minIndex] + 1;
        }
        return val;
    }
}
