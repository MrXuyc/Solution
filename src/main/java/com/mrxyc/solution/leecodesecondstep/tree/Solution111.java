package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.LinkedList;

import javafx.util.Pair;


/**
 * 树最小深度
 */
public class Solution111 {

    public static void main(String[] args) {
        Solution111 solution111 = new Solution111();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode treeNode = new TreeNode(20);
        treeNode.left = new TreeNode(15);
        treeNode.right = new TreeNode(7);
        root.right = treeNode;
        solution111.minDepth3(root);
    }

    public int minDepth(TreeNode root) {
        return 0;
    }

    //递归
    public int minDepth1(TreeNode root) {
        return deep(Integer.MAX_VALUE, root);
    }

    private int deep(int level, TreeNode root) {
        //退出条件
        if (root == null) {
            return 0;
        }
        //本层处理
        if (root.left == null && root.right == null) {
            return 1;
        }
        //递归
        if (root.left != null) {
            level = Math.min(level, deep(level, root.left));
        }
        if (root.right != null) {
            level = Math.min(level, deep(level, root.right));
        }
        return level + 1;
    }

    //迭代 深度优先
    public int minDepth2(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> deque = new LinkedList<>();
        if (root == null) return 0;
        deque.add(new Pair<>(root, 1));
        int minDepth = Integer.MAX_VALUE;
        while (!deque.isEmpty()) {
            Pair<TreeNode, Integer> cur = deque.pop();
            TreeNode curNode = cur.getKey();
            Integer curDepth = cur.getValue();
            if (curNode.left == null && curNode.right == null) {
                minDepth = Math.min(minDepth, curDepth);
            }
            if (curNode.left != null) {
                deque.add(new Pair<>(curNode.left, curDepth + 1));
            }
            if (curNode.right != null) {
                deque.add(new Pair<>(curNode.right, curDepth + 1));
            }
        }
        return minDepth;
    }
    //迭代 广度优先

    public int minDepth3(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        if (root == null) return 0;
        deque.add(root);
        int minDepth = 0;
        while (!deque.isEmpty()) {
            LinkedList<TreeNode> tmp = new LinkedList<>();
            for (TreeNode cur : deque) {
                //如果出现没有下层的，说明最近得叶子节点已经出现
                if (cur.right == null && cur.left == null) {
                    tmp.clear();
                    break;
                }
                if (cur.left != null) {
                    tmp.add(cur.left);
                }
                if (cur.right != null) {
                    tmp.add(cur.right);
                }
            }
            deque = tmp;
            minDepth++;
        }
        return minDepth;
    }
}
