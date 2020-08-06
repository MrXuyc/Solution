package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

/**
 * 路径总和
 */
public class Solution112 {

    public static void main(String[] args) {
        Solution112 solution112 = new Solution112();
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(4);
        root.left = left;
        TreeNode right = new TreeNode(8);
        right.left = new TreeNode(13);
        TreeNode rightRight = new TreeNode(4);
        rightRight.right = new TreeNode(1);
        right.right = rightRight;
        root.right = right;
        TreeNode leftLeft = new TreeNode(11);
        leftLeft.left = new TreeNode(7);
        leftLeft.right = new TreeNode(2);
        left.left = leftLeft;

        System.out.println(solution112.hasPathSum(root, 22));
    }


    //广度优先 记录当前节点和到当前节点需要的count
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<Pair<TreeNode, Integer>>();
        stack.add(new Pair<>(root, root.val));
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> cur = stack.poll();
            TreeNode curNode = cur.getKey();
            Integer curSum = cur.getValue();
            if (curNode.left == null && curNode.right == null) {
                if (curSum == sum) {
                    return true;
                }
            }
            if (curNode.left != null) {
                stack.add(new Pair<>(curNode.left, curSum + curNode.left.val));
            }
            if (curNode.right != null) {
                stack.add(new Pair<>(curNode.right, curSum + curNode.right.val));
            }
        }
        return false;
    }

    //递归 深度优先
    public boolean hasPathSum(TreeNode root, int sum) {
        return deep(root, 0, sum);
    }

    private boolean deep(TreeNode root, int cur, int sum) {
        //退出条件
        if (root == null) {
            return false;
        }
        //本层处理
        if (root.left == null && root.right == null) {
            return cur + root.val == sum;
        }
        //递归
        return deep(root.left, cur + root.val, sum) || deep(root.right, cur + root.val, sum);
    }
}
