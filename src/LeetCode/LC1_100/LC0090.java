package LeetCode.LC1_100;

import java.util.*;

/**
 * Subsets II
 *
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */

// Sort first to make the duplicates adjacent!!

public class LC0090 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }

        Arrays.sort(nums);
        dfs(new ArrayList<>(), 0, res, nums);
        return res;
    }

    private void dfs(List<Integer> subset, int i, List<List<Integer>> res, int[] nums) {
        res.add(new ArrayList<>(subset));

        for (int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }

            subset.add(nums[j]);
            dfs(subset, j + 1, res, nums);
            subset.remove(subset.size() - 1);
        }
    }

    private void dfs2(List<Integer> subset, int i, List<List<Integer>> res, int[] nums) {
        if (i == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[i]);
        dfs(subset, i + 1, res, nums);
        subset.remove(subset.size() - 1);

        while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
            i++;
        }
        dfs(subset, i + 1, res, nums);
    }

    public static void main(String[] args) {
        LC0090 so = new LC0090();
        int[] nums = new int[] {4, 4, 1, 4};
        List<List<Integer>> res = so.subsetsWithDup(nums);
        System.out.println(res);
    }
}
