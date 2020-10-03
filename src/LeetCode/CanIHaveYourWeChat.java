package LeetCode;


/**
 * In a game, 2 players take turn to count number.
 * The first player start from 1. Each time a player can count 1 or 2 consecutive numbers.
 * The player who first reach the target number wins.
 * Given a target number, return if the first player can guarantee to win.
 *
 * Example: target 4
 *          return true;
 * player1: 1 --> player2: 2, 3 --> player1: 4   or
 * player1: 1 --> player2: 2 --> player1: 3, 4
 */
public class CanIHaveYourWeChat {
    public boolean canHave(int target) {
        if (target < 1) {
            throw new IllegalArgumentException();
        }

        Boolean[] mem = new Boolean[target];
        return dfs(0, target, mem);
    }

    private boolean dfs(int cur, int target, Boolean[] mem) {
        if (cur >= target) {
            return false;
        }
        if (mem[cur] != null) {
            return mem[cur];
        }

        for (int i = 1; i <= 2; i++) {
            boolean ret = dfs(cur + i, target, mem);
            if (!ret) {
                mem[cur] = true;
                return true;
            }
        }

        mem[cur] = false;
        return false;
    }

    public boolean canHaveDP(int target) {
        if (target < 1) {
            throw new IllegalArgumentException();
        }
        if (target < 3) {
            return true;
        }

        boolean[] dp = new boolean[target];
        dp[target - 1] = true;
        dp[target - 2] = true;

        for (int i = target - 3; i >= 0; i--) {
            for (int j = 1; j <= 2; j++) {
                if (!dp[i + j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        CanIHaveYourWeChat so = new CanIHaveYourWeChat();
        for(int i = 1; i < 10; i++) {
            System.out.println(so.canHaveDP(i));
        }
    }
}
