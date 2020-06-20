package LeetCode.LC1_100;

import java.util.*;

/**
 * 3Sum
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class LC0015 {
    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        int len = nums.length;
        if (len < 3) {
            return res;
        }

        Arrays.sort(nums);

        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int start = i + 1;
            int end = len - 1;

            while (start < end) {
                long sum = nums[i] + nums[start] + nums[end];
                if ((start > i + 1 && nums[start] == nums[start - 1]) || sum < target) { // notice handle duplicate
                    start++;
                } else if ((end < len - 1) && nums[end] == nums[end + 1] || sum > target) {
                    end--;
                } else {
                    List<Integer> triplet = Arrays.asList(nums[i], nums[start], nums[end]);
                    res.add(triplet);

                    start++;
                    end--;
                }
            }
        }

        return res;
    }

    public int threeSumCount(int[] nums, int target) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0; i < len - 2; i++) {
            int start = i + 1;
            int end = len - 1;

            while (start < end) {
                long sum = nums[i] + nums[start] + nums[end];
                if (sum < target) { // notice handle duplicate
                    start++;
                } else if (sum > target) {
                    end--;
                } else {
                    int m = 1;
                    while (start < end && nums[start] == nums[++start]) {
                        m++;
                    }

                    int n = 1;
                    while (start < end && nums[end] == nums[--end]) {
                        n++;
                    }

                    cnt += m * n;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        LC0015 so = new LC0015();
        int[] array = new int[] {0, 0, 0};
        List<List<Integer>> res = so.threeSum(array, 0);
        System.out.println(res);
        array = new int[] {1, 3, 3, 3, 4, 5, 6, 6, 7};
        int cnt = so.threeSumCount(array, 10);
        System.out.println(cnt);
    }
}
