package LeetCode.LC401_500;
import java.util.*;

/**
 * All O`one Data Structure
 *
 * Implement a data structure supporting the following operations:
 *
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1.
 * Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
 * If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 */
 class AllOne {
    HashMap<String, Integer> hashMap;
    TreeMap<Integer, Set<String>> treeMap;
    /** Initialize your data structure here. */
    public AllOne() {
        hashMap = new HashMap<>();
        treeMap = new TreeMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Integer val = 1;
        if (!hashMap.containsKey(key)) {
            hashMap.put(key, val);
        } else {
            val = hashMap.get(key);
            treeMap.get(val).remove(key);
            if (treeMap.get(val).size() == 0) {
                treeMap.remove(val);
            }
            hashMap.put(key, ++val);
        }

        if (!treeMap.containsKey(val)) {
            treeMap.put(val, new HashSet<>());
        }
        treeMap.get(val).add(key);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!hashMap.containsKey(key)) {
            return;
        }

        Integer val = hashMap.get(key);
        treeMap.get(val).remove(key);
        if (treeMap.get(val).size() == 0) {
            treeMap.remove(val);
        }

        if (--val == 0) {
            hashMap.remove(key);
        } else {
            hashMap.put(key, val);
            if (!treeMap.containsKey(val)) {
                treeMap.put(val, new HashSet<>());
            }
            treeMap.get(val).add(key);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return treeMap.size() == 0 ? "" : treeMap.get(treeMap.lastKey()).iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return treeMap.size() == 0 ? "" : treeMap.get(treeMap.firstKey()).iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
