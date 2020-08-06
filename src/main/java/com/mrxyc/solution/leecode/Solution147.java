package com.mrxyc.solution.leecode;

import com.mrxyc.common.ListNode;

public class Solution147 {
    public static void main(String[] args) {
        //973  894
        Solution147 solution147 = new Solution147();
        ListNode head = new ListNode(4);
        ListNode next1 = new ListNode(2);
        ListNode next2 = new ListNode(1);
        ListNode next3 = new ListNode(3);
        head.next = next1;
        next1.next = next2;
        next2.next = next3;
        ListNode sortList = solution147.insertionSortList(head);
        if (sortList != null) {
            System.out.println(sortList.val);
            while (sortList.next != null) {
                System.out.println(sortList.next.val);
                sortList = sortList.next;
            }
        }
    }

    private ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h = new ListNode(-1);
        h.next = head;
        //首部指针，用于遍历
        ListNode pre = h;
        //lat前的节点
        ListNode cur = head;
        //当前待插入节点
        ListNode lat = head.next;
        //当没有排序节点则退出
        while (cur != null) {
            lat = cur.next;
            if (lat != null && lat.val < cur.val) {
                while (pre.next != null && pre.next.val < lat.val) {
                    pre = pre.next;
                }
                ListNode tmp = pre.next;
                pre.next = lat;
                cur.next = lat.next;
                lat.next = tmp;
                pre = h;
            } else {
                cur = lat;
            }
        }
        return h.next;
    }


}
