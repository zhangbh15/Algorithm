package LeetCode.LC1_100;

/**
 * Longest Palindromic Substring
 *
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LC0005 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        char[] chars = s.toCharArray();
        int len = chars.length;
        int start = 0; // inclusive
        int end = 0; // exclusive => end - start == length

        for (int i = 0; i < len; i++) {
            int oddPalinLen = checkFromCenter(chars, i - 1, i + 1);
            int evenPalinLen = checkFromCenter(chars, i, i + 1);
            int palinLen = Math.max(oddPalinLen, evenPalinLen);
            if (palinLen > end - start) {
                start = i - (palinLen - 1) / 2;
                end = i + palinLen / 2 + 1;
            }
        }

        return s.substring(start, end);
    }

    private int checkFromCenter(char[] chars, int i, int j) {
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            i--;
            j++;
        }
        return j - i - 1;
    }

    public String longestPalindromeDp(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        char[] chars = s.toCharArray();
        int len = s.length();
        int start = 0; // inclusive
        int end = 0; // inclusive

        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    isPalindrome[i][j] = true;
                } else if (i + 1 == j) {
                    isPalindrome[i][j] = chars[i] == chars[j];
                } else {
                    isPalindrome[i][j] = isPalindrome[i + 1][j - 1]
                            && chars[i] == chars[j];
                }

                if (isPalindrome[i][j] && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }
}
