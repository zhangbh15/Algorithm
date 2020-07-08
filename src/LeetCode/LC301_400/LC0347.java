package LeetCode.LC301_400;

import java.util.*;

/**
 * Top K Frequent Elements
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */
public class LC0347 {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int n : nums) {
            Integer cur = cnt.getOrDefault(n, 0);
            cnt.put(n, cur + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparing(cnt::get));
        for (int n : cnt.keySet()) {
            heap.offer(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] res = new int[k];
        int i = 0;
        for(int n : heap) {
            res[i++] = n;
        }
        return res;
    }

    public int[] topKFrequentQuickSelect(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int n : nums) {
            Integer cur = cnt.getOrDefault(n, 0);
            cnt.put(n, cur + 1);
        }

        Set<Integer> keySet = cnt.keySet();
        int[] array = new int[keySet.size()];
        int i = 0;
        for (int n : keySet) {
            array[i++] = n;
        }

        quickSelect(array, 0, i - 1, k, cnt);

        int[] res = new int[k];
        for (i = 0; i < k; i++) {
            res[i] = array[i];
        }
        return res;
    }

    private void quickSelect(int[] array, int start, int end, int k, Map<Integer, Integer> map) {
        if (start >= k || end < k) {
            return;
        }

        int pivot = array[end];
        int left = start;
        int right = end - 1;
        while (left <= right) {
            if (compare(array[left], pivot, map) > 0 && compare(array[right], pivot, map) <= 0) {
                swap(array, left++, right--);
            }
            if (compare(array[left], pivot, map) <= 0) {
                left++;
            }
            if (compare(array[right], pivot, map) > 0) {
                right--;
            }
        }
        swap(array, left, end);
        quickSelect(array, start, left - 1, k, map);
        quickSelect(array, left + 1, end, k, map);
    }

    private int compare(int o1, int o2, Map<Integer, Integer> map) {
        return map.get(o2).compareTo(map.get(o1));
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        LC0347 so = new LC0347();
        int[] array = {1,2,2,3,3,3,4,4,4,4,5,5,5,5,5};
        int k = 2;
        int[] res = so.topKFrequentQuickSelect(array, k);
        System.out.println(Arrays.toString(res));
    }
}
