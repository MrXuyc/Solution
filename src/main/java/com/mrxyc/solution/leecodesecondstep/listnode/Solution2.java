package com.mrxyc.solution.leecodesecondstep.listnode;

import com.mrxyc.common.ListNode;

/**
 * 两数相加
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class Solution2 {

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(7);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(2);
        solution2.addTwoNumbers(l1, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(-1);
        ListNode cur = listNode;
        ListNode curL1 = l1;
        ListNode curL2 = l2;
        int carry = 0;
        while (curL1 != null || curL2 != null) {
            int curL1Val = curL1 == null ? 0 : curL1.val;
            int curL2Val = curL2 == null ? 0 : curL2.val;
            int sum = curL1Val + curL2Val + carry;
            int remain = sum % 10;
            carry = sum / 10;
            ListNode remainNode = new ListNode(remain);
            cur.next = remainNode;
            cur = remainNode;
            curL1 = curL1 == null ? null : curL1.next;
            curL2 = curL2 == null ? null : curL2.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return listNode.next;
    }
}
