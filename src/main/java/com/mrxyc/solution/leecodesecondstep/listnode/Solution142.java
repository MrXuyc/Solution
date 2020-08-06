package com.mrxyc.solution.leecodesecondstep.listnode;

import com.mrxyc.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 返回有环链表的环节点
 */
public class Solution142 {
    public ListNode detectCycle(ListNode head) {
        return null;
    }

    //map方法 存储对象和index
    public ListNode findCycle(ListNode head) {
        Set<ListNode> tmp = new HashSet<>();
        ListNode cur = null;
        while (head != null) {
            if (tmp.contains(head)) {
                return head;
            }
            tmp.add(head);
            head = head.next;
        }
        return cur;
    }

    //快慢指针法
    public ListNode findCycle1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        //如果fast或者fast.next是空的 则无环
        ListNode index = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                //相等出现了环
                index = slow;
                break;
            }
        }
        //如果为空则说明无环
        if (index == null) {
            return null;
        }
        //有环则 首末指针进行再次相遇
//        2⋅distance(tortoise)=distance(hare)
//        2(F+a)=F+a+b+a
//        2F+2a=F+2a+b
//             F=b

        while (head != index) {
            head = head.next;
            index = index.next;
        }
        return index;
    }
}
