package com.mrxyc.solution.leecodefirststep;

import com.mrxyc.common.ListNode;

/**
 * 删除链表的倒数第N个节点
 */
public class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //简单方法
        ListNode res = new ListNode(-1);
        res.next = head;
        //遍历一遍获取size
        ListNode cur = res;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        //计算删除index
        int deleteIndex = size - n;
        //遍历到节点进行删除
        int curIndex = 0;
        ListNode pre = null;
        cur = res;
        while (curIndex < deleteIndex) {
            pre = cur;
            cur = cur.next;
            curIndex++;
        }
        pre.next = cur.next;
        return res.next;
    }


    public ListNode removeNthFormEnd1(ListNode head, int n) {
        ListNode res = new ListNode(-1);
        res.next = head;
        //双指针
        ListNode lowPreNode = res;
        ListNode lowNode = res;
        int diffIndex = 0;
        ListNode fastNode = res;
        while (fastNode != null) {
            if (diffIndex == n) {
                lowPreNode = lowNode;
                lowNode = lowNode.next;
            } else {
                diffIndex++;
            }
            fastNode = fastNode.next;
        }
        //已经到节点位置
        lowPreNode.next = lowPreNode.next.next;
        return res.next;
    }
}
