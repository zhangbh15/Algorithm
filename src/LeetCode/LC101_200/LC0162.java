package LeetCode.LC101_200;

/**
 * Find Peak Element
 *
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 *              or index number 5 where the peak element is 6.
 * Follow up: Your solution should be in logarithmic complexity.
 */
public class LC0162 {
    public int findPeakElement(int[] nums) {
         if (nums == null || nums.length == 0) {
             return -1;
         }
         int len = nums.length;
         if (len == 1 ||  nums[0] > nums[1])  {
             return 0;
         }
         if (nums[len - 1] > nums[len - 2]) {
             return len - 1;
         }

         int start = 1;
         int end = len - 2;
         while (start <= end) {
             int mid = start + (end - start) / 2;
             if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                 return mid;
             }

             if (nums[mid] < nums[mid + 1]) {
                 start = mid + 1;
             } else if (nums[mid] < nums[mid - 1]) {
                 end = mid - 1;
             } else {
                 throw new IllegalArgumentException("Invalid input array.");
             }
         }

         throw new RuntimeException("Should not reach here.");
    }
}
