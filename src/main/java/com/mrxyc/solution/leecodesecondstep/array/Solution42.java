package com.mrxyc.solution.leecodesecondstep.array;

import java.util.Stack;

public class Solution42 {
    public static void main(String[] args) {
        Solution42 solution42 = new Solution42();
        solution42.trapStackOpt(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }


    public int trapStackOpt(int[] height) {
        int res = 0;
        //对待数组的题目 需要设计索引位间距离 一般存储索引位
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            //复合水槽 一层一层处理。新遍历的元素，只要跟栈顶元素判断即可。如果大于栈顶元素则进行处理。
            while (!stack.empty() && height[i] > height[stack.peek()]) {
                int h = height[stack.pop()];
                //为空 没有左边界
                if (stack.empty()) {
                    break;
                }
                //宽度 采用索引位-索引位-1
                int weight = i - stack.peek() - 1;
                //高度 等于两个索引位对应的高度-最小值
                int high = Math.min(height[i], height[stack.peek()]) - h;
                //面积则等于 宽度*(高度-已经算过的高度)
                res = res + weight * high;
                //消除当前元素之前一直到左边界元素之前的值 并记录已经销毁的高度 如果栈不等于空则要记录上述所说高度

            }
            stack.push(i);
        }
        return res;
    }

    //栈   前面处理一次 ，后面处理一次
    public int trapStack(int[] height) {
        int res = 0;
        //异常判断 不满足蓄水池
        if (height.length < 2) {
            return res;
        }
        int leftHeight = height[0];
        int index = 0;
        //依次放入到栈
        Stack<Integer> stack = new Stack();
        stack.push(height[0]);
        for (int i = 1; i < height.length; i++) {
            if (height[i] >= leftHeight) {
                //如果大于等于则弹栈
                //弹栈需要计算大小。可用之前的高度和两个栈之间的宽度相乘，之后减去每个弹栈的大小
                int count = -1;
                int need = -leftHeight;
                while (!stack.empty()) {
                    need = need + stack.pop();
                    count++;
                }
                res = res + ((count * leftHeight) - need);
                leftHeight = height[i];
                index = i;
            }
            //如果小于则放栈
            stack.push(height[i]);
        }
        //如果不等于空则说明 有没有处理的。需要反向走一边
        if (!stack.empty()) {
            stack.clear();
            leftHeight = height[height.length - 1];
            stack.push(height[height.length - 1]);
            for (int i = height.length - 2; i >= index; i--) {
                if (height[i] >= leftHeight) {
                    //如果大于等于则弹栈
                    //弹栈需要计算大小。可用之前的高度和两个栈之间的宽度相乘，之后减去每个弹栈的大小
                    int count = -1;
                    int need = -leftHeight;
                    while (!stack.empty()) {
                        need = need + stack.pop();
                        count++;
                    }
                    res = res + ((count * leftHeight) - need);
                    leftHeight = height[i];
                }
                //如果小于则放栈
                stack.push(height[i]);
            }
        }

        return res;
    }
}
