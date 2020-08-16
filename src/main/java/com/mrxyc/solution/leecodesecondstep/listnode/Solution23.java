package com.mrxyc.solution.leecodesecondstep.listnode;

import com.mrxyc.common.ListNode;

/**
 * 合并k个链表
 */
public class Solution23 {

    //递归
    public ListNode mergeKLists1(ListNode[] lists) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        int nullCount = 0;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                nullCount++;
            } else {
                if (lists[i].val < min) {
                    min = lists[i].val;
                    minIndex = i;
                }
            }
        }
        if (nullCount == lists.length) {
            return null;
        }
        if (minIndex != -1) {
            lists[minIndex] = lists[minIndex].next;
            ListNode res = new ListNode(min);
            res.next = mergeKLists(lists);
            return res;
        } else {
            return null;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        //初始化指针
        ListNode cur = head;
        while (true) {
            //如果都已经遍历完就退出
            int nullCount = 0;
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                ListNode pointer = lists[i];
                if (pointer == null) {
                    nullCount++;
                } else {
                    if (pointer.val < min) {
                        min = pointer.val;
                        minIndex = i;
                    }
                }
            }
            //如果都为空 证明已经排好序
            if (nullCount == lists.length) {
                break;
            }
            //创建最小的放到链表
            cur.next = lists[minIndex];
            cur = cur.next;
            //修改链表可选
            lists[minIndex] = lists[minIndex].next;
        }
        return head.next;
    }
}
