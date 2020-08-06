package com.mrxyc.solution.leecodesecondstep.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * n叉树 广度优先
 */
public class Solution429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        // 队列法 放置一个标志位 用于处理层级
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            // 获取首节点 输出 判断是否有子节点，有则入栈
            // 需要一次处理一层数据
            int size = deque.size();
            List<Integer> subRes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = deque.removeFirst();
                subRes.add(node.val);
                deque.addAll(node.children);
            }
            res.add(subRes);
        }
        return res;
    }

    private static List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelOrder2(Node root) {
        //递归法
        if (root != null) {
            deep(root, 0);
        }
        return res;
    }

    public void deep(Node cur, int level) {
        //退出条件
        if (cur == null) return;
        //当前层处理
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(cur.val);
        //递归
        for (Node node : cur.children) {
            deep(node, level + 1);
        }
        //状态清除
    }


}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

