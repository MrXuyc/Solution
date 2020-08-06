package com.mrxyc.solution.leecodesecondstep.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * n叉树 前序遍历
 */
public class Solution589 {
    //迭代  用栈模拟递归
    public List<Integer> preorder(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        if (root == null) {
            return res;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.addLast(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.add(node.children.get(i));
            }
        }
        return res;
    }

    //递归
    public List<Integer> preorder2(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        deep(root, res);
        return res;
    }

    public void deep(Node root, LinkedList<Integer> res) {
        //退出条件
        if (root == null) {
            return;
        }
        //本层处理
        res.addLast(root.val);
        //递归
        for (Node child : root.children) {
            deep(child, res);
        }
    }

}
