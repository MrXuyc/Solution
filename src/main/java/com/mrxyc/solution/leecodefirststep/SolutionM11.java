package com.mrxyc.solution.leecodefirststep;

import java.util.Arrays;

/**
 * 旋转数组的最小数字
 */
public class SolutionM11 {

    public static void main(String[] args) {
        SolutionM11 solutionM11 = new SolutionM11();
        System.out.println(solutionM11.minArray1(new int[]{1, 3, 5, 5, 6, 7, 8, 9}));
    }

    //循环遍历 O^N
    public int minArray(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }


    //排序取最小值
    public int minArray2(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[0];
    }

    //二分寻找转折点
    public int minArray1(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = numbers[mid];
            if (val < numbers[right]) {
                //转折节点在左边
                right = mid;
            } else if (val > numbers[right]) {
                //转折节点在右边
                left = mid + 1;
            } else {
                right--;
            }
        }
        return numbers[left];
    }
}
