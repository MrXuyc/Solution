package com.mrxyc.solution.leecodefirststep;

/**
 * 翻转字符串中的单词
 * <p>
 * 只翻转空格字符
 */
public class Solution557 {

    public static void main(String[] args) {
        Solution557 solution557 = new Solution557();
        System.out.println(solution557.reverseWords2("Let's take LeetCode contest"));
    }

    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                res.append(sb.reverse());
                res.append(" ");
                sb = new StringBuilder();
            } else {
                sb.append(s.charAt(i));
            }
        }
        if (sb.capacity() > 0) {
            res.append(sb.reverse());
        }
        return res.toString();
    }

    public String reverseWords1(String s) {
        String[] subStrs = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < subStrs.length; i++) {
            if (i != 0) {
                res.append(" ");
            }
            res.append(new StringBuilder(subStrs[i]).reverse());
        }
        return res.toString();
    }

    public String reverseWords2(String s) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        int left = 0;
        while (left < length) {
            int start = left;
            while (left < length && s.charAt(left) != ' ') {
                left++;
            }
            for (int i = left; i > start; i--) {
                sb.append(s.charAt(i - 1));
            }
            while (left < length && s.charAt(left) == ' ') {
                left++;
                sb.append(" ");
            }

        }
        return sb.toString();
    }
}
