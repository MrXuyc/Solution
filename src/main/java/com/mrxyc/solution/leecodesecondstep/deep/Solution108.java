package com.mrxyc.solution.leecodesecondstep.deep;

import com.mrxyc.common.TreeNode;

/**
 * 有序数组转换成高度平衡二叉搜索树
 *
 * 高度平衡二叉树是指一个二叉树每个节点的左右两个子树高度差不超过1
 */
public class Solution108 {

    public static void main(String[] args) {
        Solution108 solution108 = new Solution108();
        TreeNode treeNode = solution108.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(treeNode);
    }

    //递归 选取中间节点当作
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return deep(nums, 0, nums.length - 1);
    }

    private TreeNode deep(int[] nums, int left, int right) {
        //退出条件
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        //本层处理
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        //递归
        if (left <= mid - 1) {
            root.left = deep(nums, left, mid - 1);
        }
        if (mid + 1 <= right) {
            root.right = deep(nums, mid + 1, right);
        }
        return root;
    }
}
