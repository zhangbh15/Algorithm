package LeetCode.LC1_100;

import java.util.*;

/**
 * Permutations
 *
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class LC0046 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        dfs(nums, 0, res);
        return res;
    }

    private void dfs(int[] nums, int i, List<List<Integer>> res) {
        if (i == nums.length) {
            List<Integer> sol = new ArrayList<>();
            for (int n : nums) {
                sol.add(n);
            }
            res.add(sol);
        }

        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            dfs(nums, i + 1, res);
            swap(nums, i, j);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
