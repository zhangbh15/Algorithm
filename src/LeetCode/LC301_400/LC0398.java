package LeetCode.LC301_400;

import java.util.Random;

/**
 * Random Pick Index
 *
 * Given an array of integers with possible duplicates, randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.
 *
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 *
 * Example:
 *
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 *
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 *
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 *
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
public class LC0398 {
    private final int[] nums;
    public LC0398(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        Integer sample = null;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                cnt++;
                int idx = new Random().nextInt(cnt);
                if (idx == 0) {
                    sample = i;
                }
            }
        }
        if (cnt == 0) {
            throw new RuntimeException("No exist in array.");
        } else {
            return sample;
        }
    }
}


