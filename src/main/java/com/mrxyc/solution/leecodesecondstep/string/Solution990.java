package com.mrxyc.solution.leecodesecondstep.string;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * 示例 1：
 *
 *
 * 输入：["a==b","b!=a"] 输出：false 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 *
 *
 * 示例 2：
 *
 *
 * 输出：["b==a","a==b"] 输入：true 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 */
public class Solution990 {

    public static void main(String[] args) {
        Solution990 solution990 = new Solution990();
        solution990.equationsPossible(new String[]{"a==b", "b==a", "a!=b", "b==c"});
    }

    public boolean equationsPossible1(String[] equations) {
        //构建map 存储 变量== 变量!=  string:set
        Map<String, Set<String>> relations = new HashMap<>();
        //构建set 存储所有变量
        Set<String> params = new HashSet<>();
        //初步解析
        for (String equation : equations) {
            char param1 = equation.charAt(0);
            char param2 = equation.charAt(3);
            params.add(Character.toString(param1));
            params.add(Character.toString(param2));
            Set<String> param1Relation = relations.getOrDefault(param1 + equation.substring(1, 3), new HashSet<>());
            param1Relation.add(Character.toString(param2));
            relations.put(param1 + equation.substring(1, 3), param1Relation);
            Set<String> param2Relation = relations.getOrDefault(param2 + equation.substring(1, 3), new HashSet<>());
            param2Relation.add(Character.toString(param1));
            relations.put(param2 + equation.substring(1, 3), param2Relation);
        }
        for (String param : params) {
            //传递性解析 深度优先
            Deque<String> deque = new LinkedList<>();
            //需要排除已访问的节点
            Set<String> visited = new HashSet<>();
            deque.add(param);
            visited.add(param);
            while (!deque.isEmpty()) {
                String cur = deque.poll();
                Set<String> equals = relations.getOrDefault(cur + "==", new HashSet<>());
                for (String node : equals) {
                    if (!visited.contains(node)) {
                        visited.add(node);
                        deque.add(node);
                    }
                }
            }
            relations.put(param + "==", visited);
        }
        for (String param : params) {
            //传递性解析 深度优先
            Deque<String> deque = new LinkedList<>();
            //需要排除已访问的节点
            Set<String> visited = new HashSet<>();
            Set<String> noEquals = relations.getOrDefault(param + "!=", new HashSet<>());
            deque.addAll(noEquals);
            while (!deque.isEmpty()) {
                String cur = deque.poll();
                Set<String> equals = relations.getOrDefault(cur + "==", new HashSet<>());
                for (String node : equals) {
                    if (!visited.contains(node)) {
                        visited.add(node);
                        deque.add(node);
                    }
                }
            }
            relations.put(param + "!=", visited);
        }
        //变量map 两个数组如果有相等元素 则失败
        for (String param : params) {
            Set<String> equals = relations.getOrDefault(param + "==", new HashSet<>());
            Set<String> noEquals = relations.getOrDefault(param + "!=", new HashSet<>());
            for (String node : equals) {
                if (noEquals.contains(node)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean equationsPossible(String[] equations) {
        //设置标识位
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        //设置相等索引位
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }
        //一直溯源到最顶级位置
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }


    private void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }
}
