package LeetCode.LC1_100;

/**
 * Sort Color
 *
 * Given an array with n objects colored red, white or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order
 * red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite
 * array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class LC0075 {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int left = 0;
        int mid = 0;
        int right = nums.length - 1;
        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(nums, left++, mid++);
            } else if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 2){
                swap(nums, mid, right--);
            } else {
                throw new IllegalArgumentException("Unsupported value.");
            }
        }
    }

    private void swap(int[] array, int i , int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}