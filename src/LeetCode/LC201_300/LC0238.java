package LeetCode.LC201_300;

import java.util.ArrayList;
import java.util.List;

/**
 * Product of Array Except Self
 *
 * Given an array nums of n integers where n > 1,  return an array output such that output[i]
 * is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the
 * array (including the whole array) fits in a 32 bit integer.
 *
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra
 * space for the purpose of space complexity analysis.)
 */
public class LC0238 {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int totalProduct = 1;
        List<Integer> zeroIdx = new ArrayList<>();

        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                zeroIdx.add(i);
                if (zeroIdx.size() > 1) {
                    return res;
                }
                continue;
            }
            totalProduct *= nums[i];
        }

        if (zeroIdx.size() == 1) {
            res[zeroIdx.get(0)] = totalProduct;
            return res;
        }

        for (int i = 0; i < len; i++) {
            res[i] = totalProduct / nums[i];
        }
        return res;
    }

    public int[] productExceptSelfNoList(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int totalProduct = 1;
        int cntZero = 0;

        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                cntZero++;
                if (cntZero > 1) {
                    return res;
                }
                continue;
            }
            totalProduct *= nums[i];
        }

        if (cntZero == 1) {
            for (int i = 0; i < len; i++) {
                if (nums[i] == 0) {
                    res[i] = totalProduct;
                    break;
                }
            }
        } else {
            for (int i = 0; i < len; i++) {
                res[i] = totalProduct / nums[i];
            }
        }

        return res;
    }
}
