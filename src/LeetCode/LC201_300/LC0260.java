package LeetCode.LC201_300;

import java.util.Arrays;

/**
 * Single Number III
 *
 * Given an array of numbers nums,
 * in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once.
 *
 * Example:
 *
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * Note:
 *
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */
public class LC0260 {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }

        int mask = 1;
        while ((xor & mask) == 0) {
            mask <<= 1;
        }

        int group1 = 0;
        int group2 = 0;

        for (int n : nums) {
            if ((n & mask) == 0) {
                group1 ^= n;
            } else {
                group2 ^= n;
            }
        }

        return new int[] {group1, group2};
    }

    public static void main(String[] args) {
        LC0260 so = new LC0260();
        int[] input = new int[] {1, 2, 1, 3, 2, 5};
        int[] res = so.singleNumber(input);
        System.out.println(Arrays.toString(res));
    }
}
