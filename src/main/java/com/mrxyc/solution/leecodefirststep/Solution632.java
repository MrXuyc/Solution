package com.mrxyc.solution.leecodefirststep;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 最小区间
 */
public class Solution632 {

    public static void main(String[] args) {
        Solution632 solution632 = new Solution632();
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        int rangeLeft = 0;
        int rangeRight = Integer.MAX_VALUE;
        int minRange = rangeRight - rangeLeft;
        int max = Integer.MIN_VALUE;
        int size = nums.size();
        int[] next = new int[size];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return nums.get(o1).get(next[o1]) - nums.get(o2).get(next[o2]);
            }
        });
        for (int i = 0; i < size; i++) {
            priorityQueue.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        while (true) {
            int minIndex = priorityQueue.poll();
            int curRange = max - nums.get(minIndex).get(next[minIndex]);
            if (curRange < minRange) {
                minRange = curRange;
                rangeLeft = nums.get(minIndex).get(next[minIndex]);
                rangeRight = max;
            }
            next[minIndex]++;
            if (next[minIndex] == nums.get(minIndex).size()) {
                break;
            }
            priorityQueue.offer(minIndex);
            max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
        }
        return new int[]{rangeLeft, rangeRight};
    }
}
