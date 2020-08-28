package com.mrxyc.solution.leecodesecondstep.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 是否是重复子串
 */
public class Solution459 {
    public static void main(String[] args) {
        Solution459 solution459 = new Solution459();
        System.out.println(solution459.repeatedSubstringPattern("aabaaba"));
    }

    public boolean repeatedSubstringPattern2(String s) {
        int length = s.length();
        for (int i = 1; i * 2 <= length; i++) {
            if (length % i == 0) {

            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern1(String s) {
        //去掉第一个字符之后验证是否有重复字符串
        return (s + s).indexOf(s, 1) != s.length();
    }


    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        //特殊情况
        if (length <= 1) {
            return false;
        }
        boolean res = true;
        int mid = length / 2;
        //存储可能存在的子串
        Set<String> data = new HashSet<>();
        for (int i = 0; i < mid; i++) {
            data.add(s.substring(0, i + 1));
        }
        for (String substring : data) {
            int subLength = substring.length();
            //如果长度不匹配则直接下一个
            if (length % subLength != 0) {
                res = false;
                continue;
            }
            int leftIndex = 0;
            int rightIndex = subLength;
            while (rightIndex <= length) {
                String index = s.substring(leftIndex, rightIndex);
                if (!index.equals(substring)) {
                    res = false;
                    break;
                } else {
                    res = true;
                }
                leftIndex = leftIndex + subLength;
                rightIndex = rightIndex + subLength;
            }
            //如果有已经匹配好的 则直接退出
            if (res) {
                return res;
            }
        }
        return res;
    }
}
