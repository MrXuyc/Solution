package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 验证是否是二查搜索树
 */
public class Solution98 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        Solution98 solution98 = new Solution98();
        solution98.isValidBST1(root);
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        //中序遍历 验证数组是否有序
        deepTree(list, root);
        if (list.size() <= 1) {
            return true;
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public void deepTree(List<Integer> list, TreeNode root) {
        //退出条件
        if (root == null) {
            return;
        }
        //当前层处理
        deepTree(list, root.left);
        list.add(root.val);
        deepTree(list, root.right);
        //递归
    }


    public boolean isValidBST2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        boolean res = true;
        double inorder = -Double.MAX_VALUE;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                if (inorder >= pop.val) {
                    return false;
                }
                inorder = pop.val;
                list.add(pop.val);
                cur = pop.right;
            }
        }
        return res;
    }

    //递归
    public boolean isValidBST1(TreeNode root) {
        return deep(root, null, null);
    }

    public boolean deep(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;

        int val = root.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!deep(root.right, val, upper)) return false;
        if (!deep(root.left, lower, val)) return false;
        return true;
    }


    public boolean deep1(TreeNode root, Integer lower, Integer upper) {
        //退出条件 当为空时退出
        if (root == null) return true;
        //退出条件 当前节点 需要大于最小限制，小于最大限制 限制是根据左右子树，进行赋值
        int val = root.val;
        if (lower != null && val < lower) return false;
        if (upper != null && val > upper) return false;
        if (!deep1(root.left, lower, val)) return false;
        if (!deep1(root.right, val, upper)) return false;
        return true;
    }

}
