package LeetCode.LC601_700;

import java.util.LinkedList;
import java.util.List;

/**
 * Find K Closest Elements
 *
 * Given a sorted array arr, two integers k and x, find the k closest elements
 * to x in the array. The result should also be sorted in ascending order.
 * If there is a tie, the smaller elements are always preferred.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 10^4
 * Absolute value of elements in the array and x will not exceed 104
 */
public class LC0658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new LinkedList<>();
        if (arr == null || arr.length == 0 || k > arr.length || k < 0) {
            return res;
        }

        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                break;
            }

            if (arr[mid] < x) {
                left = mid + 1;
            } else if (arr[mid] > x) {
                right = mid - 1;
            }
        }
        if (arr[mid] != x) {
            mid = left;
        }

        left = mid - 1;
        right = mid;
        for (int i = 0; i < k; i++) {
            if (right < arr.length &&
                    (left < 0 || Math.abs(arr[left] - x) > Math.abs(arr[right] - x))) {
                res.add(arr[right++]);
            } else {
                res.add(0, arr[left--]);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LC0658 so = new LC0658();
        int[] arr = {0, 1, 1, 1, 2, 3, 6, 7, 8, 9};
        int k = 9;
        int x = 4;
        List<Integer> res = so.findClosestElements(arr, k, x);
        System.out.println(res);
    }
}
