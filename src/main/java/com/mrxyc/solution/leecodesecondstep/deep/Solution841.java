package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.List;

public class Solution841 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] unlocked = new boolean[rooms.size()];
        deep(rooms, 0, unlocked);
        for (boolean unlock : unlocked) {
            if (!unlock) {
                return false;
            }
        }
        return true;
    }

    private void deep(List<List<Integer>> rooms, int index, boolean[] unlocked) {
        unlocked[index] = true;
        List<Integer> keys = rooms.get(index);
        for (Integer key : keys) {
            deep(rooms, key, unlocked);
        }
    }
}
