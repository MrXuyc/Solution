package com.mrxyc.solution.leecodesecondstep.system;

/**
 * 二进制求和
 *
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 */
public class Solution67 {

    public static void main(String[] args) {
        Solution67 solution67 = new Solution67();
        System.out.println(solution67.addBinary("111"
                , "110010"));
    }

    public String addBinary(String a, String b) {
        int aLength = a.length() - 1;
        int bLength = b.length() - 1;
        //进制
        int system = 2;
        int carry = 0;
        String res = "";
        while (aLength >= 0 || bLength >= 0) {
            if (aLength >= 0 && bLength >= 0) {
                Integer aVal = Integer.valueOf(String.valueOf(a.charAt(aLength)));
                Integer bVal = Integer.valueOf(String.valueOf(b.charAt(bLength)));
                int sum = aVal + bVal + carry;
                carry = sum / system;
                int subRes = sum % system;
                res = subRes + res;
                aLength--;
                bLength--;
            } else if (aLength >= 0) {
                if (carry > 0) {
                    res = addBinary(a.substring(0, aLength + 1), String.valueOf(carry)) + res;
                    carry = 0;
                } else {
                    res = a.substring(0, aLength + 1) + res;
                }
                aLength = -1;
            } else {
                if (carry > 0) {
                    res = addBinary(b.substring(0, bLength + 1), String.valueOf(carry)) + res;
                    carry = 0;
                } else {
                    res = b.substring(0, bLength + 1) + res;
                }
                bLength = -1;
            }
        }
        if (carry > 0) {
            res = carry + res;
        }
        return res;
    }
}
