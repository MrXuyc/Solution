package com.mrxyc.solution.leecodesecondstep.dynamicprogramming;

/**
 * 最大连续数组和
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 *
 * 输出: 6
 *
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 0^n  分治
 */
public class Solution53 {

    public static void main(String[] args) {
        Solution53 solution53 = new Solution53();
        System.out.println(solution53.maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    class WtevTree {
        //以左区间为端点最大子段和
        int lSum;
        //以右区间为端点最大子段和
        int rSum;
        //区间所有数的和
        int iSum;
        //该区间最大子段和
        int mSum;

        public WtevTree(int l, int r, int i, int m) {
            lSum = l;
            rSum = r;
            iSum = i;
            mSum = m;
        }
    }

    //分治 维持
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return getMaxInfo(nums, 0, nums.length - 1).mSum;
    }

    public WtevTree getMaxInfo(int[] nums, int left, int right) {
        //退出条件
        if (left == right) {
            return new WtevTree(nums[left], nums[left], nums[left], nums[left]);
        }
        int mid = left + (right - left) / 2;
        WtevTree leftTree = getMaxInfo(nums, left, mid);
        WtevTree rightTree = getMaxInfo(nums, mid + 1, right);
        return pushUp(leftTree, rightTree);
    }

    private WtevTree pushUp(WtevTree leftTree, WtevTree rightTree) {
        //从左开始最大
        int l = Math.max(leftTree.lSum, leftTree.iSum + rightTree.lSum);
        //从右开始最大
        int r = Math.max(rightTree.rSum, rightTree.iSum + leftTree.rSum);
        //全量值
        int i = leftTree.iSum + rightTree.iSum;
        //一种是贯穿区间 另外一种是从左右两个选
        int m = Math.max(leftTree.rSum + rightTree.lSum, Math.max(leftTree.mSum, rightTree.mSum));
        return new WtevTree(l, r, i, m);
    }


    //动态规划
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = Math.max(nums[i], dp[i - 1]);
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    //双重循环
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        //定义滑动窗口长度 1-n
        for (int i = 1; i <= n; i++) {
            int curWindow = 0;
            int curCount = 0;
            for (int j = 0; j < n; j++) {
                //不满足窗口则直接+
                if (curWindow < i) {
                    curCount = curCount + nums[j];
                    curWindow++;
                } else {
                    curCount = curCount + nums[j] - nums[j - i];
                }
                max = Math.max(max, curCount);
            }
        }
        return max;
    }
}
