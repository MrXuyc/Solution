package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历
 */
public class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        deep(root, res);
        return res;
    }

    public void deep(TreeNode root, List<Integer> res) {
        //如果当前为空则跳出
        if (root == null) {
            return;
        }
        res.add(root.val);
        deep(root.left, res);
        deep(root.right, res);
    }

    public List<Integer> stack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                res.add(cur.val);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                cur = node.right;
            }
        }
        return res;
    }

    public List<Integer> stack1(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return res;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.addLast(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }
}
