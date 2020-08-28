package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.*;

/**
 * 重新安排行程
 */
public class Solution332 {

    Map<String, PriorityQueue<String>> map = new HashMap<>();

    List<String> itinerary = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<>());
            }
            map.get(src).offer(dst);
        }
        deep("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    private void deep(String src) {
        while (map.containsKey(src) && map.get(src).size() > 0) {
            String tmp = map.get(src).poll();
            deep(tmp);
        }
        itinerary.add(src);
    }
}
