package LeetCode.LC1_100;

import java.util.*;

/**
 * Permutations II
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */

// Cannot deduplicate by comparing nums[i] with nums[i - 1]!!
// Swap may break the adjacency!
// Must use a HashSet!!!

public class LC0047 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        dfs(nums, 0, res);
        return res;
    }

    private void dfs(int[] nums, int idx, List<List<Integer>> res) {
        if (idx == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int n: nums) {
                perm.add(n);
            }
            res.add(perm);
        }

        Set<Integer> visited = new HashSet<>();
        for (int i = idx; i < nums.length; i++) {
            if (visited.add(nums[i])) {
                swap(nums, i, idx);
                dfs(nums, idx + 1, res);
                swap(nums, i, idx);
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
