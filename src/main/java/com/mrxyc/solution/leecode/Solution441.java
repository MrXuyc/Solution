package com.mrxyc.solution.leecode;

/**
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 *
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 *
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 *
 * 示例 1:
 *
 * n = 5
 *
 * 硬币可排列成以下几行: ¤ ¤ ¤ ¤ ¤
 *
 * 因为第三行不完整，所以返回2. 示例 2:
 *
 * n = 8
 *
 * 硬币可排列成以下几行: ¤ ¤ ¤ ¤ ¤ ¤ ¤ ¤
 *
 * 因为第四行不完整，所以返回3.
 */
public class Solution441 {

    public static void main(String[] args) {
        Solution441 solution441 = new Solution441();
        System.out.println(solution441.arrangeCoins(5));
    }

    public int arrangeCoins(int n) {
        //采用二分查找。找到第一个小于当前数的（通过计算规则）。
        int low = 0;
        int high = n;
        int mid = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (1L * mid * (mid + 1) >> 1 <= n) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
}
