package LeetCode.LC101_200;
import java.util.*;

/**
 * LRU Cache
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2 ) // capacity
 *
 *cache.put(1,1);
 *cache.put(2,2);
 *cache.get(1);       // returns 1
 *cache.put(3,3);    // evicts key 2
 *cache.get(2);       // returns -1 (not found)
 *cache.put(4,4);    // evicts key 1
 *cache.get(1);       // returns -1 (not found)
 *cache.get(3);       // returns 3
 *cache.get(4);       // returns 4
 */
public class LC0146 {
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    class LRUCache {
        private int cap;
        private Node head;
        private Node tail;
        private Map<Integer, Node> map;

        public LRUCache(int capacity) {
            try {
                if (capacity <= 0) {
                    throw new IllegalArgumentException("Capacity must be positive.");
                }

                head = null;
                tail = null;
                map = new HashMap<>();
                cap = capacity;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

        private void popToHead(Node cur) {
            if (cur == head) {
                return;
            }

            if (cur == tail) {
                tail = cur.prev;
            } else {
                cur.next.prev = cur.prev;
//                cur.next = null;
            }

            cur.prev.next = cur.next;
            cur.prev = null;
            head.prev = cur;
            cur.next = head;
            head = cur;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            Node ret = map.get(key);
            popToHead(ret);
            return ret.val;
        }

        public void put(int key, int value) {
            try {
                if (value <= 0) {
                    throw new IllegalArgumentException("value must be positive.");
                }

                if (map.containsKey(key)) {
                    Node cur = map.get(key);
                    cur.val = value;
                    popToHead(cur);
                } else {
                    while (map.size() >= cap) {
                        evict();
                    }

                    if (map.size() == 0) {
                        head = new Node(key, value);
                        tail = head;
                        map.put(key, head);
                    } else {
                        Node cur = new Node(key, value);
                        cur.next = head;
                        head.prev = cur;
                        head = cur;
                        map.put(key, cur);
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

        private void evict() {
            map.remove(tail.key);
            if (cap == 1) {
                head = null;
                tail = null;
            } else {
                Node cur = tail;
                cur.prev.next = null;
                tail = cur.prev;
                cur.prev = null;
            }
        }
    }

    static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }

    private LRUCache newCache(int cap) {
        return new LRUCache(cap);
    }

    public static void main(String[] args) {
        LC0146 so = new LC0146();
        LRUCache cache = so.newCache(1);
        cache.put(2, 1);
        System.out.println(cache.get(2));       // returns 1
        cache.put(3, 2);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns -1 (not found)
    }
}
