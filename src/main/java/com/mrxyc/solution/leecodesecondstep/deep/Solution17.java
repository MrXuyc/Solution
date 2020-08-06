package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Solution17 {

    private Map<Character, List<String>> data = new HashMap<>();

    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        data.put('2', new ArrayList<>(Arrays.asList("a", "b", "c")));
        data.put('3', new ArrayList<>(Arrays.asList("d", "e", "f")));
        data.put('4', new ArrayList<>(Arrays.asList("g", "h", "i")));
        data.put('5', new ArrayList<>(Arrays.asList("j", "k", "l")));
        data.put('6', new ArrayList<>(Arrays.asList("m", "n", "o")));
        data.put('7', new ArrayList<>(Arrays.asList("p", "q", "r", "s")));
        data.put('8', new ArrayList<>(Arrays.asList("t", "u", "v")));
        data.put('9', new ArrayList<>(Arrays.asList("w", "x", "y", "z")));
        if (digits.length() == 0) {
            return res;
        }
        deep("", digits, 0);
        return res;
    }


    private void deep(String curRes, String digits, int start) {
        //退出条件
        if (curRes.length() == digits.length()) {
            res.add(curRes);
            return;
        }
        List<String> vals = data.getOrDefault(digits.charAt(start), new ArrayList<>());
        for (String val : vals) {
            deep(curRes + val, digits, start + 1);
        }
    }


    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        solution17.letterCombinations("23");
        System.out.println(solution17.res);
    }


}
