package com.mrxyc.solution.leecodesecondstep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 *
 * 示例:
 *
 *
 * 输入: n = 4, k = 2 输出: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 */
public class Solution77 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        deep(new ArrayList<>(), k, new boolean[n], 0);
        return res;
    }

    private void deep(List<Integer> subList, int k, boolean[] visiteds, int cn) {
        //如果结果满了，则可以放入结果
        if (subList.size() >= k) {
            res.add(subList);
            return;
        }
        for (int i = cn; i < visiteds.length; i++) {
            if (!visiteds[i]) {
                List<Integer> tmp = new ArrayList<>(subList);
                tmp.add(i + 1);
                boolean[] tmpVisited = Arrays.copyOf(visiteds, visiteds.length);
                tmpVisited[i] = true;
                deep(tmp, k, tmpVisited, i);
            }
        }
    }
}
