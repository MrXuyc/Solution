package com.mrxyc.solution.leecodesecondstep.string;

/**
 * 机器人是否能返回原点
 */
public class Solution657 {
    public boolean judgeCircle(String moves) {
        if (moves.length() % 2 != 0) {
            return false;
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < moves.length(); i++) {
            char curChar = moves.charAt(i);
            if (curChar == 'R') {
                x++;
            } else if (curChar == 'L') {
                x--;
            } else if (curChar == 'U') {
                y++;
            } else if (curChar == 'D') {
                y--;
            }
        }
        return x == 0 && y == 0;
    }
}
