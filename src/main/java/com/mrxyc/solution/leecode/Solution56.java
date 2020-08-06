package com.mrxyc.solution.leecode;

import java.util.Arrays;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]] 输出: [[1,6],[8,10],[15,18]] 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为
 * [1,6]. 示例 2:
 *
 * 输入: [[1,4],[4,5]] 输出: [[1,5]] 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Solution56 {

    public static void main(String[] args) {
        Solution56 solution56 = new Solution56();
        //int[][] merge = solution56.merge(new int[][]{{4, 6}, {4, 5}});
        //int[][] merge = solution56.merge(new int[][]{{8, 16}, {2, 9},{1, 3},  {15, 18}});
        int[][] merge = solution56.merge(new int[][]{});
        for (int[] i : merge) {
            System.out.println(Arrays.toString(i));
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return intervals;
        }
        //先排序，将数组 按照第一个值的大小排序。
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i][0] > intervals[j][0]) {
                    int[] temp = intervals[j];
                    intervals[j] = intervals[i];
                    intervals[i] = temp;
                }
            }
        }
        //之后遍历合并
        int[][] res = new int[intervals.length][2];
        //将第一个放入
        res[0] = intervals[0];
        //记录res需要操作的位置
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] re = res[index];
            int[] next = intervals[i];
            int re0 = re[0];
            int re1 = re[1];
            int next0 = next[0];
            int next1 = next[1];
            if (re1 >= next0 && re1 <= next1) {
                //区间交叉重合 取re0和next1构成区间
                res[index] = new int[]{re0, next1};
            } else if (re1 < next0) {
                //区间不重合 分别写入
                res[index] = new int[]{re0, re1};
                res[index + 1] = new int[]{next0, next1};
                //更新操作的index
                index++;
            } else if (re1 > next1) {
                //区间重合 取re0和interval1构成区间
                res[index] = new int[]{re0, re1};
            }
        }
        int[][] finalRes = new int[index + 1][2];
        System.arraycopy(res, 0, finalRes, 0, index + 1);
        return finalRes;
    }
}
