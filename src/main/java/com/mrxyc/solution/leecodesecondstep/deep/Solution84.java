package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.Stack;

/**
 * 柱状图最大面积
 */
public class Solution84 {
    //暴力法
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        //遍历左边界
        for (int i = 0; i < heights.length; i++) {
            //遍历右边界
            for (int j = i; j < heights.length; j++) {
                //寻找高度瓶颈点
                int limitHeight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    if (limitHeight > heights[k]) {
                        limitHeight = heights[k];
                    }
                }
                //计算面积
                int res = limitHeight * (j - i + 1);
                if (res > max) {
                    max = res;
                }
            }
        }
        return max;
    }

    //优化后的暴力法  柱子高度第二次循环可直接判断出来，一直带着
    public int largestRectangleArea1(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                max = Math.max(max, minHeight * (j - i + 1));
            }
        }
        return max;
    }

    //分治  递归 确定最小柱子，得出 最小柱子面积 ，左边面积 右边面积 三者取最大
    public int largestRectangleArea2(int[] heights) {
        return deep(heights, 0, heights.length - 1);
    }

    private int deep(int[] heights, int start, int end) {
        //退出条件
        int max = 0;
        if (start > end) {
            return max;
        }
        //本层处理
        //获取最小值高度 计算面积
        int minHieghtIndex = start;
        for (int i = start; i <= end; i++) {
            if (heights[i] < heights[minHieghtIndex]) {
                minHieghtIndex = i;
            }
        }
        int curArea = heights[minHieghtIndex] * (end - start + 1);
        //递归
        //调用左右两端的递归
        int leftArea = deep(heights, start, minHieghtIndex - 1);
        int rightArea = deep(heights, minHieghtIndex + 1, end);
        //三个大小比较 返回最大面积
        max = Math.max(curArea, Math.max(leftArea, rightArea));
        //清除状态
        return max;
    }

    //栈  左边界确定，寻找右边界，如果右边界确定，则一直弹出计算，之后放新的面积
    public int largestRectangleArea4(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        //退出条件 循环结束
        for (int i = 0; i < heights.length; i++) {
            //宽度应该是（右边界）当前指针-(左边界（前一个的位置）+1)
            while (stack.peek() != -1 && heights[i] < stack.peek()) {
                max = Math.max(max, heights[stack.pop() * (i - (stack.peek() + 1))]);
            }
            stack.push(i);
        }
        //右边界确定，弹栈 弹一次 算一个面积比大小
        while (stack.peek() != -1) {
            max = Math.max(max, heights[stack.pop() * (heights.length - (stack.peek() + 1))]);
        }
        return max;
    }
}
