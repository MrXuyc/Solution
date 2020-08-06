package com.mrxyc.solution.leecodesecondstep.listnode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU
 */
public class Solution146 {
    class LRUCache1 extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public LRUCache1(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    class LRUCache {

        class Node {
            private int key;
            private int value;
            private Node prev;
            private Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public Node() {
            }
        }

        //总数
        private int capacity;
        //计数
        private int size;

        private Map<Integer, Node> cache;

        private Node head;

        private Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>(capacity);
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node res = cache.get(key);
            if (res == null) {
                //获取对象，如果没有返回默认值-1
                return -1;
            }
            //如果有值则返回缓存cache中结果
            //移动node到首部
            moveNodeToHead(res);
            return res.value;
        }

        private void moveNodeToHead(Node node) {
            //删除节点
            removeNode(node);
            //增加节点
            addNodeToHead(node);
        }

        private void removeNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        private void addNodeToHead(Node node) {
            Node headNext = head.next;
            head.next = node;
            node.prev = head;
            node.next = headNext;
            headNext.prev = node;
        }

        public void put(int key, int value) {
            //判断缓存是否有值
            Node node = cache.get(key);
            if (node == null) {
                //判断大小
                if (size >= capacity) {
                    //超过删除末尾
                    Node del = tail.prev;
                    removeNode(del);
                    //清除缓存
                    cache.remove(del.key);
                    size--;
                }
                //创建节点
                Node cur = new Node(key, value);
                //增加至头
                addNodeToHead(cur);
                //加入缓存
                cache.put(key, cur);
                size++;
            } else {
                node.value = value;
                moveNodeToHead(node);
            }
        }

    }
}
