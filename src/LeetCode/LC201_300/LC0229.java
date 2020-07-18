package LeetCode.LC201_300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Majority Element II
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class LC0229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        int maj1 = 0;
        int maj2 = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (cnt1 == 0 && (cnt2 == 0 || num != maj2)) {
                maj1 = num;
                cnt1 = 1;
                continue;
            }
            if (cnt2 == 0 && num != maj1) {
                maj2 = num;
                cnt2 = 1;
                continue;
            }

            if (num == maj1) {
                cnt1++;
            } else if (num == maj2) {
                cnt2++;
            } else {
                cnt1--;
                cnt2--;
            }
        }

        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (num == maj1) {
                cnt1++;
            } else if (num == maj2) {
                cnt2++;
            }
        }

        if (cnt1 > nums.length / 3) {
            res.add(maj1);
        }
        if (cnt2 > nums.length / 3) {
            res.add(maj2);
        }
        return res;
    }

    public static void main(String[] args) {
        LC0229 so = new LC0229();
        int[] nums = {1,1,1,3,3,2,2,2};
        List<Integer> res = so.majorityElement(nums);
        System.out.println(res);
    }
}
