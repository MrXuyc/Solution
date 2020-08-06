package com.mrxyc.solution.leecodesecondstep.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution126 {
    public static void main(String[] args) {
        Solution126 solution126 = new Solution126();
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        solution126.findLadders("hit", "cog", wordList);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int id = 0;
        int INF = 1 << 20;
        Map<String, Integer> wordId = new HashMap<>();
        ArrayList<String> idWord = new ArrayList<>();
        //给每个单词分配个id
        for (String word : wordList) {
            if (!wordId.containsKey(word)) {
                wordId.put(word, id++);
                idWord.add(word);
            }
        }
        //若endWord 不在wordList中 则无解
        if (!wordId.containsKey(endWord)) {
            return new ArrayList<>();
        }
        //若beginWord也加入wordId中
        if (!wordId.containsKey(beginWord)) {
            wordId.put(beginWord, id++);
            idWord.add(beginWord);
        }

        //初始化存边数组
        ArrayList<Integer>[] edges = new ArrayList[idWord.size()];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }

        //添加边
        for (int i = 0; i < idWord.size() - 1; i++) {
            for (int j = i + 1; j < idWord.size(); j++) {
                if (transformCheck(idWord.get(i), idWord.get(j))) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }
        //目的id
        int dest = wordId.get(endWord);
        //存答案
        List<List<String>> res = new ArrayList<>();
        //存入节点转移代价， 默认无穷大。
        int[] cost = new int[id];
        for (int i = 0; i < id; i++) {
            cost[i] = INF;
        }
        //将起点加入队列，并将其cost设置0
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        //链路
        ArrayList<Integer> tmpBegin = new ArrayList<>();
        tmpBegin.add(wordId.get(beginWord));
        queue.add(tmpBegin);
        cost[wordId.get(beginWord)] = 0;

        while (!queue.isEmpty()) {
            ArrayList<Integer> now = queue.poll();
            int last = now.get(now.size() - 1);
            //判断最后一位是否是endWord
            if (last == dest) {
                ArrayList<String> tmpRes = new ArrayList<>();
                for (int i = 0; i < now.size(); i++) {
                    tmpRes.add(idWord.get(now.get(i)));
                }
                res.add(tmpRes);
            } else {
                //如果不是则遍历，将可以走的结果都放进去。
                for (int i = 0; i < edges[last].size(); i++) {
                    int to = edges[last].get(i);
                    if (cost[last] + 1 <= cost[to]) {
                        cost[to] = cost[last] + 1;
                        ArrayList<Integer> tmp = new ArrayList<>(now);
                        tmp.add(to);
                        queue.add(tmp);
                    }
                }
            }
        }

        return res;
    }

    public boolean transformCheck(String str1, String str2) {
        int diff = 0;
        for (int i = 0; i < str1.length() && diff < 2; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }
}
