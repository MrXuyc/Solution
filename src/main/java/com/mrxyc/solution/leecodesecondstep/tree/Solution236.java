package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最小父节点
 */
public class Solution236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return root;
    }

    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        Set<Integer> visited = new HashSet<Integer>();
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }


    private TreeNode ans;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        //在当前节点
        boolean inCurrentNode = root.val == p.val || root.val == q.val;
        //在左节点
        boolean inLeft = dfs(root.left, p, q);
        //在右节点
        boolean inRight = dfs(root.right, p, q);
        if ((inLeft && inRight) || (inCurrentNode && (inLeft || inRight))) {
            ans = root;
        }
        return inLeft || inRight || inCurrentNode;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }


}
