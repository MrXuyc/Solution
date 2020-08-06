package com.mrxyc.solution.leecodesecondstep.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * n叉树 后序遍历
 */
public class Solution590 {
    //迭代法
    //用栈模拟递归
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.addFirst(node.val);
            for (Node child : node.children) {
                stack.add(child);
            }
        }
        return res;
    }


    //递归法
    public List<Integer> postorder3(Node root) {
        Deque<Integer> deque = new LinkedList<>();
        deep(deque, root);
        Integer[] integers = deque.toArray(new Integer[deque.size()]);
        return new ArrayList<>(Arrays.asList(integers));
    }

    private void deep(Deque<Integer> deque, Node root) {
        //退出条件
        if (root == null) {
            return;
        }
        //本层操作
        deque.addFirst(root.val);
        //递归
        if (root.children == null) {
            return;
        }
        for (int i = root.children.size() - 1; i >= 0; i--) {
            deep(deque, root.children.get(i));
        }

    }

}
