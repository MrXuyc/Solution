package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

import java.util.Stack;

import javafx.util.Pair;

/**
 * 最长有效括号
 */
public class Solution32 {

    public static void main(String[] args) {
        Solution32 solution32 = new Solution32();
        System.out.println(solution32.longestValidParentheses1(")()())"));
    }

    //动态规划
    public int longestValidParentheses2(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    //如果是()则在原有基础+2
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    //如果是(()())   那么这个值应该是 ()()的dp[i-1] + 第一个( +2 的和
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] + 2 : 2);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }


    //栈
    public int longestValidParentheses1(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    //更新有效起始位  只可能是前面是右括号
                    stack.push(i);
                } else {
                    //前面是消除的左括号。计算区间值
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public int longestValidParentheses3(String s) {
        int max = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }


    //遍历取标识位
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] record = new int[chars.length];
        Stack<Pair<Character, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(chars[0], 0));
        int index = 1;
        while (!stack.isEmpty() && index <= chars.length) {
            if (')' == stack.peek().getKey()) {
                Pair<Character, Integer> cur = stack.pop();
                //如果遇到）则尝试弹栈 弹栈成功则记录长度 弹栈失败则记录当前值为0
                if (!stack.isEmpty() && '(' == stack.peek().getKey()) {
                    Integer curIndex = cur.getValue();
                    Pair<Character, Integer> pre = stack.pop();
                    Integer preIndex = pre.getValue();
                    record[preIndex] = 1;
                    record[curIndex] = 1;
                }
            }
            if (index == chars.length) {
                break;
            }
            if (index < chars.length) {
                stack.push(new Pair<>(chars[index], index));
                index++;
            }
        }
        int max = 0;
        int curMax = 0;
        for (int i = 0; i < record.length; i++) {
            if (record[i] == 1) {
                curMax++;
            } else {
                max = Math.max(max, curMax);
                curMax = 0;
            }
        }
        return curMax > max ? curMax : max;
    }
}
