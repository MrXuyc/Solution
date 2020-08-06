package com.mrxyc.solution.leecodesecondstep.hash;
/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"] 输出: [ ["ate","eat","tea"], ["nat","tan"], ["bat"]]
 * 说明： 所有输入均为小写字母。 不考虑答案输出的顺序。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        return groupAnagrams1(strs);
    }

    private List<List<String>> groupAnagrams1(String[] strs) {
        //构建一个map 之后存储排序后得key。存放list索引值。
        Map<String, List<String>> temp = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> values = temp.getOrDefault(key, new ArrayList<>());
            values.add(strs[i]);
            temp.put(key, values);
        }

        return new ArrayList<>(temp.values());
    }

    private List<List<String>> groupAnagrams2(String[] strs) {
        //构建一个26个字母标志位，存储相同的key
        Map<String, List<String>> temp = new HashMap<>();
        for (String s : strs) {
            char[] schars = s.toCharArray();
            int[] charCount = new int[26];
            for (char c : schars) {
                charCount[c - 'a'] = ++charCount[c - 'a'];
            }
            String key = Arrays.toString(charCount);
            List<String> values = temp.getOrDefault(key, new ArrayList<>());
            values.add(s);
            temp.put(key, values);
        }
        return new ArrayList<>(temp.values());
    }


}
