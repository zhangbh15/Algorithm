package LeetCode.LC201_300;

import java.util.*;

public class LC0239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // cc
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (i >= k && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < val) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                res[i - k + 1] = deque.peekFirst();
            }
        }
        
        return res;
    }
}
