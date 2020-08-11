package LeetCode.LC900_;

import java.util.Random;

/**
 * Sort an Array
 *
 * Given an array of integers nums, sort the array in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000
 */
public class LC0912 {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int[] helper = new int[nums.length];
        mergeSort(helper, nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] helper, int[] array, int start, int end) {
        if (start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(helper, array, start, mid);
        mergeSort(helper, array, mid + 1, end);

        merge(helper, array, start, mid, end);
    }

    private void merge(int[] helper, int[] array, int start, int mid, int end) {
        int cur1 = start;
        int cur2 = mid + 1;

        int i = start;
        while (cur1 <= mid && cur2 <= end) {
            helper[i++] = array[cur1] <= array[cur2] ? array[cur1++] : array[cur2++];
        }

        while (cur1 <= mid) {
            helper[i++] = array[cur1++];
        }
        while (cur2 <= end) {
            helper[i++] = array[cur2++];
        }

        for (i = start; i <= end; i++) {
            array[i] = helper[i];
        }
    }

    private void quickSort(Random rand, int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = start + rand.nextInt(end - start + 1); // avoid worst case
        if ((array[pivot] - array[start]) * (array[start] - array[end]) > 0) {
            pivot = start;
        } else if ((array[pivot] - array[end]) * (array[end] - array[start]) > 0) {
            pivot = end;
        }
        swap(array, pivot, end);

        int left = start;
        int right = end - 1;
        while (left <= right) {
            if (array[left] > array[end] && array[right] <= array[end]) {
                swap(array, left++, right--);
                continue;
            }

            if (array[left] <= array[end]){
                left++;
            }
            if (array[right] > array[end]) {
                right--;
            }
        }

        swap(array, left, end);
        quickSort(rand, array, start, left - 1);
        quickSort(rand, array, left + 1, end);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
