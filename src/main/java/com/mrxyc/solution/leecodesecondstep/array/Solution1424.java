package com.mrxyc.solution.leecodesecondstep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution1424 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> temp = new TreeMap<>();
        //用于初始化数组
        int len = 0;
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> subNum = nums.get(i);
            for (int j = 0; j < subNum.size(); j++) {
                int key = i + j;
                if (temp.containsKey(key)) {
                    temp.get(key).add(subNum.get(j));
                } else {
                    temp.put(key, new ArrayList<>(Arrays.asList(subNum.get(j))));
                }
                len++;
            }
        }
        int[] res = new int[len];
        int cur = 0;
        for (List<Integer> val : temp.values()) {
            for (int i = val.size() - 1; i >= 0; i--) {
                res[cur++] = val.get(i);
            }
        }
        return res;
    }
}
