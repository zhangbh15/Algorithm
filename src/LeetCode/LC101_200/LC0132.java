package LeetCode.LC101_200;

/**
 * Palindrome Partitioning II
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class LC0132 {
    // DFS, Time Limit Exceeded on LeetCode
    private int dfs(char[] chars, int index) {
        int len = chars.length;
        if (index >= len - 1 || isPalindrome(chars, index, len)) {
            return 0;
        }

        int cost = len - index - 1;
        for (int i = index; i < len; i++) {
            if (isPalindrome(chars, index, i + 1)) {
                cost = Math.min(cost, 1 + dfs(chars, i + 1));
            }
        }
        return cost;
    }
    private boolean isPalindrome(char[] chars, int start, int end) {
        if (end - start <= 1) {
            return true;
        }
        int i = start;
        int j = end - 1;
        while (i < j) {
            if (chars[i++] != chars[j--]) {
                return false;
            }
        }
        return true;
    }
    public int minCutDFS(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] chars = s.toCharArray();
        return dfs(chars, 0);
    }

    // DFS with Pruning, 206 ms on LeetCode
    private int pruning(char[] chars, int index, int[] mem) {
        int len = chars.length;
        if (index == len - 1) {
            return 0;
        }
        if (index >= len) {
            return -1;
        }

        if (mem[index] != -1) {
            return mem[index];
        }

        int cost = len - index - 1;
        for (int i = index; i < len; i++) {
            if (isPalindrome(chars, index, i + 1)) {
                cost = Math.min(cost, 1 + pruning(chars, i + 1, mem));
            }
        }
        mem[index] = cost;
        return cost;
    }
    public int minCutPruning(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        if (s.length() == 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] mem = new int[len - 1];
        for (int i = 0; i < len - 1; i++) {
            mem[i] = -1;
        }
        return pruning(chars, 0, mem);
    }

    // DP, 8ms on LeetCode
    public int minCutDP(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;

        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                isPalindrome[i][j] = (i == j) || (chars[i] == chars[j])
                        && (j == i + 1 || isPalindrome[i + 1][j - 1]);
            }
        }

        int[] dp = new int[len + 1];
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    dp[i] = Math.min(dp[i], 1 + dp[j - 1 + 1]);
                }
            }
        }

        return dp[len] - 1;
    }

    public static void main(String[] args) {
        LC0132 so = new LC0132();
        String s = "abac";
        // int minCut = so.minCutDFS(s);
        // int minCut = so.minCutPruning(s);
        int minCut = so.minCutDP(s);
        System.out.println(minCut);
    }
}
