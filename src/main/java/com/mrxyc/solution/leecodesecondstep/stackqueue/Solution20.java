package com.mrxyc.solution.leecodesecondstep.stackqueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
 */
public class Solution20 {
    public boolean isValid(String s) {
        Stack<String> stack = new Stack();
        Map<String, String> stemp = new HashMap();
        stemp.put(")", "(");
        stemp.put("}", "{");
        stemp.put("]", "[");
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String ss = Character.toString(chars[i]);
            if (stemp.containsKey(ss)) {
                if (stack.empty()) {
                    return false;
                }
                //右括号出栈
                if (stemp.get(ss).equals(stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                //左括号进栈
                stack.push(ss);
            }
        }
        return stack.empty();
    }
}
