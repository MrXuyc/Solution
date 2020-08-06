package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

/**
 * 求最大路径和
 */
public class Solution124 {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        deep(root);
        return max;
    }

    //递归返回当前节点及其子节点的最大贡献度
    private int deep(TreeNode root) {
        //退出条件
        if (root == null) {
            return 0;
        }
        //递归
        int leftSum = Math.max(deep(root.left), 0);
        int rightSum = Math.max(deep(root.right), 0);

        //本层处理
        int val = root.val + leftSum + rightSum;
        max = Math.max(max, val);

        //返回当前层的最大贡献度
        return root.val + Math.max(leftSum, rightSum);
    }
}
