package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javafx.util.Pair;

/**
 * 树的层次遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 *
 * 二叉树：[3,9,20,null,null,15,7],
 *
 * 3
 *
 * / \
 *
 * 9  20
 *
 * /  \
 *
 * 15   7
 *
 * 返回其层次遍历结果：
 *
 * [
 *
 * [3],
 *
 * [9,20],
 *
 * [15,7]
 *
 * ]
 */
public class Solution102 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        root.left = left;
        TreeNode right = new TreeNode(20);
        root.right = right;
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        Solution102 solution102 = new Solution102();
        solution102.levelOrder1(root);
        System.out.println(solution102.res);
    }

    List<List<Integer>> res = new ArrayList<>();

    //递归
    public List<List<Integer>> levelOrder(TreeNode root) {
        deep(root, 0);
        return res;
    }

    private void deep(TreeNode root, int i) {
        //退出条件
        if (root == null) {
            return;
        }
        //本层处理
        List<Integer> list;
        if (res.size() > i) {
            list = res.get(i);
            res.remove(i);
        } else {
            list = new ArrayList<>();
        }
        list.add(root.val);
        res.add(i, list);
        //递归
        deep(root.left, i + 1);
        deep(root.right, i + 1);
    }


    //迭代
    public List<List<Integer>> levelOrder1(TreeNode root) {
        Deque<Pair<TreeNode, Integer>> deque = new LinkedList<>();
        if (root == null) {
            return res;
        }
        deque.push(new Pair<>(root, 0));
        while (!deque.isEmpty()) {
            Pair<TreeNode, Integer> pop = deque.pop();
            TreeNode node = pop.getKey();
            int curLevel = pop.getValue();
            List<Integer> list;
            if (res.size() > curLevel) {
                list = res.get(curLevel);
                res.remove(curLevel);
                list.add(node.val);
            } else {
                list = new ArrayList<>(Arrays.asList(node.val));
            }
            res.add(curLevel, list);
            if (node.left != null) {
                deque.add(new Pair<>(node.left, curLevel + 1));
            }
            if (node.right != null) {
                deque.add(new Pair<>(node.right, curLevel + 1));
            }
        }
        return res;
    }
}
