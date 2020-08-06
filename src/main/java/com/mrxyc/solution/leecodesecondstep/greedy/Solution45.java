package com.mrxyc.solution.leecodesecondstep.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳跃游戏，求最小步骤
 */
public class Solution45 {

    //逆序找最先达到目标节点的节点
    public int jump1(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        //寻找最先到目标节点的节点
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    public int jump2(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        //到达当前节点，获取节点之前能到达的最远节点做为下一步的位置
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


    private int res = Integer.MAX_VALUE;

    public int jump(int[] nums) {
        deep(nums, 0, new ArrayList<>());
        return res;
    }

    private void deep(int[] nums, int index, List<Integer> step) {
        //退出条件
        if (index >= nums.length - 1) {
            res = Math.min(res, step.size());
            return;
        }
        for (int i = 1; i <= nums[index]; i++) {
            step.add(index + i);
            deep(nums, index + i, step);
            step.remove((Integer) (index + i));
        }
    }
}
