package com.mrxyc.solution.leecodesecondstep.num;

/**
 * 回文数
 */
public class Solution9 {
    public boolean isPalindrome(int x) {
        return isPalindrome1(x);
    }

    /**
     * 反转相等比较
     */
    private boolean isPalindrome1(int x) {
        String s = String.valueOf(x);
        String s1 = new StringBuffer(s).reverse().toString();
        return s.equals(s1);
    }

    //双指针比较
    private boolean isPalindrome2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    public boolean isPalindrome3(int x) {
        //负数，或者10的倍数 天生不回文
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        //将数字通过余数拆开
        int revertNum = 0;
        while (x > revertNum) {
            revertNum = revertNum * 10 + x % 10;
            x = x / 10;
        }
        //如果是奇数个元素 需要revertNum去除掉末尾元素
        return x == revertNum || x == revertNum / 10;
    }
}
