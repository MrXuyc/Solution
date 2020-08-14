package com.mrxyc.solution.leecodesecondstep.string;

public class Solution43 {

    public static void main(String[] args) {
        Solution43 solution43 = new Solution43();
        System.out.println(9 * 9);
        System.out.println(solution43.multiply1("9", "9"));
    }

    public String multiply1(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        //先遍历相乘 存入数组中
        int num1Length = num1.length();
        int num2Length = num2.length();
        int[] subRes = new int[num1Length + num2Length];
        for (int i = num1Length - 1; i >= 0; i--) {
            int num1Val = num1.charAt(i) - '0';
            for (int j = num2Length - 1; j >= 0; j--) {
                int num2Val = num2.charAt(j) - '0';
                subRes[i + j + 1] += num1Val * num2Val;
            }
        }
        //数组进位处理
        for (int i = num1Length + num2Length - 1; i > 0; i--) {
            subRes[i - 1] += subRes[i] / 10;
            subRes[i] = subRes[i] % 10;
        }
        //数组转换成字符串
        StringBuilder sb = new StringBuilder();
        if (subRes[0] >= 1) {
            sb.append(subRes[0]);
        }
        for (int i = 1; i < num1Length + num2Length; i++) {
            sb.append(subRes[i]);
        }
        return sb.toString();
    }


    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        String res = "";
        int num1Length = num1.length();
        int num2Length = num2.length();
        //选择长度短的当做
        if (num1Length < num2Length) {
            multiply(num2, num1);
        }
        int zeroCount = 0;
        int num2Index = num2Length - 1;
        while (num2Index >= 0) {
            String singleVal = singleMultiply(num1, num2.charAt(num2Index), zeroCount);
            res = add(res, singleVal);
            zeroCount++;
            num2Index--;
        }
        return res;
    }

    //num2为单个数字
    public String singleMultiply(String num1, char num2, int zeroCount) {
        StringBuilder sb = new StringBuilder();
        int num1Index = num1.length() - 1;
        int carry = 0;
        int num2Val = num2 - '0';
        while (num1Index >= 0) {
            int num1Val = num1.charAt(num1Index) - '0';
            int sum = (num1Val * num2Val) + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            num1Index--;
            if (num1Index < 0) {
                if (carry > 0) {
                    sb.append(carry);
                }
            }
        }
        StringBuilder sbZero = new StringBuilder();
        for (int i = 0; i < zeroCount; i++) {
            sbZero.append("0");
        }
        return sb.reverse().append(sbZero.toString()).toString();
    }

    //字符串相加
    public String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int num1Index = num1.length() - 1;
        int num2Index = num2.length() - 1;
        int carry = 0;
        while (num1Index >= 0 || num2Index >= 0) {
            int num1Val = num1Index < 0 ? 0 : num1.charAt(num1Index) - '0';
            int num2Val = num2Index < 0 ? 0 : num2.charAt(num2Index) - '0';
            int sum = carry + num1Val + num2Val;
            sb.append(sum % 10);
            carry = sum / 10;
            num1Index--;
            num2Index--;
            //马上跳出
            if (num1Index < 0 && num2Index < 0) {
                if (carry > 0) {
                    sb.append(carry);
                }
            }
        }
        return sb.reverse().toString();
    }

}
