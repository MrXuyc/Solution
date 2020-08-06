package com.mrxyc.solution.leecodethirdstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

import javafx.util.Pair;

/**
 * 二叉树的最大深度
 */
public class Solution104 {
    //递归
    public int maxDepth(TreeNode root) {
        return deep(root, 0);
    }

    private int deep(TreeNode root, int curCount) {
        if (root == null) {
            return curCount;
        }
        if (root.left == null && root.right == null) {
            return curCount + 1;
        }
        int max = 0;
        if (root.left != null) {
            int deep = deep(root.left, curCount + 1);
            max = Math.max(max, deep);
        }
        if (root.right != null) {
            int deep = deep(root.right, curCount + 1);
            max = Math.max(max, deep);
        }
        return max;
    }


    //迭代
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<Pair<TreeNode, Integer>> deque = new LinkedList<>();
        deque.add(new Pair<>(root, 1));
        int max = 0;
        while (!deque.isEmpty()) {
            Pair<TreeNode, Integer> cur = deque.pop();
            TreeNode curTreeNode = cur.getKey();
            Integer index = cur.getValue();
            if (curTreeNode.left == null && curTreeNode.right == null) {
                max = Math.max(max, index);
                continue;
            }
            if (curTreeNode.left != null) {
                deque.add(new Pair<>(curTreeNode.left, index + 1));
            }
            if (curTreeNode.right != null) {
                deque.add(new Pair<>(curTreeNode.right, index + 1));
            }
        }
        return max;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int max = 0;
        while (!deque.isEmpty()) {
            Deque<TreeNode> tmp = new LinkedList<>();
            for (TreeNode node : deque) {
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            max++;
            deque = tmp;
        }
        return max;
    }
}
