package com.mrxyc.solution.leecodethirdstep.tree;

import com.mrxyc.common.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;

/**
 * 树的最小深度
 */
public class Solution111 {
    public int minDepth(TreeNode root) {
        return deep(root);
    }

    //获取每个节点的最小深度
    private int deep(TreeNode root) {
        //如果为空则代表深度是0
        if (root == null) {
            return 0;
        }
        //如果没有子节点认为是1
        if (root.left == null && root.right == null) {
            return 1;
        }
        //获取左子树右子树深度 取最小
        //如果其中一个为空 则不能选
        int leftLevel = Integer.MAX_VALUE;
        if (root.left != null) {
            leftLevel = deep(root.left);
        }
        int rightLevel = Integer.MAX_VALUE;
        if (root.right != null) {
            rightLevel = deep(root.right);
        }
        //加上自身
        return Math.min(leftLevel, rightLevel) + 1;
    }


    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int minDepth = Integer.MAX_VALUE;
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> curNode = queue.removeFirst();
            TreeNode curRoot = curNode.getKey();
            int depth = curNode.getValue();
            if (curRoot.left == null && curRoot.right == null) {
                minDepth = Math.min(minDepth, depth);
            } else {
                if (curRoot.left != null && minDepth > depth + 1) {
                    queue.add(new Pair<>(curRoot.left, depth + 1));
                }
                if (curRoot.right != null && minDepth > depth + 1) {
                    queue.add(new Pair<>(curRoot.right, depth + 1));
                }
            }
        }
        return minDepth;
    }
}
