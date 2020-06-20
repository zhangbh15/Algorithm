package LeetCode.LC101_200;

/**
 * Single Number
 *
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class LC0136 {
    public int singleNumber(int[] nums) {
        // cc
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }

    public static void main(String[] args) {
        LC0136 so = new LC0136();
        int[] input = new int[] {4, 1, 2, 1, 2};
        int res = so.singleNumber(input);
        System.out.println(res);
    }
}
