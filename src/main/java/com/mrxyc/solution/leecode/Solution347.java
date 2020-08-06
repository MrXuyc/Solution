package com.mrxyc.solution.leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((c1, c2) -> count.get(c1) - count.get(c2));
        for (int i : count.keySet()) {
            heap.add(i);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!heap.isEmpty()) {
            res.add(heap.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
