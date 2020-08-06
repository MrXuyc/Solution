package com.mrxyc.solution.leecodesecondstep.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 滑动窗口最大值
 */

public class Solution239 {

    public static void main(String[] args) {
        Solution239 solution239 = new Solution239();
        solution239.maxSlidingWindow3(new int[]{-95, 92, -85, 59, -59, -14, 88, -39, 2, 92, 94, 79, 78, -58, 37, 48, 63, -91, 91, 74, -28, 39, 90, -9, -72, -88, -72, 93, 38, 14, -83, -2, 21, 4, -75, -65, 3, 63, 100, 59, -48, 43, 35, -49, 48, -36, -64, -13, -7, -29, 87, 34, 56, -39, -5, -27, -28, 10, -57, 100, -43, -98, 19, -59, 78, -28, -91, 67, 41, -64, 76, 5, -58, -89, 83, 26, -7, -82, -32, -76, 86, 52, -6, 84, 20, 51, -86, 26, 46, 35, -23, 30, -51, 54, 19, 30, 27, 80, 45, 22}, 10);

    }


    //暴力法
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < res.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
    }


    //队列法
    public int[] maxSlidingWindow3(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length + 1 - k];
        int resIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            //保证第一个是最大值
            //如果i>=k 说明需要判断目前最大值，是否过期，如果过期则删除
            if (i >= k && deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            //添加到尾部的时候，需要向前消除
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            //放入尾部
            deque.addLast(nums[i]);
            //如果i+1>=k 则需要输出结果
            if (i + 1 >= k) {
                res[resIndex++] = deque.peekFirst();
            }
        }
        return res;
    }


    //动态规划
    //分解子问题 寻找所有分段的最大值 合并成最大值
    public int[] maxSlidingWindow2(int[] nums, int k) {
        return nums;
    }

    public int maxValue(int[] nums, int k, int start, int end) {
        return k;
    }
}
