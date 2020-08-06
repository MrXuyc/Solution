package com.mrxyc.solution.leecodesecondstep.array;

/**
 * 两数之和 输入有序数组
 */
public class Solution167 {
    //循环暴力
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    res[0] = i + 1;
                    res[1] = j + 1;
                    return res;
                }
            }
        }
        return res;
    }


    //双指针
    public int[] twoSum1(int[] numbers, int target) {
        int[] res = new int[2];
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                res[0] = left + 1;
                res[1] = right + 1;
                break;
            }
        }
        return res;
    }


    //二分查找
    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int findVal = target - numbers[i];
            int left = i + 1;
            int right = numbers.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midVal = numbers[mid];
                if (findVal < midVal) {
                    right = mid - 1;
                } else if (findVal > midVal) {
                    left = mid + 1;
                } else {
                    return new int[]{i + 1, mid + 1};
                }
            }
        }
        return new int[0];
    }

}
