package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.*;

/**
 * 克隆图
 */
public class Solution133 {

    public static void main(String[] args) {
        Solution133 solution133 = new Solution133();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors = Arrays.asList(node2, node4);
        node2.neighbors = Arrays.asList(node1, node3);
        node3.neighbors = Arrays.asList(node2, node4);
        node4.neighbors = Arrays.asList(node1, node3);
        Node copy = solution133.cloneGraph1(node1);
        System.out.println("");
    }

    //递归 边遍历边创建
    public Node cloneGraph(Node node) {
        return deep(node, new HashMap<Integer, Node>());
    }

    private Node deep(Node node, HashMap<Integer, Node> visited) {
        if (node == null) {
            return null;
        }

        //创建当前val
        Node curNode = new Node(node.val);
        visited.put(curNode.val, curNode);
        if (node.neighbors != null) {
            List<Node> curNeighbors = new ArrayList<>();
            for (Node nNode : node.neighbors) {
                if (!visited.containsKey(nNode.val)) {
                    Node curNeiNode = deep(nNode, visited);
                    if (curNeiNode != null) {
                        curNeighbors.add(curNeiNode);
                    }
                } else {
                    curNeighbors.add(visited.get(nNode.val));
                }
            }
            curNode.neighbors = curNeighbors;
        }
        return curNode;
    }

    //获取关系 重建
    public Node cloneGraph1(Node node) {
        if (node == null || node.neighbors == null) {
            return node;
        }
        Map<Integer, List<Node>> data = new HashMap<>();
        //获取关系
        deep(data, node);
        List<Node> resList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            resList.add(new Node(i + 1));
        }
        for (Map.Entry<Integer, List<Node>> entry : data.entrySet()) {
            Node curNode = resList.get(entry.getKey() - 1);
            List<Node> curNei = new ArrayList<>();
            for (Node nei : entry.getValue()) {
                Node neied = resList.get(nei.val - 1);
                curNei.add(neied);
            }
            curNode.neighbors = curNei;
        }
        return resList.get(0);
    }

    private void deep(Map<Integer, List<Node>> data, Node node) {
        if (!data.containsKey(node.val)) {
            data.put(node.val, node.neighbors);
            for (Node curNeiNode : node.neighbors) {
                deep(data, curNeiNode);
            }
        }
    }


}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
