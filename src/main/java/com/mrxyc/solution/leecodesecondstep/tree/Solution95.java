package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 不同的二叉搜索树2
 */
public class Solution95 {

    public static void main(String[] args) {
        Solution95 solution95 = new Solution95();
        List<TreeNode> treeNodes = solution95.generateTrees(3);
        System.out.println("");
    }

    public List<TreeNode> generateTrees(int n) {
        //初始化数组
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[i] = i + 1;
        }
        //递归解析
        return deep(val, 0, n - 1);
    }

    //从左右边界限定的数组中构建一个树
    public List<TreeNode> deep(int[] val, int left, int right) {
        List<TreeNode> res = new ArrayList<>();
        //如果非正常区间 则放空return
        if (left > right) {
            res.add(null);
            return res;
        }
        for (int i = left; i <= right; i++) {
            List<TreeNode> leftList = deep(val, left, i - 1);
            List<TreeNode> rightList = deep(val, i + 1, right);
            //组合放入
            for (TreeNode leftNode : leftList) {
                for (TreeNode rightNode : rightList) {
                    TreeNode head = new TreeNode(val[i]);
                    head.left = leftNode;
                    head.right = rightNode;
                    res.add(head);
                }
            }
        }
        return res;
    }
}
