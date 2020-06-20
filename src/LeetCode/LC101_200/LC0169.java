package LeetCode.LC101_200;

/**
 * Majority Element
 *
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class LC0169 {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int[] cnt = new int[32];
        for (int n : nums) {
            for (int i = 0; i < 32; i++) {
                cnt[i] += n & 1;
                n >>= 1;
            }
        }

        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (cnt[i] > nums.length / 2) {
                res += (1 << i);
            }
        }
        return res;
    }
}
