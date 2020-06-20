package LeetCode.LC201_300;

import java.util.*;

/**
 * Longest Increasing Subsequence
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LC0300 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        List<Integer> buffer = new ArrayList<>();

        for (int num : nums) {
            int idx = findIdx(buffer, num);
            if (idx < buffer.size()) {
                buffer.set(idx, num);
            } else {
                buffer.add(num);
            }
        }

        return buffer.size();
    }

    private int findIdx(List<Integer> array, int target) {
        int start = 0;
        int end = array.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array.get(mid) == target) {
                return mid;
            }

            if (array.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    private int findIdxNonDesc(List<Integer> array, int target) {
        int start = 0;
        int end = array.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array.get(mid) <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    public List<Integer> pathOfLIS(int[] nums) {
        List<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        List<Integer> buffer = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            int idx = findIdx(buffer, num);
            if (idx < buffer.size()) {
                buffer.set(idx, num);
            } else {
                buffer.add(num);
            }

            if (idx > 0) {
                map.put(num, buffer.get(idx - 1));
            }
        }

        int cur = buffer.get(buffer.size() - 1);
        res.add(cur);
        while (map.containsKey(cur)) {
            cur = map.get(cur);
            res.add(0, cur);
        }

        return res;
    }

    public static void main(String[] args) {
        LC0300 so = new LC0300();
        int[] array = new int[] {10,9,2,5,3,7,101,18};
        int res = so.lengthOfLIS(array);
        List<Integer> resList = so.pathOfLIS(array);
        System.out.println(res);
        System.out.println(resList);
    }
}
