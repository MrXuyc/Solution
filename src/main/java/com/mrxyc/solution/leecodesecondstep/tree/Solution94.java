package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历
 */
public class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        deep(root, res);
        return res;
    }


    public void deep(TreeNode root, List<Integer> res) {
        //如果当前节点为空则直接返回
        if (root == null) {
            return;
        }
        //递归
        //当前层处理
        res.add(root.val);
        deep(root.left, res);
        deep(root.right, res);
        //清除状态
    }


    public List<Integer> stack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        //用栈模拟递归
        while (cur != null || !stack.isEmpty()) {
            //放左节点
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                //获取最下层的左节点
                cur = stack.pop();
                //放入当前值
                res.add(cur.val);
                //查看当前节点的右节点 进行处理
                cur = cur.right;
            }
        }

        return res;
    }
}
