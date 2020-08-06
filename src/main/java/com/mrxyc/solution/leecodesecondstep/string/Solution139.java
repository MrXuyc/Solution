package com.mrxyc.solution.leecodesecondstep.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单词拆分
 */
public class Solution139 {

    public static void main(String[] args) {
        Solution139 solution139 = new Solution139();
        System.out.println(solution139.wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet", "code"))));
    }

    /**
     * 动态规划
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> dataSet = new HashSet<>(wordDict);
        boolean[] res = new boolean[s.length() + 1];
        //空格认为true
        res[0] = true;
        //dp公式 res[i] =res[j]&&wordSet.contain(j-i)
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (res[j] && dataSet.contains(s.substring(j, i))) {
                    res[i] = true;
                }
            }
        }
        //返回最后一位的结果
        return res[s.length()];
    }

    boolean res = false;

    //递归替换 每次只替换一个字符  会超过时间
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        deep(s, wordDictSet);
        return res;
    }

    private void deep(String s, Set<String> wordDictSet) {
        //跳出
        if (s.equals("")) {
            res = true;
            return;
        }
        //本层处理
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            tmp.append(s.charAt(i));
            if (wordDictSet.contains(tmp.toString())) {
                deep(s.substring(i + 1), wordDictSet);
            }
        }
    }
}
