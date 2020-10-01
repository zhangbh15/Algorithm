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
 *
 *
 *
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
 class AllOne { // TODO: doubly linked list
     private final Map<String, Integer> map;
     private final List<Set<String>> buckets;
     private int minVal;
     private int maxVal;

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        buckets = new ArrayList<>();
        buckets.add(new HashSet<>());
        minVal = 0;
        maxVal = 0;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (key == null) {
            return;
        }

        int val = map.getOrDefault(key, 0);
        map.put(key, val + 1);
        bucketInc(key, val);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (key == null) {
            return;
        }

        Integer val = map.get(key);
        if (val == null) {
            return;
        }

        if (val == 1) {
            map.remove(key);
        } else {
            map.put(key, val - 1);
        }
        bucketDec(key, val);
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (maxVal == 0) {
            return "";
        }

        Set<String> maxSet = buckets.get(maxVal);
        return maxSet.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (minVal == 0) {
            return "";
        }

        Set<String> minSet = buckets.get(minVal);
        return minSet.iterator().next();
    }


    private void bucketInc(String key, int val) {
        if (val == maxVal) {
            buckets.add(new HashSet<>());
            maxVal = val + 1;
        }

        buckets.get(val).remove(key);
        buckets.get(val + 1).add(key);

        if (val == minVal && buckets.get(minVal).isEmpty()) {
            minVal++;
        }
        if (val < minVal) {
            minVal = val + 1;
        }
    }

    private void bucketDec(String key, int val) {
        buckets.get(val).remove(key);
        if (val == maxVal && buckets.get(maxVal).isEmpty()) {
            maxVal--;
            buckets.remove(buckets.size() - 1);
        }

        if (val > 1) {
            buckets.get(val - 1).add(key);
            minVal = Math.min(minVal, val - 1);
        } else {
            for (int i = 0; i < buckets.size(); i++) {
                if (!buckets.get(i).isEmpty()) {
                    minVal = i;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        AllOne so = new AllOne();
        so.inc("a");
        so.inc("a");
        so.inc("b");
        so.inc("b");

        so.inc("a");
        so.dec("b");

        System.out.println(so.getMaxKey());
        System.out.println(so.getMinKey());
        so.inc("b");
        so.inc("b");
        so.inc("c");
        System.out.println(so.getMaxKey());
        System.out.println(so.getMinKey());

        so.inc("c");
        so.inc("c");
        System.out.println(so.getMinKey());
    }
}

