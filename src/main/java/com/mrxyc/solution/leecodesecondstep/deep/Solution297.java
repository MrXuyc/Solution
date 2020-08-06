package com.mrxyc.solution.leecodesecondstep.deep;

import com.mrxyc.common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 二叉树的序列化和反序列化
 */
public class Solution297 {

    public static void main(String[] args) {
        Solution297 solution297 = new Solution297();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        right.left = new TreeNode(4);
        root.right = right;
        String serialize = solution297.serialize(root);
        System.out.println(serialize);
        TreeNode deserialize = solution297.deserialize(serialize);
        System.out.println("");
    }


    public String serialize(TreeNode root) {
        return deepserialize(root, "");
    }

    private String deepserialize(TreeNode root, String s) {
        //如果是空则返回默认占位
        if (root == null) {
            s += "None,";
        } else {
            s += root.val + ",";
            s = deepserialize(root.left, s);
            s = deepserialize(root.right, s);
        }
        return s;
    }

    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        return deepDeserialize(new LinkedList<>(Arrays.asList(split)));
    }

    private TreeNode deepDeserialize(LinkedList<String> strings) {
        if ("None".equals(strings.get(0))) {
            strings.remove(0);
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.valueOf(strings.remove(0)));
            root.left = deepDeserialize(strings);
            root.right = deepDeserialize(strings);
            return root;
        }
    }
}
