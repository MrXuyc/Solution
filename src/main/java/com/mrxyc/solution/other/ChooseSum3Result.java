package com.mrxyc.solution.other;

import java.util.ArrayList;
import java.util.Arrays;

public class ChooseSum3Result {
    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ArrayList<Integer> res = new ArrayList<>();
        chooseResult(ints, res, 6);
    }

    public static void chooseResult(ArrayList<Integer> ints, ArrayList<Integer> res, int target) {
        //退出条件
        if (res.size() == 3) {
            int sum = 0;
            for (Integer i :
                    res) {
                sum = sum + i;
            }
            System.out.println(Arrays.toString(res.toArray()));
            System.out.println(sum);
        }
        for (int i = 0; i < ints.size(); i++) {
            Integer integer = ints.get(i);
            ArrayList<Integer> newRes = (ArrayList<Integer>) (res.clone());
            newRes.add(integer);
            ArrayList<Integer> newInts = (ArrayList<Integer>) (ints.clone());
            newInts.remove(i);
            chooseResult(newInts, newRes, target);
        }
    }
}
