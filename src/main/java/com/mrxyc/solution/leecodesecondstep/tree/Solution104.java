package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.LinkedList;

/**
 * 树最大深度
 */
public class Solution104 {
    public int maxDepth(TreeNode root) {
        return deep(0, root);
    }

    //递归
    public int deep(int level, TreeNode root) {
        //退出条件
        if (root == null) {
            return level;
        }
        int leftDeep = deep(level + 1, root.left);
        int rightDeep = deep(level + 1, root.right);
        return Math.max(leftDeep, rightDeep);
    }


    //迭代
    public int maxDepth1(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        int res = 0;
        if (root == null) {
            return res;
        }
        deque.add(root);
        while (!deque.isEmpty()) {
            //一次队列中的值，进行一次加1
            LinkedList<TreeNode> tmp = new LinkedList<>();
            for (TreeNode cur : deque) {
                if (cur.left != null) {
                    tmp.add(cur.left);
                }
                if (cur.right != null) {
                    tmp.add(cur.right);
                }
            }
            res++;
            deque = tmp;
        }
        return res;
    }
}
