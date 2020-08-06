package com.mrxyc.solution.leecodesecondstep.listnode;

import com.mrxyc.common.ListNode;

/**
 * k个一组反转链表
 */
public class Solution25 {
    public static void main(String[] args) {
        Solution25 solution24 = new Solution25();
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        solution24.reverseKGroup(head, 3);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        //定义首位 便于获取结果
        ListNode top = new ListNode(-1);
        top.next = head;
        //记录当前首位
        ListNode pre = top;
        //记录当前末尾
        ListNode end = top;
        //退出条件 末尾后面没有元素了
        while (end.next != null) {
            //根据k划分 记录end
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode endNext = end.next;
            //断链
            end.next = null;
            //调用反转
            pre.next = reverseGroup(start);
            start.next = endNext;
            //修改初始和启动位置
            pre = start;
            end = pre;
        }
        //记录首尾标识进行链接
        return top.next;
    }

    public ListNode reverseGroup(ListNode head) {
        //记录前一个链的head
        ListNode pre = null;
        //当前处理节点
        ListNode cur = head;
        //处理链表 先保存后，处理前面的引用
        while (cur != null) {
            ListNode curNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = curNext;
        }
        return pre;
    }
}
