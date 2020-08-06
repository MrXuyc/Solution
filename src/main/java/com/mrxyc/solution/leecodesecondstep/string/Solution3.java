package com.mrxyc.solution.leecodesecondstep.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 */
public class Solution3 {

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.lengthOfLongestSubString1("pwwkew"));
    }

    public int lengthOfLongestSubString1(String s) {
        //滑动窗口
        Set<Character> data = new HashSet<>();
        int n = s.length();
        int max = 0;
        int right = 0;
        for (int left = 0; left < n; left++) {
            //移动左边界
            if (left != 0) {
                data.remove(s.charAt(left - 1));
            }
            //寻找可用右边界
            while (right + 1 < n && !data.contains(s.charAt(right + 1))) {
                data.add(s.charAt(right + 1));
                right++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }


    public int lengthOfLongestSubString(String s) {
        //通过一个hashmap或者数组，存储字符的最近的索引位。
        //遍历 如果遇到未出现的，则+1，存储。
        //如果遇见已经出现的，如果在这次起点内，则更新max，从已经出现的节点之后 算新的start继续走, 更新curMax
        //如果不在此次起点内，curMax+1 计算最大值
        int[] recordIndex = new int[127];
        Arrays.fill(recordIndex, -1);
        int start = 0;
        int max = 0;
        int curMax = 0;
        for (int i = 0; i < s.length(); i++) {
            char curC = s.charAt(i);
            //在start范围内 存在重复字符
            if (recordIndex[curC] >= start) {
                max = Math.max(max, curMax);
                start = recordIndex[curC] + 1;
                curMax = i - start + 1;
            } else {
                curMax++;
            }
            //记录最新
            recordIndex[curC] = i;
        }
        max = Math.max(max, curMax);
        return max == 0 ? s.length() : max;
    }
}
