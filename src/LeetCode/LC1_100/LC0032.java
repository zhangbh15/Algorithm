package LeetCode.LC1_100;

/**
 * Longest Valid Parentheses
 *
 * Given a string containing just the characters '(' and ')', find the
 * length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LC0032 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        int max = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < len; i++) {
            int delta = 0;
            for (int j = i; j < len; j++) {
                if (chars[j] == '(') {
                    delta++;
                } else if (chars[j] == ')') {
                    delta--;
                } else {
                    throw new IllegalArgumentException("Unsupported character.");
                }

                if (delta == 0) {
                    max = Math.max(max, j - i + 1);
                }
                if (delta < 0){
                    break;
                }
            }
        }

        return max;
    }

    private boolean validate(char[] chars, int i, int j) {
        int delta = 0;
        for (int k = i; k < j; k++) {
            if (chars[k] == '(') {
                delta++;
            } else if (chars[k] == ')') {
                delta--;
            } else {
                throw new IllegalArgumentException("Unsupported character.");
            }

            if (delta < 0) {
                return false;
            }
        }

        return delta == 0;
    }

    // DP
    public int longestValidParenthesesDP(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int len = s.length();
        int max = 0;
        int[] dp = new int[len];

        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - 1) == ')'){
                    if (s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = i - dp[i - 1] - 2 >= 0 ?
                                dp[i - 1] + 2 + dp[i - dp[i - 1] - 2] :
                                dp[i - 1] + 2;
                    }
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
