package com.mrxyc.solution.leecodesecondstep.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 24点
 */
public class Solution679 {
    public boolean judgePoint24(int[] nums) {
        List<Double> data = new ArrayList<>();
        for (int num : nums) {
            data.add((double) num);
        }
        return solve(data);
    }

    private boolean solve(List<Double> data) {
        if (data.size() == 0) {
            return false;
        }
        if (data.size() == 1) {
            return Math.abs(data.get(0) - 24) < 1e-6;
        }
        int size = data.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> data2 = new ArrayList<>();
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            data2.add(data.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && i > j) {
                            continue;
                        }
                        if (k == 0) {
                            data2.add(data.get(i) + data.get(j));
                        } else if (k == 1) {
                            data2.add(data.get(i) * data.get(j));
                        } else if (k == 2) {
                            data2.add(data.get(i) - data.get(j));
                        } else if (k == 3) {
                            if (Math.abs(data.get(j)) < 1e-6) {
                                continue;
                            } else {
                                data2.add(data.get(i) / data.get(j));
                            }
                        }
                        if (solve(data2)) {
                            return true;
                        }
                        //删除最后一位
                        data2.remove(data2.size() - 1);
                    }

                }
            }
        }

        return false;
    }
}
