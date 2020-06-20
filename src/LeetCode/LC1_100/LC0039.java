package LeetCode.LC1_100;

import java.util.*;

/**
 * Combination Sum
 *
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class LC0039 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }

        dfs(new LinkedList<>(), 0, 0, candidates, target, res);
        return res;
    }

    private void dfs(List<Integer> sol, int sum, int idx, int[] candidates, int target, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new LinkedList<>(sol));
            return;
        }
        if (sum > target) {
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            sol.add(candidates[i]);
            dfs(sol, sum + candidates[i], i, candidates, target, res);
            sol.remove(sol.size() - 1);
        }
    }
}
