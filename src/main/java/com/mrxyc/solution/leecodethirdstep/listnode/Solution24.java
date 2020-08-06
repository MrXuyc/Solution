package com.mrxyc.solution.leecodethirdstep.listnode;

import com.mrxyc.common.ListNode;

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
public class Solution24 {
    public static void main(String[] args) {
        com.mrxyc.solution.leecodesecondstep.listnode.Solution24 solution24 = new com.mrxyc.solution.leecodesecondstep.listnode.Solution24();
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
        return method(head);
    }


    //递归
    private ListNode method(ListNode head) {
        //退出条件
        // head是空  或者 head.next是空
        if (head == null || head.next == null) {
            return head;
        }
        //当前层处理
        //swap
        ListNode next = head.next;
        //递归
        head.next = method(head.next.next);
        next.next = head;
        //恢复状态
        //返回头节点
        return next;
    }

    //迭代
    public ListNode foreach(ListNode head) {
        //创建一个头节点 便于获取结果
        ListNode top = new ListNode(-1);
        ListNode pre = top;
        top.next = head;
        //循环跳出条件
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            pre.next = second;
            first.next = second.next;
            second.next = first;
            pre = first;
            head = first.next;
        }
        return top.next;
    }
}
