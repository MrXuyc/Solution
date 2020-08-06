package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.LinkedList;

/**
 * 反转一个二叉树
 */
public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        return root;
    }

    //递归
    public TreeNode invertTree2(TreeNode root) {
        deep(root);
        return root;
    }

    private void deep(TreeNode root) {
        //退出条件
        if (root == null) {
            return;
        }
        //当前层处理
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        //递归
        deep(root.left);
        deep(root.right);
    }

    //迭代 递归模拟
    public TreeNode invertTree1(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        if (root == null) return root;
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode cur = deque.pop();
            TreeNode left = cur.left;
            TreeNode right = cur.right;
            cur.left = right;
            cur.right = left;
            if (cur.left != null) deque.add(cur.left);
            if (cur.right != null) deque.add(cur.right);
        }
        return root;
    }
}
