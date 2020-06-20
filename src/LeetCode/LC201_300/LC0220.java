package LeetCode.LC201_300;
import java.util.*;

/**
 * Contains Duplicate III
 *
 * Given an array of integers, find out whether there are two distinct indices i and j in the array
 * such that the absolute difference between nums[i] and nums[j] is at most t
 * and the absolute difference between i and j is at most k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 */
public class LC0220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        TreeSet<Long> window = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                window.remove((long)nums[i - k - 1]);
            }
            Long val = window.floor((long)nums[i] + t);
            if (val != null && val >= (long)nums[i] - t) {
                return true;
            }
            window.add((long)nums[i]);
        }
        return false;
    }

    // nums[i] and nums[j] at least t, i and j at most k, with duplicate
    public boolean contains(int[] nums, int k, int t) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        TreeMap<Long, Integer> window = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                int cnt = window.get((long)nums[i - k - 1]);
                if (cnt == 1) {
                    window.remove((long)nums[i - k - 1]);
                } else {
                    window.put((long)nums[i - k - 1], cnt - 1);
                }
            }
            long cur = (long)nums[i];
            if (window.floorKey(cur - t) != null || window.ceilingKey(cur + t) != null) {
                return true;
            }
            window.merge(cur, 1, Integer::sum);
        }
        return false;
    }

    public static void main(String[] args) {
        LC0220 so = new LC0220();
        int[] nums = new int[] {0,2147483647};
        int k = 1;
        int t = 2147483647;
        boolean res = so.containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(res);
    }
}
