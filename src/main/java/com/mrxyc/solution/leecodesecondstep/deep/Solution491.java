package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 递增子序列
 */
public class Solution491 {

    public static void main(String[] args) {
        Solution491 solution491 = new Solution491();
        solution491.findSubsequences1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        System.out.println(solution491.ans);
    }

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> findSubsequences1(int[] nums) {
        deep(0, Integer.MIN_VALUE, nums);
        return ans;
    }

    private void deep(int cur, int last, int[] nums) {
        //已经到最后位置
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                ans.add(new ArrayList<>(temp));
            }
            return;
        }
        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            //更新curIndex 选择当前最后一个最大值
            deep(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }
        if (nums[cur] != last) {
            //增加curIndex 但是不用更新last
            deep(cur + 1, last, nums);
        }
    }


    //暴力递归
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return null;
        }
        //使用去重
        Set<List<Integer>> tmpRes = new HashSet<>();
        List<Integer> data = new ArrayList<>();
        data.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {
                data.add(nums[i]);
            }
        }
        deep(tmpRes, data);
        return new ArrayList<>(tmpRes);
    }

    private void deep(Set<List<Integer>> tmpRes, List<Integer> nums) {
        if (nums.size() > 2) {
            tmpRes.add(nums);
            for (int i = 0; i < nums.size(); i++) {
                List<Integer> tmpData = new ArrayList<>(nums);
                tmpData.remove(i);
                deep(tmpRes, tmpData);
            }
        } else if (nums.size() == 2) {
            tmpRes.add(nums);
        }
    }
}
