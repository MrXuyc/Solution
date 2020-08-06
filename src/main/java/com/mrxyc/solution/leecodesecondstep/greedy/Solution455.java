package com.mrxyc.solution.leecodesecondstep.greedy;

import java.util.Arrays;

/**
 * 分发饼干
 */
public class Solution455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gIndex = 0;
        int sIndex = 0;
        int gLength = g.length;
        int sLength = s.length;
        int count = 0;
        while (sIndex < sLength && gIndex < gLength) {
            if (s[sIndex] >= g[gIndex]) {
                sIndex++;
                gIndex++;
                count++;
            } else {
                sIndex++;
            }
        }
        return count;
    }
}
