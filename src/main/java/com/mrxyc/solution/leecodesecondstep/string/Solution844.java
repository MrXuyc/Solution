package com.mrxyc.solution.leecodesecondstep.string;

import java.util.Stack;

/**
 * 比较含退格的字符串
 * <p>
 * #代表退格（删除）
 */
public class Solution844 {
    public static void main(String[] args) {
        Solution844 solution844 = new Solution844();
        System.out.println(solution844.backspaceCompare1("bbbextm"
                , "bbb#extm"));
    }

    public boolean backspaceCompare(String S, String T) {
        String s = convert(S);
        String t = convert(T);
        return s.equals(t);
    }

    public String convert(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        return stack.toString();
    }

    //双指针
    public boolean backspaceCompare1(String S, String T) {
        int sIndex = S.length() - 1;
        int tIndex = T.length() - 1;
        while (sIndex >= 0 || tIndex >= 0) {
            int sDelCount = 0;
            while (sIndex >= 0) {
                if (S.charAt(sIndex) == '#') {
                    sDelCount++;
                    sIndex--;
                } else {
                    if (sDelCount > 0) {
                        sDelCount--;
                        sIndex--;
                    } else {
                        break;
                    }
                }
            }
            int tDelCount = 0;
            while (tIndex >= 0) {
                if (T.charAt(tIndex) == '#') {
                    tDelCount++;
                    tIndex--;
                } else {
                    if (tDelCount > 0) {
                        tDelCount--;
                        tIndex--;
                    } else {
                        break;
                    }
                }
            }
            if (sIndex >= 0 && tIndex >= 0) {
                //出现了不同自符
                if (S.charAt(sIndex) != T.charAt(tIndex)) {
                    return false;
                }
            } else {
                //符号数对不上
                if (sIndex >= 0 || tIndex >= 0) {
                    return false;
                }
            }
            sIndex--;
            tIndex--;
        }
        return true;
    }
}
