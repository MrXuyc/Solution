package com.mrxyc.solution.leecodefirststep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 课程表
 */
public class Solution207 {

    public static void main(String[] args) {
        Solution207 solution207 = new Solution207();
        int[][] prerequisites = new int[][]{{1, 0}, {2, 6}, {1, 7}, {6, 4}, {7, 0}, {0, 5}};
        System.out.println(solution207.canFinish1(8, prerequisites));
    }


    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        //判断是否有环
        if (prerequisites == null || prerequisites.length <= 1) {
            return true;
        }
        //记录规则
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        //记录有先决条件的点
        int[] visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            visited[info[0]]++;
        }
        //把没有先决条件的放入到队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                queue.offer(i);
            }
        }
        int choose = 0;
        while (!queue.isEmpty()) {
            choose++;
            int cur = queue.poll();
            for (int v : edges.get(cur)) {
                //已经满足一个条件
                visited[v]--;
                //如果条件都满足 则放入队列
                if (visited[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return choose == numCourses;
    }


    //深搜
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //判断是否有环
        if (prerequisites == null || prerequisites.length <= 1) {
            return true;
        }
        //有环就失败
        return !hasCycle(prerequisites);
    }

    private boolean hasCycle(int[][] prerequisites) {
        //记录已经被访问的数据
        Set<Integer> totalVisited = new HashSet<>();
        Map<Integer, List<Integer>> data = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> list = data.getOrDefault(prerequisite[0], new ArrayList<>());
            list.add(prerequisite[1]);
            data.put(prerequisite[0], list);
        }
        for (int[] prerequisite : prerequisites) {
            if (!totalVisited.contains(prerequisite[0])) {
                if (deep(prerequisite[0], data, totalVisited, new HashSet<Integer>())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean deep(int i, Map<Integer, List<Integer>> data, Set<Integer> totalVisited, HashSet<Integer> visited) {
        //如果已经访问 则证明有环
        if (visited.contains(i)) {
            return true;
        }
        totalVisited.add(i);
        visited.add(i);
        List<Integer> list = data.get(i);
        if (list == null) {
            return false;
        }
        for (Integer next : list) {
            HashSet<Integer> copy = new HashSet<>(visited);
            if (deep(next, data, totalVisited, copy)) {
                return true;
            }
        }
        return false;
    }
}
