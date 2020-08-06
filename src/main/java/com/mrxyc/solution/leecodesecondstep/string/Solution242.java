package com.mrxyc.solution.leecodesecondstep.string;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram" 输出: true 示例 2:
 *
 * 输入: s = "rat", t = "car" 输出: false 说明: 你可以假设字符串只包含小写字母。
 */
public class Solution242 {
    public boolean isAnagram(String s, String t) {
        return isAnagram1(s, t);
    }

    public boolean isAnagram1(String s, String t) {
        //构建一个hashmap存储
        Map<Character, Integer> temp = new HashMap<>();
        char[] schars = s.toCharArray();
        for (char sc : schars) {
            Integer count = temp.getOrDefault(sc, 0);
            temp.put(sc, ++count);
        }

        char[] tchars = t.toCharArray();
        for (char tc : tchars) {
            Integer count = temp.getOrDefault(tc, 0);
            count--;
            if (count == 0) {
                temp.remove(tc);
            } else {
                temp.put(tc, count);
            }
        }
        return temp.isEmpty();
    }

    public boolean isAnagram2(String s, String t) {
        //排序
        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();
        Arrays.sort(schars);
        Arrays.sort(tchars);
        return Arrays.equals(schars, tchars);
    }
}
