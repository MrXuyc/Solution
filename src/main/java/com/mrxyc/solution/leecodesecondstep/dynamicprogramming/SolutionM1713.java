package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 恢复空格
 */
public class SolutionM1713 {
    public static void main(String[] args) {
        SolutionM1713 solutionM1713 = new SolutionM1713();
        String[] dicts = new String[]{"looked", "just", "like", "her", "brother"};
        System.out.println(solutionM1713.respace1(dicts, "jesslookedjustliketimherbrother"));
    }

    public int respace1(String[] dictionary, String sentence) {
        int n = sentence.length();
        //构建trie树
        Trie root = new Trie();
        for (String word : dictionary) {
            root.insert(word);
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, sentence.length());
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            Trie curPos = root;
            for (int j = i; j >= 1; j--) {
                int t = sentence.charAt(j - 1) - 'a';
                if (curPos.next[t] == null) {
                    break;
                } else if (curPos.next[t].isEnd) {
                    dp[i] = Math.min(dp[i], dp[j - 1]);
                }
                if (dp[i] == 0) {
                    break;
                }
                curPos = curPos.next[t];
            }
        }
        return dp[n];
    }

    class Trie {
        public Trie[] next;
        public boolean isEnd;

        public Trie() {
            next = new Trie[26];
            isEnd = false;
        }

        public void insert(String s) {
            Trie curPos = this;
            for (int i = s.length() - 1; i >= 0; i--) {
                int t = s.charAt(i) - 'a';
                if (curPos.next[t] == null) {
                    curPos.next[t] = new Trie();
                }
                curPos = curPos.next[t];
            }
            curPos.isEnd = true;
        }
    }

    //hashset 动态规划
    public int respace(String[] dictionary, String sentence) {
        Set<String> dicts = new HashSet<>(Arrays.asList(dictionary));
        int[] f = new int[sentence.length() + 1];
        Arrays.fill(f, sentence.length());
        f[0] = 0;
        for (int i = 1; i <= sentence.length(); i++) {
            f[i] = f[i - 1] + 1;
            for (int j = i; j >= 1; j--) {
                if (dicts.contains(sentence.substring(j - 1, i))) {
                    f[i] = Math.min(f[i], f[j - 1]);
                }
            }
        }
        return f[sentence.length()];
    }

}
