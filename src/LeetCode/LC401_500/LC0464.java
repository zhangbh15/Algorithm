package LeetCode.LC401_500;


import java.util.HashMap;
import java.util.Map;

/**
 * Can I Win
 *
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10.
 * The player who first causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement
 * until they reach a total >= 100.
 *
 * Given an integer maxChoosableInteger and another integer desiredTotal,
 * determine if the first player to move can force a win, assuming both players play optimally.
 *
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 *
 * Example
 *
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 *
 * Output:
 * false
 *
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 */
public class LC0464 {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if (maxChoosableInteger <= 0 || maxChoosableInteger > 20
                || desiredTotal < 0 || desiredTotal > 300) {
            return false;
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }

        int pool = 0;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            pool = (pool + 1) << 1;
        }

        Map<Integer, Boolean> mem = new HashMap<>();

        return dfs(mem, pool, desiredTotal);
    }

    private boolean dfs(Map<Integer, Boolean> mem, int pool, int target) {
        if (target <= 0) {
            return false; // current player looses
        }
        if (pool == 0) {
            return false;
        }
        int hash = pool * 300 + target;
        if (mem.containsKey(hash)) {
            return mem.get(hash);
        }

        for (int i = 1; i <= 20; i++) {
            int mask = 1 << i;
            if ((pool & mask) == 0){
                continue;
            }

            pool ^= mask;
            boolean otherWin = dfs(mem, pool, target - i);
            pool ^= mask;
            if (!otherWin) {
                mem.put(hash, true);
                return true;
            }
        }

        mem.put(hash,false);
        return false;
    }

    private boolean dfs(boolean[] pool, int curSum, int desiredTotal) {
        if (curSum >= desiredTotal) {
            return false;
        }

        int len = pool.length;
        for (int i = 1; i < pool.length; i++) {
            if (!pool[i]) {
                continue;
            }

            pool[i] = false;
            boolean ret = dfs(pool, curSum + i, desiredTotal);
            pool[i] = true;
            if (!ret) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC0464 so = new LC0464();
        int maxChoosable = 5;
        int desiredTotal = 50;
        boolean res = so.canIWin(maxChoosable, desiredTotal);
        System.out.println(res);
    }
}
