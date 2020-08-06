package com.mrxyc.solution.leecodesecondstep.array;

import java.util.Arrays;

/**
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近 
 * target （最接近表示两者之差的绝对值最小）。
 *
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 *
 * 请注意，答案不一定是 arr 中的数字。
 *
 * 1 <= arr.length <= 10^4
 *
 * 1 <= arr[i], target <= 10^5
 */
public class Solution1300 {
    public int findBestValue(int[] arr, int target) {
        int res = Integer.MAX_VALUE;
        return res;
    }

    public static void main(String[] args) {
        Solution1300 solution1300 = new Solution1300();
        solution1300.findBestValue1(new int[]{2, 3, 5}, 10);
    }


    //枚举 二分查找
    public int findBestValue1(int[] arr, int target) {
        Arrays.sort(arr);
        //计算索引前得值
        int[] prefixVal = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                prefixVal[0] = 0;
            } else {
                prefixVal[i] = prefixVal[i - 1] + arr[i - 1];
            }
        }
        //确定上下界
        //下界0  上界 数组中最大值
        int low = 0;
        int high = arr[arr.length - 1];
        int diff = target;
        int res = 0;
        for (int i = low; i <= high; i++) {
            //获取当前位置
            int index = Arrays.binarySearch(arr, i);
            if (index < 0) {
                index = -index - 1;
            }
            //计算公式 前面的和+ x*arr.length-index
            int cur = prefixVal[index] + i * (arr.length - index);
            int curDiff = Math.abs(target - cur);
            if (curDiff < diff) {
                diff = curDiff;
                res = i;
            }
        }
        return res;

    }


    //双层二分查找
    public int findBestValue2(int[] arr, int target) {
        int res = 0;
        //通过二分查找当前上下界中哪个值最接近target
        Arrays.sort(arr);
        int[] prefixVal = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            prefixVal[i] = prefixVal[i - 1] + arr[i - 1];
        }
        int low = 0;
        int high = arr[arr.length - 1];
        int n = arr.length;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int index = Arrays.binarySearch(arr, mid);
            if (index < 0) {
                index = -index - 1;
            }
            int cur = prefixVal[index] + mid * (n - index);
            if (cur <= target) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        int smallDiff = check(arr, ans);
        int bigDiff = check(arr, ans + 1);
        return Math.abs(target - smallDiff) <= Math.abs(target - bigDiff) ? ans : ans + 1;
    }

    private int check(int[] arr, int x) {
        int ret = 0;
        for (int num : arr) {
            ret += Math.min(num, x);
        }
        return ret;
    }
}
