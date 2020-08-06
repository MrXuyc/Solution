package com.mrxyc.solution.leecodesecondstep.array;

import java.util.Deque;
import java.util.LinkedList;

import javafx.util.Pair;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class Solution739 {
    public static void main(String[] args) {
        Solution739 solution739 = new Solution739();
        solution739.dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
    }

    //暴力法
    //双层循环
    public int[] dailyTemperatures1(int[] T) {
        int[] res = new int[T.length];
        for (int i = 0; i < T.length - 1; i++) {
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    //栈  临近元素相关  也可存index之后数组比较
    public int[] dailyTemperatures2(int[] T) {
        int[] res = new int[T.length];
        Deque<Pair<Integer, Integer>> stack = new LinkedList<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > stack.peek().getKey()) {
                Pair<Integer, Integer> pop = stack.pop();
                res[pop.getValue()] = i - pop.getValue();
            }
            stack.push(new Pair<>(T[i], i));
        }
        return res;
    }
}
