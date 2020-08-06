package com.mrxyc.solution.leecodesecondstep.binarysearch;

/**
 * 搜索旋转排序数组中某个元素
 */
public class Solution33 {

    public static void main(String[] args) {
        Solution33 solution33 = new Solution33();
        System.out.println(solution33.search(new int[]{5, 1, 3}, 5));
    }

    public int search(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = nums[mid];
            if (val == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                //转移节点在 右
                //判断当前结果在哪个区间
                if (target < val && nums[0] <= target) {
                    //0-mid区间存在
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                //转移节点在 左
                if (target > val && target <= nums[nums.length - 1]) {
                    //mid - n.length-1区间是否存在
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }
        return index;
    }
}
