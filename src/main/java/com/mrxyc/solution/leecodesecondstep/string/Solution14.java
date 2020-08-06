package com.mrxyc.solution.leecodesecondstep.string;

/**
 * 最长公共前缀
 */
public class Solution14 {
    //横向比较
    public String longestCommonPrefix(String[] strs) {
        //循环遍历 维护一个前缀
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            //退出条件，如果已经无公共前缀则直接退出
            if ("".equals(prefix)) {
                return prefix;
            }
            String curStr = strs[i];
            //获取当前前缀
            StringBuilder sb = new StringBuilder();
            int n = prefix.length() > curStr.length() ? curStr.length() : prefix.length();
            for (int j = 0; j < n; j++) {
                if (prefix.charAt(j) == curStr.charAt(j)) {
                    sb.append(prefix.charAt(j));
                } else {
                    break;
                }
            }
            prefix = sb.toString();
        }
        return prefix;
    }


    //纵向比较
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }
        int index = 0;
        for (; index < minLength; index++) {
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(index) != strs[i - 1].charAt(index)) {
                    return strs[0].substring(0, index);
                }
            }
        }
        return strs[0].substring(0, index);
    }


    //分治
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        return commonPrefix(strs, 0, strs.length - 1);
    }

    public String commonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        }
        int mid = start + (end - start) / 2;
        String sStart = commonPrefix(strs, start, mid);
        String sEnd = commonPrefix(strs, mid + 1, end);
        int index = 0;
        int n = Math.min(sStart.length(), sEnd.length());
        for (int i = 0; i < n; i++) {
            if (sStart.charAt(i) != sEnd.charAt(i)) {
                break;
            } else {
                index++;
            }
        }
        return sStart.substring(0, index);
    }

    //二分查找
    //取最小长度的字符串
    //最小长度的字符串的长度到0 进行二分查找
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        //获取最短的字符串长度
        int minLength = Integer.MAX_VALUE;
        String minStr = "";
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
                minStr = strs[i];
            }
        }
        int start = 0;
        int end = minLength - 1;
        int resIndex = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            String substring = minStr.substring(0, mid);
            //判断当前的字符串是否是最小字符
            boolean flag = check(substring, strs);
            if (flag) {
                resIndex = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return minStr.substring(0, resIndex);
    }

    private boolean check(String substring, String[] strs) {
        for (String str : strs) {
            if (!str.startsWith(substring)) {
                return false;
            }
        }
        return true;
    }


}
