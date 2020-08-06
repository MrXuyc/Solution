package com.mrxyc.solution.leecodefirststep;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数组中第K个最大元素
 */
public class Solution215 {

    public static void main(String[] args) {
        Solution215 solution215 = new Solution215();
        System.out.println(solution215.findKthLargest(new int[]{-1, 3, -2}, 2));
    }

    //排序 取角标  快排自己写
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    //快排
    public void quickSort(int[] nums, int start, int end) {
        //选择末尾节点，遍历数组将所有元素
        int pivot = nums[start];
        int left = start;
        int right = end;
        while (left < right) {
            //right指针选定一个当前节点小于pivot得位置
            while (left < right && nums[right] > pivot) {
                right--;
            }
            //left指针选定一个当前节点大于pivot得位置
            while (left < right && nums[left] < pivot) {
                left++;
            }
            //change
            if (nums[left] == nums[right] && left < right) {
                left++;
            } else {
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
            }
        }
        if (left - 1 > start) {
            quickSort(nums, start, left - 1);
        }
        if (right + 1 < end) {
            quickSort(nums, right + 1, end);
        }
    }

    //堆排序 pop  堆排序自己写
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            if (queue.isEmpty()) {
                break;
            }
            res = queue.poll();
        }
        return res;
    }

    //滑动窗口 填充 插入排序kn
    public int findKthLargest1(int[] nums, int k) {
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            if (i == 0) {
                res[0] = nums[0];
            } else {
                res[i] = 0;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < k; j++) {
                if (nums[i] >= res[j]) {
                    //插入当前位置，后续的移动
                    System.arraycopy(res, j, res, j + 1, res.length - j - 1);
                    res[j] = nums[i];
                    break;
                }
            }
        }
        return res[k - 1];
    }
}
