package LeetCode.LC1_100;

import java.util.*;

/**
 * Combination Sum II
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class LC0040 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }

        Arrays.sort(candidates);
        dfs(new LinkedList<>(), 0, 0, target, candidates, res);
        return res;
    }

    private void dfs(List<Integer> sol, int sum, int idx, int target, int[] candidates, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new LinkedList<>(sol));
            return;
        }
        if (sum > target || idx == candidates.length) {
            return;
        }

        sol.add(candidates[idx]);
        dfs(sol, sum + candidates[idx], idx + 1, target, candidates, res);
        sol.remove(sol.size() - 1);
        while (idx + 1 < candidates.length && candidates[idx + 1] == candidates[idx]) {
            idx++;
        }
        dfs(sol, sum, idx + 1, target, candidates, res);
    }

    private void dfs2(List<Integer> sol, int sum, int idx, int target, int[] candidates, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new LinkedList<>(sol));
            return;
        }
        if (sum > target || idx == candidates.length) {
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (i != idx && candidates[i] == candidates[i - 1]) {
                continue;
            }

            sol.add(candidates[i]);
            dfs(sol, sum + candidates[i], i + 1, target, candidates, res);
            sol.remove(sol.size() - 1);
        }
    }
}
