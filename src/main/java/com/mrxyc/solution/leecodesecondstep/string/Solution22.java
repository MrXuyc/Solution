package com.mrxyc.solution.leecodesecondstep.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 根据n 生成有效的括号组合
 */
public class Solution22 {
    private static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        Solution22 solution22 = new Solution22();
        solution22.generateParenthesis(3);
        for (String str : res) {
            System.out.println(str);
        }
    }

    public List<String> generateParenthesis(int n) {
        return generateParenthesis2(n);
    }

    //递归
    //递归先验

    private List<String> generateParenthesis2(int n) {
        deepPre(new ArrayList<>(), n, n, 2 * n);
        return res;
    }

    private void deepPre(List<String> stringList, int left, int right, int total) {
        //退出条件
        if (left == 0 && right == 0 && stringList.size() == total) {
            StringBuilder sb = new StringBuilder();
            for (String str : stringList) {
                sb.append(str);
            }
            res.add(sb.toString());
            return;
        }
        if (left > 0) {
            //本层处理
            List<String> leftList = new ArrayList<>(stringList);
            leftList.add("(");
            //递归
            deepPre(leftList, left - 1, right, total);
        }
        if (right > 0 && right > left) {
            List<String> rightList = new ArrayList<>(stringList);
            rightList.add(")");
            deepPre(rightList, left, right - 1, total);
        }
        //清除状态
    }

    //递归后验法

    private List<String> generateParenthesis1(int n) {
        deep(new ArrayList<>(), n, n);
        return res;
    }

    private void deep(List<String> strings, int left, int right) {
        //退出条件，已经用完
        if (left == 0 && right == 0) {
            //验证是否是符合要求的
            Stack<String> stack = new Stack<>();
            for (int i = 0; i < strings.size(); i++) {
                if (stack.isEmpty()) {
                    if (")".equals(strings.get(i))) return;
                    stack.push(strings.get(i));
                } else {
                    String peek = stack.peek();
                    if ("(".equals(peek) && ")".equals(strings.get(i))) {
                        stack.pop();
                    } else {
                        stack.push(strings.get(i));
                    }
                }
            }
            if (stack.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String str : strings) {
                    stringBuilder.append(str);
                }
                res.add(stringBuilder.toString());
            }
            return;
        }

        if (left > 0) {
            //本层处理
            List<String> leftStrs = new ArrayList<>(strings);
            leftStrs.add("(");
            // 递归
            deep(leftStrs, left - 1, right);
        }
        if (right > 0) {
            //本层处理
            List<String> rightStrs = new ArrayList<>(strings);
            rightStrs.add(")");
            // 递归
            deep(rightStrs, left, right - 1);
        }
        //清除状态
    }


}
