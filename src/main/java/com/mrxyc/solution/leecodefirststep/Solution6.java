package com.mrxyc.solution.leecodefirststep;

import java.util.ArrayList;
import java.util.List;

/**
 * Z字形变换
 *
 * L   C   I   R
 *
 * E T O E S I I G
 *
 * E   D   H   N
 */
public class Solution6 {

    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        System.out.println(solution6.convert("LEETCODEISHIRING", 3));
    }

    public String convert1(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                //到了边界进行换方向
                goingDown = !goingDown;
            }
            curRow = curRow + (goingDown ? 1 : -1);
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder row : rows) {
            sb.append(row);
        }
        return sb.toString();
    }


    public String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int cycleLength = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j = j + cycleLength) {
                sb.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLength - i < n) {
                    sb.append(s.charAt(j + cycleLength - i));
                }
            }
        }
        return sb.toString();
    }


    //寻找索引规则
    public String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            res.add("");
        }
        int[] direction = new int[2 * numRows - 2];
        for (int i = 1; i < direction.length; i++) {
            if (i < numRows) {
                direction[i] = direction[i - 1] + 1;
            } else {
                direction[i] = direction[i - 1] - 1;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            int index = i % direction.length;
            String remove = res.remove(direction[index]);
            res.add(direction[index], remove + s.charAt(i));
        }
        //将字符串拼接输出
        StringBuilder sb = new StringBuilder();
        for (String r : res) {
            sb.append(r);
        }
        return sb.toString();
    }
}
