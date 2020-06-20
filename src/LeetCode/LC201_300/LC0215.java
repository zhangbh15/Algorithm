package LeetCode.LC201_300;

/**
 * Kth Largest Element in an Array
 *
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class LC0215 {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length || k < 0) {
            throw new IllegalArgumentException();
        }

        return findKth(nums, 0, nums.length - 1, k);
    }

    private int findKth(int[] array, int start, int end, int k) {
        if (start == end) {
            return array[start];
        }

        int q = partition(array, start, end);
        int rank = q - start + 1;

        if (rank == k) {
            return array[q];
        } else if (rank < k) {
            return findKth(array, q + 1, end, k - rank);
        } else {
            return findKth(array, start, q - 1, k);
        }
    }

    private int partition(int[] array, int start, int end) {
        int pivot = array[end];

        int left = start - 1;
        for (int i = start; i < end; i++) {
            if (array[i] > pivot) {
                swap(array, ++left, i);
            }
        }

        swap(array, ++left, end);
        return left;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        LC0215 so = new LC0215();
        int[] array = new int[] {3,2,3,1,2,4,5,5,6};
        int k = 4;
        int res = so.findKthLargest(array, k);
        System.out.println(res);
    }
}
