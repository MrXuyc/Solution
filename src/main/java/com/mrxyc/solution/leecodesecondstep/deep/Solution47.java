package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution47 {

    public static void main(String[] args) {
        Solution47 solution47 = new Solution47();
        solution47.permuteUnique(new int[]{1, 1, 2});
    }

    Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        deep(new ArrayList<>(), nums, new boolean[nums.length]);
        return new ArrayList<>(res);
    }

    //递归
    public void deep(List<Integer> list, int[] nums, boolean[] visited) {
        //如果全部选完，添加退出
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
                    continue;
                }
                List<Integer> cur = new ArrayList<>(list);
                cur.add(nums[i]);
                boolean[] curVisited = Arrays.copyOf(visited, visited.length);
                curVisited[i] = true;
                deep(cur, nums, curVisited);
            }
        }
        if (list.size() == nums.length) {
            res.add(list);
        }
        //本层次处理，加入
        //递归
    }
}
