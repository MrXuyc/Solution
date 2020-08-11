package com.mrxyc.solution.leecodefirststep;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制子串
 *
 * @author yanchun.xu
 */
public class Solution696 {

    public static void main(String[] args) {
        Solution696 solution696 = new Solution696();
        System.out.println(solution696.countBinarySubStrings("10101"));
    }

    //暴力  超出时间限制
    public int countBinarySubStrings(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isMatch(s, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean isMatch(String s, int i, int j) {
        int length = j - i + 1;
        //基数不可能成功
        if (length % 2 != 0) {
            return false;
        }
        //计算数目是否符合
        int zeroCount = 0;
        int oneCount = 0;
        int pre = s.charAt(i);
        int mid = i + (j - i) / 2;
        for (; i <= j; i++) {
            //前面需要和前面相等
            if (i <= mid && s.charAt(i) != pre) {
                return false;
            }
            //后面需要和前面不等
            if (i > mid && s.charAt(i) == pre) {
                return false;
            }
            if ('0' == s.charAt(i)) {
                zeroCount++;
            } else if ('1' == s.charAt(i)) {
                oneCount++;
            }
        }
        return zeroCount == oneCount;
    }

    //一次循环 记录个数 取最小值
    public int countBinarySubString1(String s) {
        int n = s.length();
        int res = 0;
        int index = 0;
        List<Integer> list = new ArrayList<>();
        while (index < n) {
            char curChar = s.charAt(index);
            int count = 0;
            while (index < n && s.charAt(index) == curChar) {
                count++;
                index++;
            }
            list.add(count);
        }
        for (int i = 1; i < list.size(); i++) {
            res += Math.min(list.get(i), list.get(i - 1));
        }
        return res;
    }
}
