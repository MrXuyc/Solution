package com.mrxyc.solution.leecodefirststep;

/**
 * 字符串相加
 */
public class Solution415 {
    public String addStrings(String num1, String num2) {
        int num1Index = num1.length() - 1;
        int num2Index = num2.length() - 1;
        int add = 0;
        StringBuilder sb = new StringBuilder();
        while (num1Index >= 0 || num2Index >= 0 || add != 0) {
            int x = num1Index >= 0 ? num1.charAt(num1Index) - '0' : 0;
            int y = num2Index >= 0 ? num2.charAt(num2Index) - '0' : 0;
            int sum = x + y + add;
            sb.append(sum % 10);
            add = sum / 10;
            num1Index--;
            num2Index--;
        }
        sb.reverse();
        return sb.toString();
    }
}
