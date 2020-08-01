package LeetCode.LC101_200;

/**
 * Maximum Product Subarray
 *
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class LC0152 {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int[][] dp = new int[len][len];
        int max = nums[len - 1];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                } else {
                    dp[i][j] = dp[i][j - 1] * nums[j];
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }

    public int o1Space(int[] nums) {
        // cc
        int len = nums.length;
        int start = nums[0];
        int max = start;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                start = i == j ? nums[i] : start * nums[j];
                max = Math.max(max, start);
            }
        }

        return max;
    }

    public int dp(int[] nums) {
        // cc
        int min = nums[0];
        int max = nums[0];
        int res = max;

        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            max *= cur;
            min *= cur;

            int tempMax = Math.max(cur, Math.max(max, min));
            min = Math.min(cur, Math.min(max, min));
            max = tempMax;

            res = Math.max(max, res);
        }

        return res;
    }

    public static void main(String[] args) {
        LC0152 so = new LC0152();
        int[] nums = {2, 3, -2, 4};
        int res = so.o1Space(nums);
        System.out.println(res);
    }
}
