package LeetCode.LC301_400;

import java.util.*;

/**
 * Coin Change
 *
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class LC0322 {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        int[] mem = new int[amount + 1];
        Arrays.fill(mem, -2);
        mem[0] = 0;
        return dfs(amount, mem, coins);
    }

    private int dfs(int target, int[] mem, int[] coins) {
        if (mem[target] != -2) {
            return mem[target];
        }

        int min = Integer.MAX_VALUE;
        for (int val : coins) {
            if (target >= val) {
                int cnt = dfs(target - val, mem, coins);
                min = cnt == -1 ? min : Math.min(min, cnt);
            }
        }

        mem[target] = min == Integer.MAX_VALUE ? -1 : min + 1;
        return mem[target];
    }


    public int coinChangeDP(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[i] = -1;
        }
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int val : coins) {
                int prev = i - val;
                if (prev >= 0 && dp[prev] != -1) {
                    dp[i] = dp[i] == -1 ? dp[prev] + 1 : Math.min(dp[i], dp[prev] + 1);
                }
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        LC0322 so = new LC0322();
        int[] coins = {5, 23, 11, 1};
        int amount = 88;
        int res = so.coinChangeDP(coins, amount);
        System.out.println(res);
    }
}
