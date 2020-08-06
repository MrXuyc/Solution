package com.mrxyc.solution.leecodesecondstep.listnode;

import com.mrxyc.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表是否有环
 */
public class Solution141 {
    public boolean hasCycle(ListNode head) {
        return fastSlowPoint(head);
    }

    //快慢指针
    public boolean fastSlowPoint(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean res = false;
        //退出条件
        //快指针和快指针.next遇到了空
        //快指针和慢指针相等  有
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow.equals(fast)) {
                res = true;
                break;
            }
        }
        return res;
    }

    //set
    public boolean setMethod(ListNode head) {
        ListNode curr = head;
        Set<ListNode> tmp = new HashSet<>();
        while (curr != null) {
            if (tmp.contains(curr)) {
                return true;
            }
            tmp.add(curr);
            curr = curr.next;
        }
        return false;
    }
}
