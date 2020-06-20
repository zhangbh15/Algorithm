package LeetCode.LC201_300;

import java.util.Arrays;

/**
 * 3Sum Smaller
 *
 * Given an array of n integers nums and a target,
 * find the number of index triplets i, j, k with 0 <= i < j < k < n that
 * satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * Example:
 *
 * Input: nums = [-2,0,1,3], and target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 *              [-2,0,1]
 *              [-2,0,3]
 * Follow up: Could you solve it in O(n2) runtime?
 */
public class LC0259 {
    public int threeSumSmaller(int[] nums, int target) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        Arrays.sort(nums);

        int cnt = 0;
        for (int i = 0; i < len - 2; i++) {
            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                long sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    cnt += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        LC0259 so = new LC0259();
        int[] array = new int[] {-2, 0, 1, 3};
        int target = 2;
        System.out.print(so.threeSumSmaller(array, target));
    }
}
