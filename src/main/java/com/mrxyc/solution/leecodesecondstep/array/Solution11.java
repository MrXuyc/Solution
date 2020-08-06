package com.mrxyc.solution.leecodesecondstep.array;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i,
 * 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 *  
 *
 *
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 *  
 *
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7] 输出：49
 */
class Solution11 {

    public int maxArea(int[] height) {

        violence(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        return twoPoint(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
    }
    //解法1 面积大小 暴力法

    public int violence(int[] height) {
        int max = 0;
        //遍历左边界
        for (int i = 0; i < height.length - 1; i++) {
            //遍历右边界
            for (int j = i + 1; j < height.length; j++) {
                //寻找高度瓶颈点  最小点
                int minHeight = Math.min(height[i], height[j]);
                //计算面积
                int res = minHeight * (j - i);
                if (res > max) {
                    max = res;
                }
            }
        }
        return max;
    }

    //解法2 双指针
    public int twoPoint(int[] height) {
        int max = 0;
        int low = 0;
        int high = height.length - 1;
        while (low < high) {
            int width = high - low;
            int minHeight = Math.min(height[low], height[high]);
            int res = width * minHeight;
            max = Math.max(max, res);
            if (height[low] > height[high]) {
                high--;
            } else {
                low++;
            }
        }
        return max;
    }
}