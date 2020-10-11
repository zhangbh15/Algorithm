package LeetCode.LC301_400;

/**
 * Burst Balloons
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class LC0312 {
    public int maxCoins(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        if (nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int[][] dp = new int[len][len];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                int leftBound = i == 0 ? 1 : nums[i - 1];
                int rightBound = j == len - 1 ? 1 : nums[j + 1];

                for (int k = i; k <= j; k++) {
                    int leftSub = k == i ? 0 : dp[i][k - 1];
                    int rightSub = k == j ? 0 : dp[k + 1][j];
                    int coin = leftSub + rightSub + nums[k] * leftBound * rightBound;
                    // Always burst the balloon at k last, so that the boundaries of
                    // the left and right subranges are fixed.

                    dp[i][j] = Math.max(dp[i][j], coin);
                }
            }
        }

        return dp[0][len - 1];
    }
}
