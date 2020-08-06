package com.mrxyc.solution.leecodefirststep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 回文对
 */
public class Solution336 {

    public List<List<Integer>> palindromePairs1(String[] words) {
        int n = words.length;
        //字符串反转存储
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(new StringBuffer(words[i]).reverse().toString(), i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = word.length();
            if (m == 0) {
                continue;
            }
            for (int j = 0; j <= m; j++) {
                if (isPalindrome(word, j, m - 1)) {
                    int leftId = findWord(index, word, 0, j - 1);
                    if (leftId != -1 && leftId != i) {
                        res.add(Arrays.asList(i, leftId));
                    }
                }
                if (j != 0 && isPalindrome(word, 0, j - 1)) {
                    int rightId = findWord(index, word, j, m - 1);
                    if (rightId != -1 && rightId != i) {
                        res.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return res;
    }

    private int findWord(Map<String, Integer> index, String word, int left, int right) {
        return index.getOrDefault(word.substring(left, right + 1), -1);
    }

    private boolean isPalindrome(String word, int left, int right) {
        while (left <= right) {
            if (word.charAt(left) == word.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }


    //暴力法
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPalindrome(words[i] + words[j])) {
                    res.add(new ArrayList<>(Arrays.asList(i, j)));
                }
                if (isPalindrome(words[j] + words[i])) {
                    res.add(new ArrayList<>(Arrays.asList(j, i)));
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
