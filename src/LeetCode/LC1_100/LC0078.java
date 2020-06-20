package LeetCode.LC1_100;

import java.util.*;

/**
 * Subsets
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class LC0078 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null ) {
            return res;
        }

        List<Integer> subset = new ArrayList<>();
        dfs(subset, 0, res, nums);
        return res;
    }

    private void dfs(List<Integer> subset, int i, List<List<Integer>> res, int[] nums) {
        if (i == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }

        dfs(subset, i + 1, res, nums);
        subset.add(nums[i]);
        dfs(subset, i + 1, res, nums);
        subset.remove(subset.size() - 1);
    }

    private void dfs2(List<Integer> subset, int i, List<List<Integer>> res, int[] nums) {
        res.add(new ArrayList<>(subset));

        for (int j = i; j < nums.length; j++) {
            subset.add(nums[j]);
            dfs(subset, j + 1, res, nums);
            subset.remove(subset.size() - 1);
        }
    }
}
