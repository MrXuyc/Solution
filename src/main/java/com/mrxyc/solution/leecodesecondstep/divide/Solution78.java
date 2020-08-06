package com.mrxyc.solution.leecodesecondstep.divide;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 */
public class Solution78 {
    public static void main(String[] args) {
        Solution78 solution78 = new Solution78();
        List<List<Integer>> lists = solution78.subList(new int[]{1, 2, 3});
        System.out.println(lists);
    }

    //迭代
    public List<List<Integer>> subList(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> newSubSets = new ArrayList<>();
            for (List<Integer> cur : output) {
                ArrayList<Integer> curCopy = new ArrayList<>(cur);
                curCopy.add(num);
                newSubSets.add(curCopy);
            }
            output.addAll(newSubSets);
        }
        return output;
    }

    private List<List<Integer>> res;

    //递归
    public List<List<Integer>> subList1(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            deep(new ArrayList<>(), 0, nums);
        }
        return res;
    }

    public void deep(List<Integer> cur, int needCount, int[] nums) {
        //退出条件
        //当需要的数量满足的时候
        if (cur.size() == needCount) {
            res.add(cur);
        }
        //本层处理
        for (int i = 0; i < nums.length; i++) {
            cur.add(nums[i]);
            deep(cur, needCount, nums);
            cur.remove(cur.size() - 1);
        }
        //递归
    }
}
