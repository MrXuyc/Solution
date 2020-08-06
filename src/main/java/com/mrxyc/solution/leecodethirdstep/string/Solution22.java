package com.mrxyc.solution.leecodethirdstep.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 */
public class Solution22 {


    public static void main(String[] args) {
        Solution22 solution22 = new Solution22();
        solution22.generateParenthesis(3);
        System.out.println(solution22.res);
    }

    List<String> res = new ArrayList<>();

    //递归  先验
    public List<String> generateParenthesis(int n) {
        deep("", n, n);
        return res;
    }

    private void deep(String subRes, int leftN, int rightN) {
        //退出条件
        if (leftN == 0 && rightN == 0) {
            res.add(subRes);
            return;
        }
        //本层处理
        //递归 递归条件
        //必须先放左括号，才可放右括号
        if (leftN < rightN && rightN > 0) {
            deep(subRes + ")", leftN, rightN - 1);
        }
        if (leftN > 0) {
            deep(subRes + "(", leftN - 1, rightN);
        }
    }


}
