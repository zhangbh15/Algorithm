package LeetCode.LC401_500;

/**
 * Predict the Winner
 *
 * Given an array of scores that are non-negative integers.
 * Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on.
 * Each time a player picks a number, that number will not be available for the next player.
 * This continues until all the scores have been chosen. The player with the maximum score wins.
 *
 * Given an array of scores, predict whether player 1 is the winner.
 * You can assume each player plays to maximize his score.
 *
 * Example 1:
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5.
 * If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return False.
 *
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 *
 * Note:
 * 1 <= length of the array <= 20.
 * Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 */
public class LC0486 {
    /* Each player can take the leftmost or the leftmost two numbers from the remaining numbers. */
    public boolean sameEnd(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        if (array.length <= 2) {
            return true;
        }
        if (array.length == 3) {
            return array[0] + array[1] >= array[2];
        }

        int len = array.length;
        int[] dp = new int[len];
        dp[len - 1] = array[len - 1];
        dp[len - 2] = array[len - 2] + array[len - 1];
        dp[len - 3] = array[len - 3] + array[len - 2];
        dp[len - 4] = array[len - 4] + Math.max(array[len -3], array[len - 1]);
        for (int i = len - 5; i >= 0; i--) {
            dp[i] = array[i] + Math.min(dp[i + 2], dp[i + 3]);
            dp[i] = Math.max(dp[i], array[i] + array[i + 1] + Math.min(dp[i + 3], dp[i + 4]));
        }
        return dp[0] >= Math.min(dp[1], dp[2]);
    }

    /* Each player can take one number from either the left or the right end. */
    public boolean eitherEnd(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        if (array.length <= 2) {
            return true;
        }

        int len = array.length;
        int[][] dp = new int[len][len];
        dp[len - 1][len - 1] = array[len - 1];
        dp[len - 2][len - 2] = array[len - 2];
        dp[len - 2][len - 1] = Math.max(array[len - 2], array[len - 1]);

        for (int i = len - 3; i >= 0; i--) {
            dp[i][i] = array[i];
            dp[i][i + 1] = Math.max(array[i], array[i + 1]);
            for (int j = i + 2; j < len; j++) {
                dp[i][j] = array[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                dp[i][j] = Math.max(dp[i][j], array[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]));
            }
        }

        return dp[0][len - 1] >= Math.min(dp[1][len - 1], dp[0][len - 2]);
    }

    public static void main(String[] args) {
        LC0486 so = new LC0486();
        int[] nums = {10, 1, 2, 100, 3};
        // boolean res = so.sameEnd(nums);
        boolean res = so.eitherEnd(nums);
        System.out.println(res);
    }
}
