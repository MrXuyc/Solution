package com.mrxyc.solution.leecodesecondstep.array;

/**
 * 最佳观光组合
 *
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 * 示例：
 *
 * 输入：[8,1,5,2,6] 输出：11 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 */
public class Solution1014 {

    public static void main(String[] args) {
        Solution1014 solution1014 = new Solution1014();
        int i = solution1014.maxScoreSightseeingPair(new int[]{6, 9, 10, 5, 9, 10, 4, 5});
        System.out.println(i);
    }

    //暴力法 双重遍历
    public int maxScoreSightseeingPair1(int[] A) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int curRes = A[i] + A[j] + i - j;
                res = Math.max(res, curRes);
            }
        }
        return res;
    }

    //
    public int maxScoreSightseeingPair(int[] A) {
        int res = Integer.MIN_VALUE;
        int max = A[0];
        for (int j = 1; j < A.length; j++) {
            //
            res = Math.max(res, max + A[j] - j);
            //获取之前遍历的结果最大值
            max = Math.max(max, A[j] + j);
        }
        return res;
    }
}
