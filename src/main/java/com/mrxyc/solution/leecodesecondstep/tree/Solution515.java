package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javafx.util.Pair;

/**
 * 在每个树行中找最大值
 */
public class Solution515 {
    //递归
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        deep(res, root, 0);
        return res;
    }

    private void deep(List<Integer> res, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (res.size() > level) {
            Integer curMax = res.remove(level);
            res.add(level, Math.max(curMax, root.val));
        } else {
            res.add(level, root.val);
        }
        deep(res, root.left, level + 1);
        deep(res, root.right, level + 1);
    }


    //迭代
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<Pair<TreeNode, Integer>> deque = new LinkedList<>();
        if (root != null) {
            deque.push(new Pair<>(root, 0));
        }
        while (!deque.isEmpty()) {
            Pair<TreeNode, Integer> pop = deque.pop();
            int curLevel = pop.getValue();
            TreeNode node = pop.getKey();
            int curVal = node.val;
            if (res.size() > curLevel) {
                Integer preMax = res.remove(curLevel);
                res.add(curLevel, Math.max(preMax, curVal));
            } else {
                res.add(curLevel, curVal);
            }
            if (node.left != null) {
                deque.push(new Pair<>(node.left, curLevel + 1));
            }
            if (node.right != null) {
                deque.push(new Pair<>(node.right, curLevel + 1));
            }
        }
        return res;
    }
}
