package com.mrxyc.solution.leecodesecondstep.string;

/**
 * 验证回文串
 *
 * 只考虑字符串和数字
 */
public class Solution125 {
    //api法 利用 reverse equals
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        String s1 = sb.toString();
        return sb.reverse().toString().equalsIgnoreCase(s1);
    }


    public static void main(String[] args) {
        Solution125 solution125 = new Solution125();
        System.out.println(solution125.isPalindrome1("A man, a plan, a canal: Panama"));
    }

    //双指针
    public boolean isPalindrome1(String s) {
        int start = 0;
        int end = s.length() - 1;
        boolean res = true;
        while (start < end) {
            while (start < end && (!(Character.isDigit(s.charAt(start)) || Character.isLetter(s.charAt(start))))) {
                start++;
            }
            while (start < end && (!(Character.isDigit(s.charAt(end)) || Character.isLetter(s.charAt(end))))) {
                end--;
            }
            if (start < end && !String.valueOf(s.charAt(start)).equalsIgnoreCase(String.valueOf(s.charAt(end)))) {
                return false;
            }
            start++;
            end--;

        }
        return res;
    }
}
