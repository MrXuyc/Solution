package com.mrxyc.solution.leecode;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意: 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0] 输出: [0,0,1,1,2,2] 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class Solution75 {
    public static void main(String[] args) {
        Solution75 solution75 = new Solution75();
        solution75.sortColors(new int[]{2, 0, 2, 1, 1, 0});
    }

    public void sortColors(int[] nums) {
        int p0 = 0;
        int cur = 0;
        int p1 = nums.length - 1;
        while (cur <= p1) {
            if (nums[cur] == 0) {
                int tmp = nums[cur];
                nums[cur] = nums[p0];
                nums[p0] = tmp;
                p0++;
                cur++;
            } else if (nums[cur] == 2) {
                int tmp = nums[cur];
                nums[cur] = nums[p1];
                nums[p1] = tmp;
                p1--;
            } else {
                cur++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

//    public void sortColors(int[] nums) {
//        int[] colors = new int[]{0,0,0};
//        for(int i = 0;i<nums.length;i++){
//            int color = nums[i];
//            colors[color] = colors[color]+1;
//        }
//        int index = 0;
//        for(int i = 0;i<colors.length;i++){
//            while (colors[i]>0){
//                nums[index]  = i;
//                colors[i] = colors[i] -1;
//                index++;
//            }
//        }
//    }
}
