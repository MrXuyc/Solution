package com.mrxyc.solution.leecodesecondstep.array;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 *  
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。  
 *
 * 示例:
 *
 * 输入: nums1 = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

    }

    public static void main(String[] args) {
        Solution88 solution88 = new Solution88();
        solution88.merge3(new int[]{2, 0}, 1, new int[]{1}, 1);
    }

    //合并后直接排序

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    //迭代 双指针从后往前
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        // 插入
        int insertIndex = nums1.length - 1;
        int nums1Index = m - 1;
        int nums2Index = n - 1;
        //退出条件 有的指针到了头
        while (nums1Index >= 0 && nums2Index >= 0) {
            if (nums2[nums2Index] >= nums1[nums1Index]) {
                nums1[insertIndex] = nums2[nums2Index];
                nums2Index--;
            } else {
                nums1[insertIndex] = nums1[nums1Index];
                nums1Index--;
            }
            insertIndex--;
        }
        //看哪个指针没有到头 直接复制到目标
        //如果是nums1没结束 可以直接使用结果  不用变动
//        if (nums1Index >= 0) {
//            System.arraycopy(nums1, 0, nums1, 0, nums1Index + 1);
//        }

        if (nums2Index >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, nums2Index + 1);
        }

    }

    //迭代 双指针从前往后
    private void merge1(int[] nums1, int m, int[] nums2, int n) {
        int nums1Index = 0;
        int nums2Index = 0;
        //遍历nums1 nums2
        //退出条件指针有一个移动到m,n
        while (nums1Index < m && nums2Index < n) {
            //指针指向的数字进行判断
            //如果nums2大于等于nums1则移动nums1指针
            if (nums2[nums2Index] >= nums1[nums1Index]) {
                nums1Index++;
            } else {
                //移动是否可以直接system.copy
                System.arraycopy(nums1, nums1Index, nums1, nums1Index + 1, nums1.length - nums1Index - 1);
                //如果小于则移动插入nums1Index-1位置 并且m+1
                nums1[nums1Index] = nums2[nums2Index];
                nums1Index++;
                nums2Index++;
                m++;
            }
        }
        //nums1有剩 不用处理  本来就是有序的
        //nums2有剩，直接补充到后面
        if (nums2Index < n) {
            System.arraycopy(nums2, nums2Index, nums1, m, n - nums2Index);
        }
    }

    private void merge3(int[] nums1, int m, int[] nums2, int n) {
        //copy出一个nums1数组 用于判断
        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);
        int nums1Index = 0;
        int nums2Index = 0;
        int nums1CopyIndex = 0;
        //直接修改nums1
        while (nums1CopyIndex < m && nums2Index < n) {
            if (nums1Copy[nums1CopyIndex] <= nums2[nums2Index]) {
                nums1[nums1Index] = nums1Copy[nums1CopyIndex];
                nums1CopyIndex++;
                nums1Index++;
            } else {
                nums1[nums1Index] = nums2[nums2Index];
                nums1Index++;
                nums2Index++;
            }
        }
        //如果有指针没满足 则直接copy
        //copy的时候长度 如果需要完全copy desc 则length=des.length-desIndex
        // 或者根据需要 指定长度
        if (nums1CopyIndex < m) {
            System.arraycopy(nums1Copy, nums1CopyIndex, nums1, nums1Index, m - nums1CopyIndex);
        }

        if (nums2Index < n) {
            System.arraycopy(nums2, nums2Index, nums1, nums1Index, n - nums2Index);
        }
        System.out.println("");
    }

}
