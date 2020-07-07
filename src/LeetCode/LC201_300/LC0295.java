package LeetCode.LC201_300;

import java.util.*;

/**
 * Find Median from Data Stream
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
public class LC0295 {
    // add-heavy
    private static class MedianFinder0to100 {
        private final int[] buckets;
        private int cnt;

        /** initialize your data structure here. */
        public MedianFinder0to100() {
            buckets = new int[101];
            cnt = 0;
        }

        // O(1)
        public void addNum(int num) {
            if (num < 0 || num > 100) {
                return;
            }

            buckets[num]++;
            cnt++;
        }

        // O(101)
        public double findMedian() {
            if (cnt == 0) {
                return 0;
            }

            if (cnt % 2 == 1) {
                int mid = cnt / 2 + 1;
                for (int i = 0; i < 101; i++) {
                    mid -= buckets[i];
                    if (mid <= 0) {
                        return i;
                    }
                }
            } else {
                int mid = cnt / 2;
                for (int i = 0; i < 101; i++) {
                    mid -= buckets[i];
                    if (mid < 0) {
                        return i;
                    }
                    if (mid == 0) {
                        return i + 0.5;
                    }
                }
            }
            throw new RuntimeException("Should not reach here.");
        }
    }

    private static class MedianFinder {
        private final List<Integer> list;

        /** initialize your data structure here. */
        public MedianFinder() {
            list = new ArrayList<>();
        }

        // O(1)
        public void addNum(int num) {
            list.add(num);
        }

        // O(nlogn)
        public double findMedian() {
            Collections.sort(list);
            int size = list.size();
            if (size % 2 == 0) {
                return (double)(list.get(size / 2 - 1) + list.get(size / 2)) / 2;
            } else {
                return list.get(size / 2);
            }
        }
    }

    private static class MedianFinderTwoHeap {
        private final PriorityQueue<Integer> small;
        private final PriorityQueue<Integer> large;

        /** initialize your data structure here. */
        public MedianFinderTwoHeap() {
            small = new PriorityQueue<>();
            large = new PriorityQueue<>((Comparator.reverseOrder()));
        }

        // O(3logN)
        public void addNum(int num) {
            small.add(num);
            large.add(small.poll());

            if (large.size() > small.size()) { // small.size == large.size || small.size == large.size + 1
                small.add(large.poll());
            }
        }

        // O(1)
        public double findMedian() {
            if (small.isEmpty()) {
                return 0;
            }

            return small.size() > large.size() ? small.peek() : (double) (small.peek() + large.peek()) / 2;
        }
    }
}
