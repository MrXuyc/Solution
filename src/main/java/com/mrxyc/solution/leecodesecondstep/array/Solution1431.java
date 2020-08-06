package com.mrxyc.solution.leecodesecondstep.array;

import java.util.ArrayList;
import java.util.List;

public class Solution1431 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        return kidsWithCandies1(candies, extraCandies);
    }

    // n
    private List<Boolean> kidsWithCandies1(int[] candies, int extraCandies) {
        //一次循环判断当前最多的值
        int max = -1;
        for (int i = 0; i < candies.length; i++) {
            max = Math.max(max, candies[i]);
        }
        List<Boolean> res = new ArrayList<>();
        //二次循环处理
        for (int i = 0; i < candies.length; i++) {
            int count = candies[i] + extraCandies;
            if (count >= max) {
                res.add(Boolean.TRUE);
            } else {
                res.add(Boolean.FALSE);
            }
        }
        return res;
    }


}
