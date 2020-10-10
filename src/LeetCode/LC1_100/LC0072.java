package LeetCode.LC1_100;

/**
 * Edit Distance
 *
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class LC0072 {
    // DFS, Time Limit Exceeded on LeetCode
    public int minDistanceDFS(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }
        return dfs(word1, word2, 0, 0);
    }

    private int dfs(String word, String target, int i, int j) {
        int wordLen = word.length();
        int targetLen = target.length();

        if (i == wordLen) { // must add char
            return targetLen - j;
        }
        if (j == targetLen) { // must delete
            return wordLen - i;
        }

        if (word.charAt(i) == target.charAt(j)) {
            return dfs(word, target, i + 1, j + 1);
        }
        return 1 + Math.min(Math.min(dfs(word, target, i, j + 1), // add
                dfs(word, target, i + 1, j)),  // delete
                dfs(word, target, i + 1, j + 1)); // replace
    }



    // DFS with Pruning, 4ms on LeetCode
    public int minDistancePruning(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] mem = new int[len1][len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                mem[i][j] = -1;
            }
        }
        return pruning(word1, word2, 0, 0, mem);
    }

    private int pruning(String word, String target, int i, int j, int[][] mem) {
        int wordLen = word.length();
        int targetLen = target.length();

        if (i == wordLen) { // must add char
            return targetLen - j;
        }
        if (j == targetLen) { // must delete
            return wordLen - i;
        }

        if (mem[i][j] == -1) {
            if (word.charAt(i) == target.charAt(j)) {
                mem[i][j] = pruning(word, target, i + 1, j + 1, mem);
            } else {
                mem[i][j] = 1 + Math.min(Math.min(pruning(word, target, i, j + 1, mem), // add
                        pruning(word, target, i + 1, j, mem)),  // delete
                        pruning(word, target, i + 1, j + 1, mem)); // replace
            }
        }

        return mem[i][j];
    }



    // DP, 5ms on LeetCode
    public int minDistanceDP(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]);
                }
            }
        }
        return dp[len1][len2];
    }


    public static void main(String[] args) {
        LC0072 so = new LC0072();
        String word1 = "intention";
        String word2 = "execution";
        // int cost = so.minDistanceDFS(word1, word2);
        // int cost = so.minDistancePruning(word1, word2);
        int cost = so.minDistanceDP(word1, word2);
        System.out.println("The minimum number of operations is: " + cost);
    }
}
