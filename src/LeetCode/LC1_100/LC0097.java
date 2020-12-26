package LeetCode.LC1_100;

/**
 * Interleaving String
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class LC0097 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            throw new IllegalArgumentException();
        }
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }

        int len1 = s1.length();
        int len2 = s2.length();

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c = s3.charAt(i + j - 2);
                if (s1.charAt(i - 1) == c) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (s2.charAt(j - 1) == c) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }

        return dp[len1][len2];
    }


    public static void main(String[] args) {
        LC0097 so = new LC0097();
        String str1 = "aabcc";
        String str2 = "dbbca";
        String str3 = "aadbbcbcac";

        boolean res = so.isInterleave(str1, str2, str3);
        System.out.println(res);
    }
}
