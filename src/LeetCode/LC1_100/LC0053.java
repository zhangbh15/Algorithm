package LeetCode.LC1_100;

/**
 * Maximum Subarray
 *
 * Given an integer array nums, find the contiguous subarray (containing at
 * least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution
 * using the divide and conquer approach, which is more subtle.
 */
public class LC0053 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int curMax = nums[0];
        int max = curMax;
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(curMax, 0) + nums[i];
            max = Math.max(max, curMax);
        }

        return max;
    }
}
