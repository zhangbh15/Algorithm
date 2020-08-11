package LeetCode.LC1_100;

/**
 * Decode Ways
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class LC0091 {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        cnt = 0;
        dfs(0, 0, s.toCharArray());
        return cnt;
    }

    private int cnt;

    private void dfs(int idx, int prev, char[] chars) {
        if (idx == chars.length) {
            cnt++;
            return;
        }

        if (chars[idx] > '9' || chars[idx] < '0') {
            return; // invalid
        }

        int cur = chars[idx] - '0';
        if (cur > 0 && cur <= 9) {
            dfs(idx + 1, cur, chars);
        }

        cur += prev * 10;
        if (cur >= 10 && cur <= 26) {
            dfs(idx + 1, cur, chars);
        }
    }

    public int dp(String s) {
        // cc
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        dp[len - 1] = (s.charAt(len - 1) > '0' && s.charAt(len - 1) <= '9') ? 1 : 0;

        for (int i = len - 2; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch > '9' || ch < '0') {
                return 0;
            }

            dp[i] = ch == '0' ? 0 : dp[i + 1];
            int twoDigits = (ch - '0') * 10 + (s.charAt(i + 1) - '0');
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i + 2];
            }
        }

        return dp[0];
    }
}
