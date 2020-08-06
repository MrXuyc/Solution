package com.mrxyc.solution.leecodesecondstep.listnode;

import com.mrxyc.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class SolutionM0201 {

    //双重循环
    public ListNode removeDuplicateNodes1(ListNode head) {
        ListNode ob = head;
        while (ob != null) {
            ListNode oc = ob;
            while (oc.next != null) {
                if (ob.val == oc.next.val) {
                    oc.next = oc.next.next;
                } else {
                    oc = oc.next;
                }
            }
            ob = ob.next;
        }
        return head;
    }

    //哈希表
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return head;
        }
        //采用Set去重
        Set<Integer> data = new HashSet<>();
        ListNode cur = head.next;
        ListNode pre = head;
        data.add(head.val);
        while (cur != null) {
            if (data.contains(cur.val)) {
                //删除当前节点
                //pre节点不变
                pre.next = cur.next;
            } else {
                data.add(cur.val);
                //前节点变为当前节点
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
