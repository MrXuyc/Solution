package com.mrxyc.solution.leecodefirststep;

import com.mrxyc.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍3
 */
public class Solution337 {

    public static void main(String[] args) {
        Solution337 solution337 = new Solution337();
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leftRight = new TreeNode(3);
        TreeNode rightRight = new TreeNode(1);
        root.left = left;
        root.right = right;
        left.right = leftRight;
        right.right = rightRight;
        System.out.println(solution337.rob(root));
    }

    //dp
    public int rob1(TreeNode root) {
        int[] rootStatus = dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] leftVal = dfs(root.left);
        int[] rightVal = dfs(root.right);
        int select = root.val + leftVal[1] + rightVal[1];
        int noSelect = Math.max(leftVal[0], leftVal[1]) + Math.max(rightVal[0], rightVal[1]);
        return new int[]{select, noSelect};
    }


    //递归   加入缓存减少重复计算
    private Map<TreeNode, Integer> falseMaxValue = new HashMap<>();
    private Map<TreeNode, Integer> trueMaxValue = new HashMap<>();

    public int rob(TreeNode root) {
        int falseVal = deep(root, false);
        int trueVal = deep(root, true);
        return Math.max(falseVal, trueVal);
    }

    private int deep(TreeNode root, boolean curDirection) {
        //如果为空则跳出
        if (root == null) {
            return 0;
        }
        if (curDirection) {
            if (trueMaxValue.containsKey(root)) {
                return trueMaxValue.get(root);
            }
            int leftVal = deep(root.left, false);
            int rightVal = deep(root.right, false);
            int max = leftVal + rightVal + root.val;
            trueMaxValue.put(root, max);
            return max;
        } else {
            if (falseMaxValue.containsKey(root)) {
                return falseMaxValue.get(root);
            }
            //定义可选值
            boolean[] direction = new boolean[]{true, false};
            //左边最大值
            int maxLeftVal = 0;
            for (int i = 0; i < direction.length; i++) {
                int leftVal = deep(root.left, direction[i]);
                maxLeftVal = Math.max(leftVal, maxLeftVal);
            }
            //右边最大值
            int maxRightVal = 0;
            for (int i = 0; i < direction.length; i++) {
                int rightVal = deep(root.right, direction[i]);
                maxRightVal = Math.max(maxRightVal, rightVal);
            }
            int max = maxLeftVal + maxRightVal;
            falseMaxValue.put(root, max);
            return max;
        }
    }
}
