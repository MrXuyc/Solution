package com.mrxyc.solution.leecode;

import com.mrxyc.common.TreeNode;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点； 如果找到了，删除它。 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * 示例:
 *
 * root = [5,3,6,2,4,null,7] key = 3
 *
 * 5 / \ 3   6 / \   \ 2   4   7
 *
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 * 5 / \ 4   6 /     \ 2       7
 *
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 * 5 / \ 2   6 \   \ 4   7
 */
public class Solution450 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode l1 = new TreeNode(3);
        TreeNode l1l = new TreeNode(2);
        TreeNode l1r = new TreeNode(4);
        TreeNode r2 = new TreeNode(6);
        TreeNode r2r = new TreeNode(7);
        root.left = l1;
        l1.left = l1l;
        l1.right = l1r;
        root.right = r2;
        r2.right = r2r;
        Solution450 solution450 = new Solution450();
        TreeNode treeNode = solution450.deleteNode(root, 5);
        System.out.println("");
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = null;
        TreeNode delP = null;
        boolean find = true;
        //寻找
        if (root != null) {
            cur = root;
            while (cur != null && find) {
                if (key > cur.val) {
                    delP = cur;
                    cur = cur.right;
                } else if (key < cur.val) {
                    delP = cur;
                    cur = cur.left;
                } else {
                    find = false;
                }
            }
        }
        //删除
        if (cur == null) return root;

        // 要删除的节点有两个子节点
        if (cur.left != null && cur.right != null) { // 查找右子树中最小节点
            TreeNode minP = cur.right;
            TreeNode minPP = cur; // minPP 表示 minP 的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            cur.val = minP.val; // 将 minP 的数据替换到 p 中 左右子树不用变更
            cur = minP; // 下面就变成了删除 minP 了
            delP = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        TreeNode child; // p 的子节点
        if (cur.left != null) child = cur.left;
        else if (cur.right != null) child = cur.right;
        else child = null;

        if (delP == null) root = child; // 删除的是根节点
        else if (delP.left == cur) delP.left = child;
        else delP.right = child;
        return root;
    }
}
