package com.mrxyc.solution.leecodesecondstep.num;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 跳水板
 */
public class SolutionM1611 {

    public static void main(String[] args) {
        SolutionM1611 solutionM1611 = new SolutionM1611();
        System.out.println(Arrays.toString(solutionM1611.divingBoard1(1, 1, 1000)));
    }

    public int[] divingBoard1(int shorter, int longer, int k) {
        int[] res = new int[k + 1];
        if (k == 0) {
            return new int[]{};
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        for (int i = 0; i <= k; i++) {
            res[i] = ((k - i) * shorter + i * longer);
        }
        return res;
    }

    //递归 会超时

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[]{};
        }
        Set<Integer> data = new TreeSet<>();
        deep(shorter, longer, 0, k, 0, data);
        int[] res = new int[data.size()];
        Iterator<Integer> iterator = data.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            res[index] = iterator.next();
            index++;
        }
        return res;
    }

    private void deep(int shorter, int longer, int curCount, int k, int curLength, Set<Integer> data) {
        //退出条件
        if (curCount == k) {
            data.add(curLength);
            return;
        }
        //本层处理
        //递归
        if (shorter == longer) {
            deep(shorter, longer, curCount + 1, k, curLength + shorter, data);
        } else {
            deep(shorter, longer, curCount + 1, k, curLength + shorter, data);
            deep(shorter, longer, curCount + 1, k, curLength + longer, data);
        }
    }
}
