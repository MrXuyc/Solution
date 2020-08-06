package com.mrxyc.util;

import java.util.ArrayList;
import java.util.List;

/*
    1
   3 2
  4 5 6
10 9 8 7


 */
public class TestClass1 {
    public static void main(String[] args) {
        method(10);
    }

    private static void method(int n) {
        List<Integer> need = new ArrayList<>();
        int count = 1;
        int needCount = 1;
        while (count <= n) {
            while (need.size() < needCount) {
                need.add(count);
                count++;
            }
            if (needCount % 2 != 0) {
                for (Integer i : need) {
                    System.out.print(i);
                    System.out.print(" ");
                }
            } else {
                for (int i = need.size() - 1; i > -1; i--) {
                    System.out.print(need.get(i));
                    System.out.print(" ");
                }
            }
            need.clear();
            needCount++;
            System.out.println("");
        }
    }
}
