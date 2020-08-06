package com.mrxyc.solution.leecodesecondstep.listnode;

import com.mrxyc.common.ListNode;

/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
 */
public class Solution21 {
    //迭代
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode l1Cur = l1;
        ListNode l2Cur = l2;
        ListNode cur = res;
        //都不等于空则合并
        while (l1Cur != null && l2Cur != null) {
            if (l1Cur.val <= l2Cur.val) {
                cur.next = l1Cur;
                l1Cur = l1Cur.next;
            } else {
                cur.next = l2Cur;
                l2Cur = l2Cur.next;
            }
            cur = cur.next;
        }
        cur.next = l1Cur == null ? l2Cur : l1Cur;
        return res.next;
    }

    //递归
    public ListNode deepMergeTwoList(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        deep(res, l1, l2);
        return res.next;
    }


    public void deep(ListNode res, ListNode l1, ListNode l2) {
        //退出条件
        if (l1 == null && l2 == null) {
            return;
        }
        if (l1 == null) {
            res.next = l2;
            return;
        }
        if (l2 == null) {
            res.next = l1;
            return;
        }
        //当前层处理
        //递归
        if (l1.val <= l2.val) {
            res.next = l1;
            deep(res.next, l1.next, l2);
        } else {
            res.next = l2;
            deep(res.next, l1, l2.next);
        }
        //清理状态
    }

    public ListNode deep2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = deep2(l1.next, l2);
            return l1;
        } else {
            l2.next = deep2(l1, l2.next);
            return l2;
        }
    }
}
