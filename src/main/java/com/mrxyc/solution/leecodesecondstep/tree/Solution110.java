package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

/**
 * 平衡二叉树
 */
public class Solution110 {
    public static void main(String[] args) {
        Solution110 solution110 = new Solution110();
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        TreeNode rightLeft = new TreeNode(15);
        TreeNode rightRight = new TreeNode(7);
        root.left = left;
        root.right = right;
        right.left = rightLeft;
        right.right = rightRight;
        solution110.isBalanced(root);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            //两树高度小于等于1  并且左右都为平衡二叉树
            return Math.abs(height(root.left) - height(root.right)) <= 1
                    && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    public boolean isBalanced1(TreeNode root) {
        return height(root) >= 0;
    }

    public int height1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height1(root.left);
        int rightHeight = height1(root.right);
        //如果为-1  说明不合法  或者相差大于1也认为不合法
        if (leftHeight == -1 || rightHeight == -1
                || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            //否则返回相差高度
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}
