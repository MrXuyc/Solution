package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution105 {

    public static void main(String[] args) {
        Solution105 solution105 = new Solution105();
        solution105.buildTree1(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }

    Map<Integer, Integer> data = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            data.put(inorder[i], i);
        }
        int n = inorder.length;
        return deep(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode deep(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        //递归
        //退出条件
        if (preLeft > preRight) {
            return null;
        }
        //本层处理
        int val = preorder[preLeft];
        //构建当前节点
        TreeNode node = new TreeNode(val);
        Integer index = data.get(val);
        //获取左子树的长度
        int size_left = index - inLeft;
        //递归 指向左右节点
        node.left = deep(preorder, inorder, preLeft + 1, preLeft + size_left, inLeft, index - 1);
        node.right = deep(preorder, inorder, preLeft + size_left + 1, preRight, index + 1, inRight);
        return node;
    }


    //迭代
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            //当前栈上节点
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                //值不相等
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }

        return root;
    }
}
