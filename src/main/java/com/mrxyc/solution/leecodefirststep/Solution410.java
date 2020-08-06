package com.mrxyc.solution.leecodefirststep;

import java.util.Arrays;

/**
 * 分割数组的最大值
 *
 *
 * 分割成m个连续数组，数组的最大值最小
 */
public class Solution410 {

    public static void main(String[] args) {
        Solution410 solution410 = new Solution410();
        System.out.println(solution410.splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

    //动态规划
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        //计算前缀和数组
        int[] preVal = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preVal[i] = preVal[i - 1] + nums[i - 1];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], preVal[i] - preVal[k]));
                }
            }
        }
        return dp[n][m];
    }

    //二分查找
    public int splitArray1(int[] nums, int m) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            sum = sum + nums[i];
        }
        int left = max;
        int right = sum;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int mid, int m) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > mid) {
                //已经超过mid 重新计算
                cnt++;
                sum = nums[i];
            } else {
                //没超过继续加
                sum = sum + nums[i];
            }
        }
        //如果cnt小于m则认为是 当前选择值过大
        return cnt <= m;
    }
}
