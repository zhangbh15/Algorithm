package LeetCode.LC1_100;

/**
 * Median of Two Sorted Arrays
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class LC0004 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return  0.0;
        }

        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        int mid = (len1 + len2 + 1) / 2; // TODO: long

        int start = 0;
        int end = Math.min(len1, mid);
        int i = 0;
        int j = 0;
        while (start <= end) {
            i = start + (end - start) / 2; // number of elements from nums1
            j = mid - i; // number of elements from nums2

            if (i < end && nums1[i] < nums2[j - 1]) {
                start = i + 1;
            } else if ( i > start && nums2[j] < nums1[i - 1]) { //
                end = i - 1;
            } else {
                break;
            }
        }

        double median = i == 0 ? nums2[j - 1] :
                (j == 0 ? nums1[i - 1] : Math.max(nums1[i - 1], nums2[j - 1]));
        if ((len1 + len2) % 2 == 0) {
            median += i == len1 ? nums2[j] :
                    (j == len2 ? nums1[i] : Math.min(nums1[i], nums2[j]));
            median /= 2;
        }
        return median;
    }
}
