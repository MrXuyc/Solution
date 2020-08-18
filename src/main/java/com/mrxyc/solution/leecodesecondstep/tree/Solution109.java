package com.mrxyc.solution.leecodesecondstep.tree;

import com.mrxyc.common.ListNode;
import com.mrxyc.common.TreeNode;

/**
 * 有序链表转换为平衡二叉树
 */
public class Solution109 {

    public static void main(String[] args) {
        Solution109 solution109 = new Solution109();
        ListNode head1 = new ListNode(-10);
        ListNode head2 = new ListNode(-3);
        ListNode head3 = new ListNode(0);
        ListNode head4 = new ListNode(5);
        ListNode head5 = new ListNode(9);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        TreeNode res = solution109.sortedListToBST1(head1);
        System.out.println("");
    }

    //选择中间节点之后递归 分治
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode lowPreNode = head;
        ListNode lowNode = head;
        ListNode fastNode = head;
        while (fastNode != null && fastNode.next != null) {
            lowPreNode = lowNode;
            lowNode = lowNode.next;
            fastNode = fastNode.next.next;
        }
        //断链
        lowPreNode.next = null;
        TreeNode root = new TreeNode(lowNode.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(lowNode.next);
        return root;
    }

    //中序遍历 从中选取val
    private ListNode globalHead;

    public TreeNode sortedListToBST1(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    private TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(globalHead.val);
        globalHead = globalHead.next;
        root.left = buildTree(left, mid - 1);
        root.right = buildTree(mid + 1, right);
        return root;
    }

    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
