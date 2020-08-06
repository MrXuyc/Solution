package com.mrxyc.solution.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * //评测题目: 有向无环图
 *
 *
 *
 *
 * 给定一组数据节点，每个节点使用中括号括起来，多个节点之间使用半角逗号分隔，如下所示： [1-2],[2-3],[3-4,5,6],[4-],[5-8],[6-7],[7-],[8-2]
 * 节点内部结构使用“-”分隔，前半部分为节点ID，后半部分为该节点指向的节点，如果没有指向，则为空，多个指向节点使用半角逗号分隔；
 *
 *
 * 给定任意一组数据，请计算节点之间是否存在环？如果存在环，直接结束；不存在环，则打印出每条路径。
 */
public class Ali2 {

    //减少判断重复链路。已触碰节点，不进行遍历
    public static Set<Integer> force = new HashSet<>();

    public static void main(String[] args) {
        //存数
        Map<Integer, List<Integer>> arg = new HashMap<>();
        arg.put(1, new ArrayList<>(Arrays.asList(2)));
        arg.put(2, new ArrayList<>(Arrays.asList(3)));
        arg.put(3, new ArrayList<>(Arrays.asList(4, 5, 6)));
        arg.put(4, new ArrayList<>());
        arg.put(5, new ArrayList<>(Arrays.asList(8)));
        arg.put(6, new ArrayList<>(Arrays.asList(7)));
        arg.put(7, new ArrayList<>());
        arg.put(10, new ArrayList<>(Arrays.asList(2)));
        arg.put(8, new ArrayList<>(Arrays.asList(9)));
        List<List<Integer>> res = new ArrayList<>();
        boolean flag = false;
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        //获取入口
        for (Integer key : arg.keySet()) {
            if (!force.contains(key)) {
                flag = deep(set, list, new ArrayList<>(Arrays.asList(key)), arg, res);
            }
            if (!flag) {
                System.out.println("有环");
                return;
            }
        }

        for (List<Integer> s : res) {
            System.out.println(s);
        }

    }

    public static boolean deep(Set<Integer> set, List<Integer> list, List<Integer> curNodes, Map<Integer, List<Integer>> arg, List<List<Integer>> res) {

        for (int i = 0; i < curNodes.size(); i++) {
            Integer curNode = curNodes.get(i);
            if (set.contains(curNode)) {
                //set已经存在该值。有环
                return false;
            }
            //当前层处理
            //用set记录已经遍历的值
            set.add(curNode);
            //list存储路径
            list.add(curNode);
            //全局set 触摸已遍历节点（已触摸代表不会为根节点）
            force.add(curNode);
            //递归
            if (arg.get(curNode) != null && !arg.get(curNode).isEmpty()) {
                if (!deep(set, list, arg.get(curNode), arg, res)) {
                    return false;
                }
            } else {
                //如果当前节点curNode下层是空的 则保存
                if (!list.isEmpty()) {
                    List<Integer> temp = new ArrayList<>(list);
                    res.add(temp);
                }
            }
            //清理状态
            set.remove(curNode);
            list.remove(curNode);
        }
        return true;
    }
}
