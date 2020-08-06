package com.mrxyc.solution.leecodesecondstep.stackqueue;

import java.util.Stack;

public class SolutionM09 {
    class CQueue {
        //实际存储的栈
        private Stack<Integer> storeStack;
        //中转栈
        private Stack<Integer> exchangeStack;

        public CQueue() {
            storeStack = new Stack<>();
            exchangeStack = new Stack<>();
        }

        public void appendTail(int value) {
            storeStack.push(value);
        }

        //如果exchange为空 说明之前导入的批次都已经弹出了
        //再从store导入一批
        //如果exchange不为空 说明之前还没弹完 继续弹
        public int deleteHead1() {
            if (!exchangeStack.isEmpty()) return exchangeStack.pop();
            if (storeStack.isEmpty()) return -1;
            while (!storeStack.isEmpty()) {
                exchangeStack.push(storeStack.pop());
            }
            return exchangeStack.pop();
        }

        //每次都进行导入导出
        public int deleteHead() {

            if (storeStack.isEmpty()) {
                return -1;
            }
            //将所有结果导入到exchangeStack
            while (!storeStack.isEmpty()) {
                exchangeStack.push(storeStack.pop());
            }
            //弹出栈顶
            int head = exchangeStack.pop();
            //将结果重新导入storeStack
            while (!exchangeStack.isEmpty()) {
                storeStack.push(exchangeStack.pop());
            }
            return head;
        }
    }
}
