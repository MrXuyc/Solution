package com.mrxyc.solution.leecodesecondstep.greedy;

/**
 * 跳跃游戏
 */
public class Solution55 {


    public static void main(String[] args) {
        Solution55 solution55 = new Solution55();
        solution55.canJump(new int[]{3, 2, 1, 0, 4});
        System.out.println(solution55.res);
    }

    //贪心
    public boolean canJump1(int[] nums) {
        int maxJump = 0;
        for (int i = 0; i < nums.length; i++) {
            //说明当前节点可达
            if (i <= maxJump) {
                maxJump = Math.max(maxJump, i + nums[i]);
                if (maxJump >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean res;

    public boolean canJump(int[] nums) {
        deep(nums, 0);
        return res;
    }

    private void deep(int[] nums, int index) {
        if (res) {
            return;
        }
        //退出
        if (index >= nums.length - 1) {
            res = true;
            return;
        }
        //本层处理
        for (int i = 1; i <= nums[index]; i++) {
            //递归
            deep(nums, index + i);
        }
    }
}
