package LeetCode.LC1_100;

/**
 * Jump Game II
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 *
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 *
 * You can assume that you can always reach the last index.
 */
public class LC0045 {
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int len= nums.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            // if (dp[i] == 0) {return -1;}

            int maxJump = Math.min(len - 1, i + nums[i]);
            while (maxJump >= 0 && dp[maxJump] == 0) {
                dp[maxJump--] = dp[i] + 1;
            }

            if (dp[len - 1] != 0) {
                return dp[len - 1];
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        LC0045 so = new LC0045();
        int[] nums = {2, 3, 1, 1, 4};
        int res = so.jump(nums);
        System.out.println(res);
    }
}
