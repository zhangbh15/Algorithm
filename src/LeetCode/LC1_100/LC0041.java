package LeetCode.LC1_100;

/**
 * First Missing Positive
 *
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 *
 * Your algorithm should run in O(n) time and uses constant extra space.
 */
public class LC0041 {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int val = nums[i];
            if (val <= 0 || val > len) {
                nums[i] = Integer.MAX_VALUE;
            }
        }

        for (int n : nums) {
            n = Math.abs(n);
            if (n <= len && nums[n - 1] > 0) {
                nums[n - 1] *= -1;
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return len + 1;
    }

    public int firstMissingPositiveBit(int[] nums) {
        int length = nums.length;
        int[] bit = new int[(length - 1) / 32 + 1];

        for (int digit : nums) {
            if (digit >= 1 && digit <= length) {
                int index = (digit - 1) / 32;
                bit[index] |= (1 << ((digit - 1) % 32));
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if ((bit[i / 32] & (1 << (i % 32))) == 0)
                return i + 1;
        }
        return length + 1;
    }

    public static void main(String[] args) {
        LC0041 so = new LC0041();
        int[] array = {3, 4, -1, 1};
        int res = so.firstMissingPositive(array);
        System.out.println(res);
    }
}
