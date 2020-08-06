package com.mrxyc.solution.leecodesecondstep.stackqueue;

import java.util.LinkedList;
import java.util.Stack;

public class Solution155 {
    /**
     * 使用链表的方式
     */
    class MinStack {
        class MinStackNode {
            private int val;
            private int min;

            public MinStackNode(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }

        private LinkedList<MinStackNode> linkedList;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            linkedList = new LinkedList();
        }

        public void push(int x) {
            if (linkedList.isEmpty()) {
                linkedList.add(new MinStackNode(x, x));
            } else {
                linkedList.add(new MinStackNode(x, Math.min(x, linkedList.peekLast().min)));
            }
        }

        public void pop() {

            linkedList.removeLast();
        }

        public int top() {
            return linkedList.peekLast().val;
        }

        public int getMin() {
            return linkedList.peekLast().min;
        }
    }

    //自己实现链表
    class MinStack1 {

        class ListNode {
            private int val;
            private int min;
            private ListNode next;

            public ListNode(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }

        private ListNode head;

        public MinStack1() {

        }

        public void push(int x) {
            if (head == null) {
                head = new ListNode(x, x);
            } else {
                ListNode next = head;
                head = new ListNode(x, Math.min(x, head.min));
                head.next = next;
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }
    }

    //双栈 一个栈存数，另外一个栈存最小值（小于栈底元素的值）。
    class MinStack2 {

        /**
         * initialize your data structure here.
         */
        public MinStack2() {

        }

        Stack<Integer> stack = new Stack<>();

        Stack<Integer> minStack = new Stack<>();

        public void push(int x) {
            //如果原栈是空的，则两个栈同时压
            if (stack.empty()) {
                stack.push(x);
                minStack.push(x);
            } else {
                //比较和原栈大小，如果小于等于的话压入最小栈
                if (x <= minStack.peek()) {
                    minStack.push(x);
                }
                //放入元素到原栈
                stack.push(x);
            }
        }

        public void pop() {
            //如果两个栈顶相等 则需要都弹出
            if (stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    //单栈
    // 1.第一个元素 无最小值不用压入两次
    // 2.存入比栈底元素小的时候，多存一个一个历史最小值。删除的时候如果是最小值 弹出两次栈
    class MinStack3 {

        /**
         * initialize your data structure here.
         */
        public MinStack3() {

        }

        private int minValue;
        private Stack<Integer> stack = new Stack<>();

        public void push(int x) {
            if (stack.empty()) {
                stack.push(x);
                stack.push(x);
                minValue = x;
            } else {
                //如果小于等于 则把历史最小也存进去。
                if (x <= minValue) {
                    stack.push(minValue);
                    minValue = x;
                }
                stack.push(x);
            }
        }

        public void pop() {
            //如果相等 需要弹栈两次 第二次的值复制给min
            if (minValue == stack.peek()) {
                stack.pop();
                minValue = stack.pop();
            } else {
                stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minValue;
        }
    }

    //单栈
    // 1.min当作准线，其他存入与之相差的值。如果遇到大于等于0的 不用处理。遇到负数，说明要变更min。
    // 2.出栈 大于等于0则直接出 栈元素+min 如果小于0则 出min 更新min=min-栈
    // 由于需要加减 所以需要用long
    class MinStack4 {

        /**
         * initialize your data structure here.
         */
        public MinStack4() {

        }

        private Stack<Long> stack = new Stack<>();

        private long minValue;

        public void push(int x) {
            //为空 则放入栈及更新最小值
            if (stack.empty()) {
                stack.push(0L);
                minValue = x;
            } else {
                //不为空
                //如果x小于minValue 则更新min 并且压入差值
                long i = x - minValue;
                if (i < 0) {
                    minValue = x;
                }
                stack.push(i);
            }
        }

        public void pop() {
            //如果小于0 则需要变更min
            if (stack.peek() < 0) {
                minValue = minValue - stack.peek();
            }
            stack.pop();
        }

        public int top() {
            if (stack.peek() <= 0) {
                return (int) minValue;
            }
            return (int) (stack.peek() + minValue);
        }

        public int getMin() {
            return (int) minValue;
        }
    }

}
