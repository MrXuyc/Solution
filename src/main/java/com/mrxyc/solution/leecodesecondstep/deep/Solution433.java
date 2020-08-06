package com.mrxyc.solution.leecodesecondstep.deep;


import java.util.HashSet;
import java.util.Set;

/**
 * 最小基因变化
 */
public class Solution433 {

    public static void main(String[] args) {
        Solution433 solution433 = new Solution433();
        System.out.println(solution433.minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGATT", "AACCGATA", "AAACGATA", "AAACGGTA"}));
    }


    private int res = Integer.MAX_VALUE;

    public int minMutation(String start, String end, String[] bank) {
        deep(bank, start, end, new HashSet<>());
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void deep(String[] banks, String start, String end, Set<String> step) {
        //退出条件 字符串全部处理完
        if (start.equals(end)) {
            res = Math.min(res, step.size());
            return;
        }
        //本层处理  遍历bank 寻找改动点是1的 并且不包含在step中 进行递归
        for (String bank : banks) {
            int diff = 0;
            for (int i = 0; i < start.length(); i++) {
                if (start.charAt(i) != bank.charAt(i)) {
                    diff++;
                    if (diff > 1) {
                        break;
                    }
                }
            }
            if (diff == 1 && !step.contains(bank)) {
                //递归
                step.add(bank);
                deep(banks, bank, end, step);
                step.remove(bank);
            }
        }
    }
}
