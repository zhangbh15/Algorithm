package LeetCode.LC101_200;

/**
 * Single Number II
 *
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once.
 * Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 */
public class LC0137 {
    public int singleNumber(int[] nums) {
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
            if (cnt[i] % 3 != 0) {
                res += (1 << i);
            }
        }
        return res;
    }
}
