package com.mrxyc.solution.leecodefirststep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 右侧小于当前个数
 */
public class Solution315 {

    public static void main(String[] args) {
        Solution315 solution315 = new Solution315();
        List<Integer> res = solution315.countSmaller1(new int[]{5, 2, 6, 1});
        System.out.println(res);
    }

    //归并排序
    private int[] temp;
    private int[] counter;
    private int[] indexes;

    public List<Integer> countSmaller1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        temp = new int[len];
        counter = new int[len];
        indexes = new int[len];
        for (int i = 0; i < len; i++) {
            indexes[i] = i;
        }
        mergeAndCountSmaller(nums, 0, len - 1);
        for (int i = 0; i < len; i++) {
            res.add(counter[i]);
        }
        return res;
    }

    private void mergeAndCountSmaller(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeAndCountSmaller(nums, left, mid);
        mergeAndCountSmaller(nums, mid + 1, right);
        if (nums[indexes[mid]] > nums[indexes[mid + 1]]) {
            mergeOfTwoSortedArrAndCountSmaller(nums, left, mid, right);
        }
    }

    private void mergeOfTwoSortedArrAndCountSmaller(int[] nums, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = indexes[i];
        }
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                indexes[k] = temp[j];
                j++;
            } else if (j > right) {
                indexes[k] = temp[i];
                i++;
                counter[indexes[k]] = counter[indexes[k]] + (right - mid);
            } else if (nums[temp[i]] <= nums[temp[j]]) {
                indexes[k] = temp[i];
                i++;
                counter[indexes[k]] = counter[indexes[k]] + (j - mid - 1);
            } else {
                indexes[k] = temp[j];
                j++;
            }
        }
    }


    private int[] c;
    private int[] a;

    //树状数组
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        //去重排序全部结果
        discretization(nums);
        init(nums.length + 5);
        for (int i = nums.length - 1; i >= 0; i--) {
            //寻找到当前元素位置
            int id = getId(nums[i]);
            int queryRes = query(id);
            res.add(queryRes);
            update(id + 1);
        }
        Collections.reverse(res);
        return res;
    }

    private void init(int length) {
        c = new int[length];
        Arrays.fill(c, 0);
    }

    private int lowBit(int x) {
        return x & -x;
    }

    private void update(int pos) {
        while (pos < c.length) {
            c[pos] = c[pos] + 1;
            pos = pos + lowBit(pos);
        }
    }

    private int query(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret = ret + c[pos];
            pos = pos - lowBit(pos);
        }
        return ret;
    }

    private void discretization(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        a = new int[size];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        Arrays.sort(a);
    }

    private int getId(int x) {
        return Arrays.binarySearch(a, x);
    }
}
