package com.mrxyc.solution.leecodesecondstep.array;

import java.util.Arrays;

/**
 * 长度最小的子数组
 *
 * nums中满足的最小连续子数组（不能排序）
 *
 * O n  O NlogN
 */
public class Solution209 {
    public static void main(String[] args) {
        Solution209 solution209 = new Solution209();
        System.out.println(solution209.minSubArrayLen2(7, new int[]{2, 3, 1, 2, 4, 3}));
    }


    //暴力法  n^2
    public int minSubArrayLen2(int s, int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int curVal = 0;
            int curCount = 0;
            for (int j = i; j < nums.length; j++) {
                curVal = curVal + nums[i];
                curCount++;
                //如果大于该值则进行赋值
                if (curVal >= s) {
                    count = curCount;
                    break;
                }
            }
        }
        return count;
    }

    public int minSubArrayLen3(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        //计算前缀和
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        //循环 寻找右边界
        for (int i = 1; i <= n; i++) {
            int target = s + nums[i - 1];
            int index = Arrays.binarySearch(sums, target);
            if (index < 0) {
                index = -index - 1;
            }
            if (index <= n) {
                ans = Math.min(ans, index - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    //n 滑动窗口 双指针 小于则向右移动end 成功后则移动start 如果一直成功移动start
    public int minSubArrayLen1(int s, int[] nums) {
        int count = Integer.MAX_VALUE;
        int n = nums.length;
        int start = 0;
        int end = 0;
        int sum = 0;
        while (end < n) {
            sum = sum + nums[end];
            while (sum >= s) {
                count = Math.min(count, end - start + 1);
                sum = sum - nums[start];
                start++;
            }
            end++;
        }
        return count == Integer.MAX_VALUE ? 0 : count;
    }


    //nlogn 二分查找确定间隔 加循环
    public int minSubArrayLen(int s, int[] nums) {
        int count = 0;
        //1-nums.length 二分查找找最小可支持的区间
        int minLen = 1;
        int maxLen = nums.length;
        while (minLen <= maxLen) {

            int midLen = minLen + (maxLen - minLen) / 2;
            int curLen = 0;
            int curCount = 0;
            int maxCount = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (curLen < midLen) {
                    //没满足midLen 一直加
                    curCount = curCount + nums[i];
                    maxCount = Math.max(maxCount, curCount);
                    curLen++;
                } else {
                    //满足了midLen 加当前 减去i-midLen
                    curCount = curCount + nums[i] - nums[i - midLen];
                    maxCount = Math.max(maxCount, curCount);
                }
            }
            if (maxCount > s) {
                maxLen = midLen - 1;
                count = midLen;
            } else if (maxCount < s) {
                minLen = midLen + 1;
            } else {
                return midLen;
            }
        }
        return count;
    }
}
