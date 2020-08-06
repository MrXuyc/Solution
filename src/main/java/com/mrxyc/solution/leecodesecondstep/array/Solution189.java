package com.mrxyc.solution.leecodesecondstep.array;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3 输出: [5,6,7,1,2,3,4] 解释: 向右旋转 1 步: [7,1,2,3,4,5,6] 向右旋转 2 步:
 * [6,7,1,2,3,4,5] 向右旋转 3 步: [5,6,7,1,2,3,4] 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2 输出: [3,99,-1,-100] 解释: 向右旋转 1 步: [99,-1,-100,3] 向右旋转 2 步:
 * [3,99,-1,-100] 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class Solution189 {
    public void rotate(int[] nums, int k) {
    }


    //环状替换
    public void loopSwap(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        //变动次数等于总数 就退出
        int count = 0;
        for (int i = 0; count < nums.length; i++) {
            int previous = nums[i];
            int replaceIndex = i;
            //退出条件指针循环
            do {
                //计算目标index
                replaceIndex = (replaceIndex + k) % nums.length;
                //swap
                //保存被替换值
                int temp = nums[replaceIndex];
                //将替换值，给到被替换值位置
                nums[replaceIndex] = previous;
                //保存新的替换值
                previous = temp;
                count++;
            } while (i != replaceIndex);
        }

    }


    //三次反转
    public void threeSwap(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        //先转换一次全部数组
        revert(nums, 0, nums.length - 1);
        //转换一下前k个
        revert(nums, 0, k - 1);
        //转换一下后nums.length-k个
        revert(nums, k, nums.length - 1);
    }

    private void revert(int[] nums, int start, int end) {
        //如果头尾指针相同/start超过end则不用反转
        while (start < end) {
            int tmp = nums[end];
            nums[end] = nums[start];
            nums[start] = tmp;
            start++;
            end--;
        }
    }

    //临时变量做为需要替换元素，遍历更新。
    public void preForLoopSwap(int[] nums, int k) {
        if (k == 0 || nums.length == 0) {
            return;
        }
        int tmp, previous;
        for (int j = 0; j < k; j++) {
            previous = nums[nums.length - 1];
            for (int i = 0; i < nums.length; i++) {
                tmp = nums[i];
                nums[i] = previous;
                previous = tmp;
            }
        }
    }

    //最后的元素一直往前swap到顶点
    public void forLoopSwap(int[] nums, int k) {
        k = k % nums.length;
        for (int i = 0; i < k; i++) {
            for (int j = nums.length - 1; j > 0; j--) {
                int tmp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = tmp;
            }
        }
    }

    //使用额外数组 存储正确结果
    public void newArray(int[] nums, int k) {
        if (k == 0) {
            return;
        }
        int[] tmp = new int[nums.length];
        //循环放到新数组
        k = k % nums.length;
        for (int j = 0; j < nums.length; j++) {
            tmp[(j + k) % nums.length] = nums[j];
        }
        //遍历新数组，替换当前结果
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmp[i];
        }
    }


}
