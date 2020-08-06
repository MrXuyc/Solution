package com.mrxyc.solution.leecodesecondstep.listnode;
/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */

import com.mrxyc.common.ListNode;

public class Solution24 {

    public static void main(String[] args) {
        Solution24 solution24 = new Solution24();
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;
        solution24.swapPairs(head);
    }

    public ListNode swapPairs(ListNode head) {
        return method1(head);
    }

    //递归
    public ListNode method(ListNode head) {
        //退出条件
        //如果遇到head是空或者head.next是空则直接返回head 不进行转换
        if (head == null || head.next == null) {
            return head;
        }
        //当前层处理
        ListNode first = head;
        ListNode second = head.next;
        //递归  先改first的引用是防止链表循环
        first.next = method(head.next.next);
        second.next = first;
        //恢复状态
        return second;
    }

    //迭代
    public ListNode method1(ListNode head) {
        //创建特殊变量，为了获取结果
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        //为了只有一个的特殊情况
        pre.next = head;
        //退出条件 head==null||head.next==null
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            //链接上个链表
            pre.next = second;
            first.next = second.next;
            second.next = first;
            pre = first;
            head = first.next;
        }
        return dummy.next;
    }
}
