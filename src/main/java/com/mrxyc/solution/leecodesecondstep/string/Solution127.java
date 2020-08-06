package com.mrxyc.solution.leecodesecondstep.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

/**
 * 单词接龙
 */
public class Solution127 {

    public static void main(String[] args) {
        Solution127 solution127 = new Solution127();
        String beginWord = "qa";
        String endWord = "sq";
        List<String> strings = Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye");
        System.out.println(solution127.ladderLength1(beginWord, endWord, new ArrayList<>(strings)));
    }

    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        int wordLength = beginWord.length();

        Map<String, List<String>> allComboDict = new HashMap<>();

        for (String word : wordList) {
            for (int i = 0; i < wordLength; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        }
        Queue<Pair<String, Integer>> queueBegin = new LinkedList<>();
        Queue<Pair<String, Integer>> queueEnd = new LinkedList<>();
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        queueBegin.add(new Pair<>(beginWord, 1));
        queueEnd.add(new Pair<>(endWord, 1));
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);
        while (!queueBegin.isEmpty() && !queueEnd.isEmpty()) {
            int ans = visitWordNode(queueBegin, visitedBegin, visitedEnd, wordLength, allComboDict);
            if (ans > -1) {
                return ans;
            }
            ans = visitWordNode(queueEnd, visitedEnd, visitedBegin, wordLength, allComboDict);
            if (ans > -1) {
                return ans;
            }
        }
        return 0;
    }

    private int visitWordNode(Queue<Pair<String, Integer>> queue, Map<String, Integer> visited, Map<String, Integer> otherVisited, int wordLength, Map<String, List<String>> allComboDict) {
        Pair<String, Integer> node = queue.poll();
        String word = node.getKey();
        int level = node.getValue();
        for (int i = 0; i < wordLength; i++) {
            String newWord = word.substring(0, i) + "*" + word.substring(i + 1);
            for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                if (otherVisited.containsKey(adjacentWord)) {
                    return level + otherVisited.get(adjacentWord);
                }
                if (!visited.containsKey(adjacentWord)) {
                    visited.put(adjacentWord, level + 1);
                    queue.add(new Pair<>(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }


    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        int wordLength = beginWord.length();

        Map<String, List<String>> allComboDict = new HashMap<>();

        for (String word : wordList) {
            for (int i = 0; i < wordLength; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        }

        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(beginWord, 1));

        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);
        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.poll();
            String word = node.getKey();
            Integer level = node.getValue();
            for (int i = 0; i < wordLength; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1);
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        queue.add(new Pair<>(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }


    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        int res = Integer.MAX_VALUE;
        int index = 0;
        //构建图，进行查找
        Map<String, Integer> dataIdMap = new HashMap<>();
        List<String> idDataList = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (!dataIdMap.containsKey(wordList.get(i))) {
                dataIdMap.put(wordList.get(i), index);
                idDataList.add(wordList.get(i));
                index++;
            }
        }
        if (!dataIdMap.containsKey(beginWord)) {
            idDataList.add(beginWord);
            dataIdMap.put(beginWord, index);
        }
        if (!dataIdMap.containsKey(endWord)) {
            return 0;
        }
        //初始化图关系
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < idDataList.size(); i++) {
            edges.add(i, new ArrayList<>());
        }
        for (int i = 0; i < idDataList.size() - 1; i++) {
            for (int j = i + 1; j < idDataList.size(); j++) {
                if (check(idDataList.get(i), idDataList.get(j))) {
                    edges.get(i).add(j);
                    edges.get(j).add(i);
                }
            }
        }
        //从起始节点进行走
        Deque<List<Integer>> deque = new LinkedList<>();
        deque.push(new ArrayList<>(Arrays.asList(dataIdMap.get(beginWord))));
        while (!deque.isEmpty()) {
            List<Integer> nodes = deque.pop();
            Integer node = nodes.get(nodes.size() - 1);
            if (idDataList.get(node).equals(endWord)) {
                //满足结果
                //将当前结果放入
                res = Math.min(res, nodes.size());
            } else {
                List<Integer> edge = edges.get(node);
                if (nodes.size() < res) {
                    for (Integer id : edge) {
                        String s = idDataList.get(id);
                        if (!nodes.contains(id)) {
                            List<Integer> curNodes = new ArrayList<>(nodes);
                            curNodes.add(dataIdMap.get(s));
                            deque.push(curNodes);
                        }
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private boolean check(String s, String s1) {
        int diff = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s1.charAt(i)) {
                diff++;
                if (diff > 1) {
                    break;
                }
            }
        }
        return diff == 1;
    }


    private int res = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //防止重复
        wordList.remove(beginWord);
        deep(beginWord, endWord, wordList, new HashSet<String>());
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private void deep(String beginWord, String endWord, List<String> wordList, HashSet<String> step) {
        if (step.size() > res) {
            return;
        }
        //退出条件
        if (beginWord.equals(endWord)) {
            res = Math.min(res, step.size() + 1);
        }
        for (int i = 0; i < wordList.size(); i++) {
            int diff = 0;
            for (int j = 0; j < beginWord.length(); j++) {
                if (beginWord.charAt(j) != wordList.get(i).charAt(j)) {
                    diff++;
                    if (diff > 1) {
                        break;
                    }
                }
            }
            if (diff == 1 && !step.contains(wordList.get(i))) {
                step.add(wordList.get(i));
                deep(wordList.get(i), endWord, wordList, step);
                step.remove(wordList.get(i));
            }
        }
    }
}
