package com.mrxyc.solution.leecodesecondstep.greedy;

import java.util.HashSet;
import java.util.Set;

public class Solution874 {
    public static void main(String[] args) {
        Solution874 solution874 = new Solution874();
        int[] commands = new int[]{-2, 8, 3, 7, -1};
        int[][] obstacles = new int[][]{{-4, -1}, {1, -1}, {1, 4}, {5, 0}, {4, 5}, {-2, -1}, {2, -5}, {5, 1}, {-3, -1}, {5, -3}};
        System.out.println(solution874.robotSim(commands, obstacles));
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        int nIndex = 0;
        int mIndex = 0;
        //右下左上
        int[] nDirection = new int[]{0, 1, 0, -1};
        int[] mDirection = new int[]{1, 0, -1, 0};
        int curDirection = 0;
        int ans = 0;
        //构建陷阱
        Set<String> obs = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            String s = obstacles[i][0] + "-" + obstacles[i][1];
            obs.add(s);
        }
        //遍历
        for (int i = 0; i < commands.length; i++) {
            //选择当前方向，进行走步
            if (commands[i] > 0) {
                for (int s = 0; s < commands[i]; s++) {
                    //判断当前是否是陷阱
                    int tmpN = nIndex + nDirection[curDirection];
                    int tmpM = mIndex + mDirection[curDirection];
                    if (obs.contains(tmpN + "-" + tmpM)) {
                        break;
                    }
                    nIndex = tmpN;
                    mIndex = tmpM;
                    ans = Math.max(ans, nIndex * nIndex + mIndex * mIndex);
                }
            } else if (commands[i] == -2) {
                //向左90度
                curDirection = (curDirection + 3) % nDirection.length;
            } else if (commands[i] == -1) {
                //向右90度
                curDirection = (curDirection + 1) % nDirection.length;
            }
        }
        return ans;
    }
}
