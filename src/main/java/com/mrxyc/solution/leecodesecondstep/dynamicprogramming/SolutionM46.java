package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成
 * “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 12258
 *
 * 输出: 5
 *
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 把一个数字拆分 按顺序组合成一个数组  组合值区间是<=25
 */
public class SolutionM46 {

    public static void main(String[] args) {
        SolutionM46 solutionM46 = new SolutionM46();
        solutionM46.translateNum(12258);
    }

    public int translateNum(int num) {
        int res = 1;
        int p = 0;
        int q = 0;
        String src = String.valueOf(num);
        for (int i = 0; i < src.length(); i++) {
            p = q;
            q = res;
            res = 0;
            res += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                res += p;
            }
        }
        return res;
    }


    public int translateNum1(int num) {
        int r = 1;
        //i-2
        int p = 0;
        //i-1
        int q = 0;
        String src = String.valueOf(num);
        for (int i = 0; i < src.length(); i++) {
            //把当前结果给 n-1
            q = r;
            //i-1直接加
            r = 0;
            r = r + q;
            //i-2需要验证是否符合区间
            //0没有i-2
            if (i != 0) {
                String sub = src.substring(i - 1, i + 1);
                if (sub.compareTo("25") <= 0 && sub.compareTo("10") >= 0) {
                    r = r + p;
                }
            }
            //把n-1结果给 n-2
            p = q;
        }
        return r;
    }
}
