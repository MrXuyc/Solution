package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 无重复数字 全排列
 */
public class Solution46 {

    public static void main(String[] args) {
        Solution46 solution46 = new Solution46();
        solution46.permute(new int[]{1, 2, 3});
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        deep(new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    //递归
    public void deep(List<Integer> list, int[] nums, boolean[] visited) {
        //如果全部选完，添加退出
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
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


    //迭代
}
