package com.mrxyc.solution.leecodesecondstep.listnode;

import com.mrxyc.common.ListNode;

/**
 * 单向链表排序 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 */
public class Solution148 {
    //归并 从底至顶直接合并
    public ListNode sortList(ListNode head) {
        ListNode h = head;
        ListNode res;
        int length = 0;
        while (h != null) {
            length++;
            h = h.next;
        }
        ListNode h1;
        ListNode h2;
        ListNode pre;
        int intv = 1;
        res = new ListNode(-1);
        res.next = head;
        while (intv < length) {
            pre = res;
            h = res.next;
            while (h != null) {
                int i = intv;
                h1 = h;
                while (i > 0 && h != null) {
                    h = h.next;
                    i--;
                }
                if (i > 0) {
                    break;
                }
                i = intv;
                h2 = h;
                while (i > 0 && h != null) {
                    h = h.next;
                    i--;
                }
                int c1 = intv;
                int c2 = intv - i;
                while (c1 > 0 && c2 > 0) {
                    if (h1.val < h2.val) {
                        pre.next = h1;
                        h1 = h1.next;
                        c1--;
                    } else {
                        pre.next = h2;
                        h2 = h2.next;
                        c2--;
                    }
                    pre = pre.next;
                }
                pre.next = c1 == 0 ? h2 : h1;
                while (c1 > 0 || c2 > 0) {
                    pre = pre.next;
                    c1--;
                    c2--;
                }
                pre.next = h;
            }
            intv *= 2;
        }
        return res.next;
    }


    public static void main(String[] args) {
        Solution148 solution148 = new Solution148();
        ListNode head = new ListNode(4);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(1);
        ListNode head3 = new ListNode(3);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        ListNode listNode = solution148.sortList(head);
        System.out.println("");
    }

    //归并 递归
    public ListNode sortList1(ListNode head) {
        //退出条件
        if (head == null || head.next == null) {
            return head;
        }
        //本层处理
        //分开
        //双指针
        ListNode lowCur = head;
        ListNode fastCur = head;
        ListNode lowCurPre = head;
        while (fastCur != null && fastCur.next != null) {
            lowCurPre = lowCur;
            lowCur = lowCur.next;
            fastCur = fastCur.next.next;
        }
        ListNode nextHead = lowCur;
        lowCurPre.next = null;
        ListNode listNode1 = sortList1(head);
        ListNode listNode2 = sortList1(nextHead);
        //合并
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        ListNode curList1 = listNode1;
        ListNode curList2 = listNode2;
        while (curList1 != null && curList2 != null) {
            if (curList1.val < curList2.val) {
                cur.next = curList1;
                cur = curList1;
                curList1 = curList1.next;
            } else {
                cur.next = curList2;
                cur = curList2;
                curList2 = curList2.next;
            }
        }
        ListNode remain = null;
        if (curList1 != null) {
            remain = curList1;
        } else if (curList2 != null) {
            remain = curList2;
        }
        if (remain != null) {
            cur.next = remain;
        }
        return res.next;
    }
}
