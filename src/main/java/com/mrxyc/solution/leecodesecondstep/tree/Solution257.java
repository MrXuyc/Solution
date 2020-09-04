package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的所有路径
 */
public class Solution257 {

    public static void main(String[] args) {
        Solution257 solution257 = new Solution257();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leftRight = new TreeNode(5);
        root.left = left;
        root.right = right;
        left.right = leftRight;
        System.out.println(solution257.binaryTreePaths1(root));
    }

    //递归
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> data = new ArrayList<>();
        deep(data, "", root);
        return data;
    }

    private void deep(List<String> data, String step, TreeNode root) {
        if (root == null) {
            return;
        }
        //本层处理
        if ("".equals(step)) {
            step = String.valueOf(root.val);
        } else {
            step = step + "->" + root.val;
        }
        //递归
        if (root.left == null && root.right == null) {
            data.add(step);
            return;
        }
        if (root.left != null) {
            deep(data, step, root.left);
        }
        if (root.right != null) {
            deep(data, step, root.right);
        }
    }

    //迭代
    public List<String> binaryTreePaths1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        Queue<Pair<TreeNode, String>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, String.valueOf(root.val)));
        while (!queue.isEmpty()) {
            Pair<TreeNode, String> curEntry = queue.poll();
            TreeNode curNode = curEntry.getKey();
            String curPath = curEntry.getValue();

            if (curNode.left == null && curNode.right == null) {
                res.add(curPath);
            } else {
                if (curNode.left != null) {
                    queue.add(new Pair<>(curNode.left, curPath + "->" + curNode.left.val));
                }
                if (curNode.right != null) {
                    queue.add(new Pair<>(curNode.right, curPath + "->" + curNode.right.val));
                }
            }
        }
        return res;
    }
}
