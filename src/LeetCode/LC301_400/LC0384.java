package LeetCode.LC301_400;

import java.util.Random;

/**
 * Shuffle an Array
 *
 * Shuffle a set of numbers without duplicates.
 *
 * Example:
 *
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 *
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 *
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 *
 *
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
public class LC0384 {
    private static class Solution {
        private final int[] originalArray;
        private int[] shuffleArray;
        private final Random rand;

        public Solution(int[] nums) {
            this.originalArray = nums;
            this.shuffleArray = nums.clone();
            this.rand = new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            this.shuffleArray = this.originalArray.clone();
            return this.shuffleArray;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            for (int i = this.shuffleArray.length - 1; i > 0; i--) {
                swap(shuffleArray, i, rand.nextInt(i + 1));
            }
            return this.shuffleArray;
        }

        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
