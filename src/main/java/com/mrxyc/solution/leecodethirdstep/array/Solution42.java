package com.mrxyc.solution.leecodethirdstep.array;

import java.util.Stack;

/**
 * 接雨水
 */
public class Solution42 {

    public static void main(String[] args) {
        Solution42 solution42 = new Solution42();
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution42.trap(height));
    }

    public int trap(int[] height) {
        int res = 0;
        //存index
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                //获取中间低槽的高度
                int h = height[stack.pop()];
                //如果没有左边界则退出
                if (stack.isEmpty()) {
                    break;
                }
                //获取宽度
                int weight = i - stack.peek() - 1;
                //减去已经计算的高度
                int high = Math.min(height[i], height[stack.peek()]) - h;
                res += weight * high;
            }

            stack.push(i);
        }
        return res;
    }
}
