package com.mrxyc.solution.leecodethirdstep.stackqueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效括号
 */
public class Solution20 {

    public static void main(String[] args) {
        Solution20 solution20 = new Solution20();
        System.out.println(solution20.isValid("[][{()}][]"));
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<String, String> relation = new HashMap<>();
        relation.put("}", "{");
        relation.put(")", "(");
        relation.put("]", "[");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty()) {
                String iStr = String.valueOf(s.charAt(i));
                if (relation.containsKey(iStr)) {
                    if (relation.get(iStr).equals(stack.peek())) {
                        stack.pop();
                        continue;
                    }
                }
            }
            stack.push(String.valueOf(s.charAt(i)));
        }
        return stack.isEmpty();
    }

    public boolean isValid1(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<Character, Character> relation = new HashMap<>();
        relation.put('}', '{');
        relation.put(')', '(');
        relation.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character iStr = s.charAt(i);
            if (relation.containsKey(iStr)) {
                //没有左括号
                if (stack.isEmpty()) {
                    return false;
                }
                if (relation.get(iStr).equals(stack.peek())) {
                    stack.pop();
                }
            } else {
                stack.push(iStr);
            }
        }
        return stack.isEmpty();
    }
}
