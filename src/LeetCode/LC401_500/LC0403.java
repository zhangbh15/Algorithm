package LeetCode.LC401_500;
import java.util.*;

/**
 * Frog Jump
 *
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 *
 * Given a list of stones' positions (in units) in sorted ascending order,
 * determine if the frog is able to cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 *
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units.
 * Note that the frog can only jump in the forward direction.
 *
 * Note:
 *
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 *
 * Example 1:
 *
 * [0,1,3,5,6,8,12,17]
 *
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 *
 * Return true. The frog can jump to the last stone by jumping
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 * 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 *
 * Example 2:
 *
 * [0,1,2,3,4,8,9,11]
 *
 * Return false. There is no way to jump to the last stone as
 * the gap between the 5th and 6th stone is too large.
 */
public class LC0403 {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length < 1) {
            return false;
        }
        if (stones.length == 1) {
            return true;
        }
        if (stones[1] - stones[0] != 1) {
            return false;
        }

        int len = stones.length;
        Map<Integer, Boolean>[] mem = new HashMap[len];
        for (int i = 0; i < len; i++) {
            mem[i] = new HashMap<>();
        }

        return dfs(1, 1, stones, mem);
    }

    private boolean dfs(int k, int idx, int[] stones, Map<Integer, Boolean>[] mem) {
        if (idx == stones.length - 1) {
            return true;
        }

        Map<Integer, Boolean> map = mem[idx];
        if (map.containsKey(k)) {
            return map.get(k);
        }

        for (int i = idx + 1; i < stones.length; i++) {
            int step = stones[i] - stones[idx];
            if (step < k - 1) {
                continue;
            }
            if (step > k + 1) {
                break;
            }

            if (dfs(step, i, stones, mem)) {
                map.put(k, true);
                return true;
            }
        }

        map.put(k, false);
        return false;
    }

    public boolean canCrossDP(int[] stones) {
        if (stones == null || stones.length < 1) {
            return false;
        }
        if (stones.length == 1) {
            return true;
        }
        if (stones[1] - stones[0] != 1) {
            return false;
        }

        int len = stones.length;
        Map<Integer, Integer> map = new HashMap<>(); // From stone position to array index
        for (int i = 0; i < len; i++) {
            map.put(stones[i], i);
        }

        boolean[][] dp = new boolean[len][len];
        dp[0][1] = true;

        int maxK = 1;
        for (int i = 1; i < len; i++) {
            for (int k = 1; k <= Math.min(len - 1, maxK + 1); k++) {
                for (int j = k - 1; j <= Math.min(k + 1, len - 1); j++) {
                    int prev = stones[i] - j;
                    if (map.containsKey(prev) && dp[map.get(prev)][j]) {
                        dp[i][k] = true;
                        maxK = Math.max(maxK, k);
                        break;
                    }
                }
            }
        }

        for (int i = 0; i <= maxK; i++) {
            if (dp[len - 1][i]) {
                return true;
            }
        }

        return false;
    }
}
