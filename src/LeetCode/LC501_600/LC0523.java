package LeetCode.LC501_600;

import java.util.HashMap;
import java.util.Map;

/**
 * Continuous Subarray Sum
 *
 * Given a list of non-negative numbers and a target integer k, write a function
 * to check if the array has a continuous subarray of size at least 2 that sums up
 * to a multiple of k, that is, sums up to n*k where n is also an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 *
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 *
 * Constraints:
 *
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class LC0523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int len = nums.length;
        int[] prefixSum = new int[len + 1];

        prefixSum[0] = 0;
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 2; j <= len; j++) {
                int sum = prefixSum[j] - prefixSum[i];
                if (k == 0 && sum == 0) {
                    return true;
                }
                if (k != 0 && sum % k == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    // O(n) solution
    public boolean checkHashMap(int[] nums, int k) {
        // cc

        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k; // if prefix sum at i and j have the same modulus, the subarray sum is a multiple of k
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }
}
