package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 从先序遍历还原树
 *
 * 输入："1-2--3--4-5--6--7"
 *
 * 输出：[1,2,5,3,4,6,7]
 *
 * 输入："1-2--3---4-5--6---7"
 *
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 *
 * 输入："1-401--349---90--88"
 *
 * 输出：[1,401,null,349,88,90]
 */
public class Solution1028 {

    public static void main(String[] args) {
        Solution1028 solution1028 = new Solution1028();
        solution1028.recoverFromPreorder("1-2--3--4-5--6--7");
    }

    //迭代
    public TreeNode recoverFromPreorder2(String S) {
        Deque<TreeNode> deque = new LinkedList<>();
        int index = 0;
        while (index < S.length()) {
            int level = 0;
            while (S.charAt(index) == '-') {
                level++;
                index++;
            }
            int value = 0;
            while (index < S.length() && Character.isDigit(S.charAt(index))) {
                value = value * 10 + S.charAt(index) - '0';
                index++;
            }
            TreeNode node = new TreeNode(value);
            if (deque.size() == level) {
                //说明当前不需要补右节点
                if (!deque.isEmpty()) {
                    deque.peek().left = node;
                }
            } else {
                while (deque.size() != level) {
                    deque.pop();
                }
                deque.peek().right = node;
            }
            deque.push(node);
        }
        return deque.peekFirst();
    }


    //递归
    public TreeNode recoverFromPreorder(String S) {
        TreeNode deep = deep(S, 0);
        return deep;
    }

    int pos = 0;

    private TreeNode deep(String S, int level) {
        //退出条件
        //如果字符串长度到了上限进行退出
        if (pos > S.length()) {
            return null;
        }
        //如果当前的字符串的层次不等于当前level则进行退出
        int lineCount = 0;
        int curPos = pos;
        while (pos < S.length() && S.charAt(pos) == '-') {
            lineCount++;
            pos++;
        }
        if (level != lineCount) {
            pos = curPos;
            return null;
        }
        //本层处理
        int digitStart = pos;
        while (pos < S.length() && Character.isDigit(S.charAt(pos))) {
            pos++;
        }
        int value = Integer.valueOf(S.substring(digitStart, pos));
        TreeNode node = new TreeNode(value);
        node.left = deep(S, level + 1);
        //递归
        node.right = deep(S, level + 1);
        return node;
    }
}
