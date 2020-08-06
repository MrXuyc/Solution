package com.mrxyc.solution.leecodesecondstep.binarysearch;

/**
 * 搜索二维矩阵 二维有序矩阵
 */
public class Solution74 {

    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int left = 0;
        int right = n * m - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = matrix[mid / m][mid % m];
            if (val == target) {
                return true;
            } else if (val > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            int min = matrix[i][0];
            int max = matrix[i][matrix[i].length - 1];
            if (target >= min && target <= max) {
                //选择符合预期的数组
                int left = 0;
                int right = matrix[i].length - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    int val = matrix[i][mid];
                    if (val == target) {
                        return true;
                    } else if (val > target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                break;
            }
        }
        return false;

    }
}
