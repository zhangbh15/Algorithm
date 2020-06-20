package LeetCode.LC101_200;

/**
 * Longest Substring with At Most Two Distinct Characters
 *
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 *
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 *
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 */
public class LC0159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // cc
        char c1 = '\0';
        char c2 = '\0';
        int idx1 = -1;
        int idx2 = -1;

        int start = 0;
        int max = 0;

        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (c1 == ch) {
                idx1 = end;
            } else  if (c2 == ch) {
                idx2 = end;
            } else {
                if (idx1 < idx2) { // replace idx1
                    c1 = ch;
                    start = idx1 + 1;
                    idx1 = end;
                } else {
                    c2 = ch;
                    start = idx2 + 1;
                    idx2 = end;
                }
            }

            max = Math.max(max, end - start + 1);
        }
        return max;
    }
}
