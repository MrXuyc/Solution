package com.mrxyc.solution.leecodesecondstep.twoarray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution498 {
    public int[] findDiagonalOrder(int[][] matrix) {
        int len = 0;
        Map<Integer, List<Integer>> temp = new TreeMap<>();
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //该值的计算方式，可以通过按索引画坐标轴来推导
                int key = i + j;
                List<Integer> list = temp.getOrDefault(key, new ArrayList<>());
                list.add(matrix[i][j]);
                len++;
            }
        }
        int[] res = new int[len];
        int cur = 0;
        int tier = 0;
        for (List<Integer> list : temp.values()) {
            if (tier % 2 == 0) {
                //正序
                for (int i = 0; i < list.size(); i++) {
                    res[cur++] = list.get(i);
                }
            } else {
                //反序
                for (int i = list.size() - 1; i >= 0; i--) {
                    res[cur++] = list.get(i);
                }
            }
            tier++;
        }
        return res;
    }
}
